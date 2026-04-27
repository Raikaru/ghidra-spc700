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
- Remaining accumulator ALU addressing modes plus `(X),(Y)`, `dp,dp`, and
  `dp,#imm` memory-to-memory forms for the same ALU families.
- PSW pack/unpack helpers now keep `PSW` coherent with individual flags for
  implemented flag-affecting instructions and `PUSH`/`POP PSW`.
- Completed SPC700 `MOV` load/store addressing forms for accumulator, index
  registers, memory destinations, `(X)+`, `dp,dp`, and `dp,#imm`, with CI smoke
  coverage.
- Shift/rotate memory forms, direct/absolute `INC`/`DEC`, `CMP X/Y`, and
  `ADDW`/`SUBW`/`CMPW` word operations, with smoke coverage.
- Direct-page bit set/clear operations and bit/compare/decrement branch forms
  (`SET1`, `CLR1`, `BBS`, `BBC`, `CBNE`, `DBNZ`), with smoke coverage.
- Remaining SPC700 opcode constructors, covering `TCALL`, `PCALL`, `JMP
  [!abs+X]`, `RETI`, `SLEEP`, `STOP`, memory-bit carry operations, `TSET1`,
  `TCLR1`, `MOVW`, `INCW`, `DECW`, `MUL`, `DIV`, `DAA`, `DAS`, and `XCN`,
  with smoke coverage for the completed opcode map.
- Direct-page addressing now tracks the `P` flag through `CLRP`/`SETP` context,
  and normal memory operands export effective addresses instead of being
  double-dereferenced in p-code.
- Added exact instruction-text and emulator semantic smoke scripts for
  high-risk operand rendering, direct-page `P` behavior, and selected flag
  semantics.
- Added release packaging workflow that builds an installable
  `Ghidra/Processors/SPC700` zip and attaches it to tagged GitHub releases.
- Headless smoke script and workflow smoke import for the initial subset.
