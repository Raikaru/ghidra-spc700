# SPC700 APU Workflow

Use this processor module for locally extracted or observed SPC700 programs.
It is especially useful when validating an SNES loader/65816 analysis that
finds SPC upload routines or sound command dispatch.

## Safe Workflow

1. In the 65816 program, identify routines that write to APU ports or upload
   SPC700 code.
2. Record labels, bus addresses, and behavior hypotheses in the caller project.
3. Locally carve or capture the SPC700 blob. Keep it outside git.
4. Import the blob as raw binary with language `SPC700:LE:16:default`.
5. Analyze command parser logic, DSP register writes, sequence dispatch, and
   sample metadata references.
6. Feed only payload-free facts back to the caller project: labels, offsets,
   hashes, control-flow notes, and validation tasks.

## Do Not Commit

- SPC program payloads;
- BRR samples;
- decoded songs;
- copied disassembly blocks;
- screenshots of copyrighted data;
- raw byte ranges from a commercial ROM.

## Local Checks

For this repo, use the normal CI-equivalent checks:

```powershell
docker exec ghidra-mcp bash -lc "/opt/ghidra/support/sleigh -a /opt/ghidra/Ghidra/Processors/SPC700"
```

The release package workflow builds an installable Ghidra extension zip with
`extension.properties`, `Module.manifest`, and `data/` at the zip root.
