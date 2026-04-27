# ghidra-spc700

Sony SPC700 processor module scaffold for Ghidra.

## Status

This repository currently contains an early but compiling SPC700 language
implementation. It is not complete, but it has enough structure for Ghidra to
register the language and disassemble a small core subset: `NOP`, common
branches, `CALL`/`JMP`/`RET`, immediate/data/register moves, accumulator ALU
addressing modes plus memory-to-memory ALU forms for `ADC`/`SBC`/`AND`/`EOR`/
`OR`/`CMP`, shifts/rotates, word arithmetic, pushes/pops, register/memory
increments/decrements, direct-page bit branches and bit set/clear operations,
`CBNE`/`DBNZ`, and basic flag-control instructions.

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

1. Add bit carry operations, multiplication/division, decimal-adjust, and special branch/call operations.
2. Expand load/store addressing modes and control-flow variants.
3. Add vector/default-symbol support for SPC dumps.
4. Expand headless smoke tests into semantic opcode-family coverage.
