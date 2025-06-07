# NICNAC16-CC: C Compiler for NICNAC16 CPU

## What Problem Does This Solve?
NICNAC16-CC is a minimal C compiler specifically designed for the [NICNAC16 CPU architecture](https://github.com/nczempin/NICNAC16). It enables developers to write programs in a subset of the C programming language and compile them to run on the NICNAC16 processor, bridging the gap between high-level C code and NICNAC16 assembly.

## Who Is This For?
- Developers working with the NICNAC16 CPU architecture
- Computer architecture enthusiasts interested in compiler design
- Students learning about compiler construction and code generation
- Anyone exploring the NICNAC16 ecosystem of tools

## Current Implementation Status
- ✅ Basic C syntax parsing
- ✅ Variable declarations and assignments
- ✅ Integer expressions and arithmetic operations
- ✅ Function declarations with return statements
- ✅ Simple control structures (if, while, for)
- 🚧 Advanced control flow (switch, do-while)
- 🚧 Pointers and memory management
- 🚧 Structs and complex data types
- 📋 Optimization passes

## Setup Instructions

### Prerequisites
- A C++20 compatible compiler (tested with `g++` 13)
- Basic understanding of C programming and compiler concepts

### Installation
1. Clone the repository:
   ```
   git clone https://github.com/nczempin/NICNAC16-CC.git
   cd NICNAC16-CC
   ```

### Running the Compiler
1. Ensure `g++` is installed (see `setup.sh`):
   ```bash
   ./setup.sh
   ```

2. Build the compiler:
   ```bash
   make
   ```

3. Run the compiler with an example file:
   ```bash
   make run ARGS=samples/test001.c
   ```

## Project Scope

### What This IS
- A compiler for a subset of the C language targeting the NICNAC16 architecture
- A tool for generating executable code for the NICNAC16 CPU
- An educational project demonstrating compiler construction principles

### What This IS NOT
- Not a full C99/C11 compiler with complete standard library support
- Not intended for production-level software development
- Not optimized for performance or code size (educational focus)

## Repository Structure
- `src/` - C++20 source files implementing the compiler
- `samples/` - Example C programs used for testing
- `setup.sh` - script to check build prerequisites
- `Makefile` - simple build rules

## Related Projects
- [NICNAC16](https://github.com/nczempin/NICNAC16) - The CPU architecture this compiler targets
- [NICNAC16-ASS](https://github.com/nczempin/NICNAC16-ASS) - Assembler for NICNAC16
- [NICNAC16-SIM](https://github.com/nczempin/NICNAC16-SIM) - Simulator for NICNAC16

## Development Status
This is an experimental educational project in active development. Contributions and feedback are welcome.
