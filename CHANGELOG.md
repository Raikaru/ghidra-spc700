# Changelog

All notable changes to `ghidra-spc700` will be documented in this file.

## [0.1.0] - 2026-04-26

### Added

- Initial repository scaffold for a standalone SPC700 Ghidra processor module.
- Minimal language registration files (`ldefs`, `cspec`, `pspec`, `slaspec`).
- CI skeleton that downloads Ghidra and compiles the processor module.

- Initial instruction subset covering `NOP`, common branches, `CALL` / `JMP` /
  `RET`, immediate/data moves, pushes/pops, register inc/dec, and basic flag
  control instructions.
- Immediate accumulator ALU subset for `ADC`, `SBC`, `AND`, `EOR`, `OR`, and
  `CMP`, with opcode-family smoke coverage.
- Direct-page accumulator ALU forms for `ADC`, `SBC`, `AND`, `EOR`, `OR`,
  and `CMP`; fixed carry/borrow flag helpers to account for carry input without
  truncating the operand.
- Headless smoke script and workflow smoke import for the initial subset.