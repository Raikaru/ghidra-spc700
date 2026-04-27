# ghidra-spc700

Sony SPC700 processor module scaffold for Ghidra.

## Status

This repository currently contains an early but compiling SPC700 language
implementation. It is not complete, but it has enough structure for Ghidra to
register the language and disassemble a small core subset: `NOP`, common
branches, `CALL`/`JMP`/`RET`, immediate/data moves, immediate `A,#imm` ALU
operations, pushes/pops, register increments/decrements, and basic flag-control
instructions.

## Layout

```text
Module.manifest
.github/workflows/sleigh-compile.yml
data/languages/
  spc700.ldefs
  spc700.cspec
  spc700.pspec
  spc700.slaspec
tests/
```

## Local compile

```text
<ghidra>/support/sleigh -a <ghidra>/Ghidra/Processors/SPC700
```

## Next implementation targets

1. Complete register/flag semantics and PSW packing/unpacking.
2. Add the remaining ALU addressing modes beyond the initial immediate forms.
3. Add bit, multiplication/division, and word operations.
4. Add vector/default-symbol support for SPC dumps.
5. Expand headless smoke tests into opcode-family coverage.
