// SPDX-License-Identifier: MIT
import ghidra.app.cmd.disassemble.DisassembleCommand;
import ghidra.app.script.GhidraScript;
import ghidra.program.model.address.Address;
import ghidra.program.model.listing.Instruction;

public class ProcessorSmoke extends GhidraScript {
    @Override
    public void run() throws Exception {
        String[] args = getScriptArgs();
        if (args.length < 2) {
            throw new IllegalArgumentException("usage: ProcessorSmoke <language-id> <mnemonic> [mnemonic...]");
        }
        String actualLanguage = currentProgram.getLanguageID().getIdAsString();
        if (!args[0].equals(actualLanguage)) {
            throw new AssertionError("language mismatch: expected " + args[0] + " got " + actualLanguage);
        }
        Address cur = currentProgram.getMinAddress();
        for (int i = 1; i < args.length; i++) {
            if (getInstructionAt(cur) == null && !new DisassembleCommand(cur, null, true).applyTo(currentProgram, monitor)) {
                throw new AssertionError("failed to disassemble at " + cur);
            }
            Instruction insn = getInstructionAt(cur);
            if (insn == null) {
                throw new AssertionError("no instruction at " + cur);
            }
            String actualMnemonic = insn.getMnemonicString();
            if (!args[i].equals(actualMnemonic)) {
                throw new AssertionError("mnemonic mismatch at " + cur + ": expected " + args[i]
                    + " got " + actualMnemonic + " (" + insn + ")");
            }
            println("MARK: INSN " + cur + " " + insn);
            cur = cur.add(insn.getLength());
        }
    }
}
