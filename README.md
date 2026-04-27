# ghidra-spc700

Sony SPC700 processor module scaffold for Ghidra.

## Status

This repository currently contains a compiling SPC700 language implementation
with constructors for all 256 opcode bytes. The implemented decode coverage
includes control flow, calls/returns, loads/stores, accumulator and
memory-to-memory ALU forms, shifts/rotates, word arithmetic, bit operations,
pushes/pops, register/memory increments/decrements, and basic flag-control
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

1. Review and harden instruction semantics against hardware references.
2. Model direct-page `P` flag behavior across direct-page operand tables.
3. Add vector/default-symbol support for SPC dumps.
4. Expand headless smoke tests into semantic opcode-family coverage.
