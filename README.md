# ghidra-spc700

Sony SPC700 processor module for Ghidra.

## Status

This repository currently contains a compiling SPC700 language implementation
with constructors for all 256 opcode bytes. The implemented decode coverage
includes control flow, calls/returns, loads/stores, accumulator and
memory-to-memory ALU forms, shifts/rotates, word arithmetic, bit operations,
pushes/pops, register/memory increments/decrements, and basic flag-control
instructions. Direct-page addressing tracks the SPC700 `P` flag for `CLRP` and
`SETP` flows, and CI includes mnemonic, operand-text, and emulator semantic
smoke tests.

## Layout

```text
Module.manifest
.github/workflows/sleigh-compile.yml
.github/workflows/package-release.yml
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

1. Continue broadening semantic opcode-family coverage beyond the current smoke cases.
2. Add vector/default-symbol support for SPC dumps.
3. Cut tagged releases with the installable zip produced by the package workflow.
