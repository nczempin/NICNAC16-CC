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
- Java Development Kit (JDK) 8 or higher
- [Git LFS](https://git-lfs.github.com/) for binary dependencies
- Basic understanding of C programming and compiler concepts

### Installation
1. Install Git LFS if you haven't already:
   ```
   git lfs install
   ```

2. Clone the repository:
   ```
   git clone https://github.com/nczempin/NICNAC16-CC.git
   cd NICNAC16-CC
   ```

3. Ensure all dependencies are downloaded:
   ```
   git lfs pull
   ```

### Running the Compiler
1. Navigate to the compiler directory:
   ```
   cd de.czempin.nicnac16.compiler
   ```

2. Compile a C file:
   ```
   java -cp lib/*:bin de.czempin.nicnac16.compiler.Main path/to/your/file.c
   ```

3. Example with included test file:
   ```
   java -cp lib/*:bin de.czempin.nicnac16.compiler.Main test001.c
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
- `de.czempin.nicnac16.compiler/` - Main compiler source code
  - `src/` - Java source files implementing the compiler
  - `lib/` - External dependencies (JUnit, Hamcrest, Guava)
  - `test*.c` - Example C files for testing the compiler

## Related Projects
- [NICNAC16](https://github.com/nczempin/NICNAC16) - The CPU architecture this compiler targets
- [NICNAC16-ASS](https://github.com/nczempin/NICNAC16-ASS) - Assembler for NICNAC16
- [NICNAC16-SIM](https://github.com/nczempin/NICNAC16-SIM) - Simulator for NICNAC16

## Development Status
This is an experimental educational project in active development. Contributions and feedback are welcome.
