// SPDX-License-Identifier: MIT
import java.math.BigInteger;

import ghidra.app.cmd.disassemble.DisassembleCommand;
import ghidra.app.emulator.EmulatorHelper;
import ghidra.app.script.GhidraScript;
import ghidra.program.model.address.Address;

public class SemanticSmoke extends GhidraScript {
    private EmulatorHelper emu;

    @Override
    public void run() throws Exception {
        String[] args = getScriptArgs();
        if (args.length < 1) {
            throw new IllegalArgumentException("usage: SemanticSmoke <language-id>");
        }
        String actualLanguage = currentProgram.getLanguageID().getIdAsString();
        if (!args[0].equals(actualLanguage)) {
            throw new AssertionError("language mismatch: expected " + args[0] + " got " + actualLanguage);
        }
        Address start = currentProgram.getMinAddress();
        if (!new DisassembleCommand(start, null, true).applyTo(currentProgram, monitor)) {
            throw new AssertionError("failed to disassemble semantic smoke program");
        }
        emu = new EmulatorHelper(currentProgram);
        try {
            runDirectPagePFlag(start);
            runDecimalAndMulFlags(start.add(6));
        }
        finally {
            emu.dispose();
        }
    }

    private void runDirectPagePFlag(Address start) throws Exception {
        emu.writeRegister("PC", start.getOffset());
        emu.writeRegister("PF", 0);
        emu.writeRegister("SP", 0xff);
        emu.writeMemory(addr(0x0010), new byte[] { 0x11 });
        emu.writeMemory(addr(0x0110), new byte[] { (byte) 0x80 });

        step("SETP");
        expectReg("PF", 1);
        step("MOV A,dp with P=1");
        expectReg("A", 0x80);
        expectReg("NF", 1);
        expectReg("ZF", 0);

        step("CLRP");
        expectReg("PF", 0);
        step("MOV A,dp with P=0");
        expectReg("A", 0x11);
        expectReg("NF", 0);
        expectReg("ZF", 0);
    }

    private void runDecimalAndMulFlags(Address start) throws Exception {
        emu.writeRegister("PC", start.getOffset());

        emu.writeRegister("A", 0xfa);
        emu.writeRegister("CF", 0);
        emu.writeRegister("HF", 0);
        step("DAA high adjust");
        expectReg("A", 0x60);
        expectReg("CF", 1);
        expectReg("ZF", 0);

        emu.writeRegister("A", 0x15);
        emu.writeRegister("CF", 1);
        emu.writeRegister("HF", 0);
        step("DAS low adjust");
        expectReg("A", 0x0f);
        expectReg("CF", 1);

        emu.writeRegister("A", 0x10);
        emu.writeRegister("Y", 0x10);
        step("MUL high-byte flags");
        expectReg("A", 0x00);
        expectReg("Y", 0x01);
        expectReg("NF", 0);
        expectReg("ZF", 0);
    }

    private void step(String label) throws Exception {
        if (!emu.step(monitor)) {
            throw new AssertionError("emulator failed during " + label + ": " + emu.getLastError());
        }
        println("MARK: STEP " + label + " PC=" + emu.readRegister("PC").toString(16));
    }

    private void expectReg(String name, long expected) {
        BigInteger value = emu.readRegister(name);
        long actual = value.longValue() & 0xffffL;
        if (actual != expected) {
            throw new AssertionError(name + " mismatch: expected 0x" + Long.toHexString(expected)
                + " got 0x" + Long.toHexString(actual));
        }
    }

    private Address addr(long offset) {
        return currentProgram.getAddressFactory().getDefaultAddressSpace().getAddress(offset);
    }
}
