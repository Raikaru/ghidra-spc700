# ghidra-spc700

Sony SPC700 processor module scaffold for Ghidra.

## Status

This repository currently contains a minimal language scaffold that compiles,
but it is not yet a usable SPC700 disassembler/decompiler. The present SLEIGH
spec only defines a placeholder `NOP` instruction so CI can verify module
packaging and language registration early.

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

1. Register file and flags with correct naming and bit layout.
2. Core addressing modes.
3. Control-flow instructions.
4. ALU/load-store coverage.
5. Basic headless smoke tests.
