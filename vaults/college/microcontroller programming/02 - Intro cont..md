---
dg-publish: true
---
back: [[01 - Intro]]
# the microprocessor
- called the cpu (central processing unit)
- controls memory and I/O through connections called _buses_
- memory and I/O controlled via instructions stored in memory, executed by the microprocessor.

**microprocessor performs 3 main tasks**
- data transfer between itself <=> memory||I/O systems
- simple arithmetic and logic operations
- program flow via simple decisions
=> a microprocessor can decide a number == 0, > 0, and so forth
	those decisions allow it to modify the program flow, so programs appear to think through these simple decisions.

# buses
=> a common group of wires that interconnect components in a computer system.

they transfer:
- address
- data
- control information between microprocessor||memory||I/O

three buses exist for this transfer of information:
- address bus
- data bus
- control bus
![[Pasted image 20231104002804.png]]
## address bus
the address bus requests a memory location from the memory or an I/O location from the I/O devices.
- if I/O addressed, the address bus contains a 16-bit I/O address from 0000H to FFFFH.
- if memory is addressed, the bus contains a memory address, varying in width by type of microprocessor. **(20-bits for 8086)**
## data bus
the data bus transfers information between the microprocessor and its memory and I/O address space.

data transfers vary in size, from 8 bits wide to 64 bits wide in various Intel microprocessors. **(8086 transfers 16 bits of data)**
## control bus
the control bus lines select and cause memory or I/O to perform a read or write operation.

in most computer systems, there are four control bus connections:
- MRDC (memory read control)
- MWTC (memory write control)
- IORC (I/O read control)
- IOWC (I/O write control)
**overbar indicates the control signal is active or low;
active when logic zero appears on control line.**

### cycle
1. mp reads a memory location by sending the memory an address through the address bus.
2. it sends a MRDC signal cause the memory to read data.
3. data read from memory are passed to the mp through the data bus.
4. whenever a memory write, I/O write, or I/O read occurs, the same sequence ensues.

# number systems 
conversions:
- decimal <=> binary
- decimal <=> hexadecimal
- binary <=> hexadecimal
## digits
- hexadecimal
- decimal
- octal (not popular)
- binary
## positional notation
larger numbers are constructed using positional notation.
- an example is decimal number 132,
  this number has 1 hundred, 3 tens, 2 units.

**in decimals:**
the units position has a weight of 10<sup>0</sup>, or 1.
the tens position has a weight of 10<sup>1</sup>, or 10.
the hundreds position has a weight of 10<sup>2</sup>, or 100.

## BCD (binary coded hexadecimal)
Binary-coded hexadecimal (BCH) is a hexadecimal number written each digit is represented by a 4-bit binary number.
![[Pasted image 20231104032749.png]]
## complements
it exist to represent negative numbers.

two systems used to represent negative data:
- radix
- radix-1 (earliest negative complement)

![[Pasted image 20231104032911.png]]

# computer data formats
## ascii and unicode
Standard ASCII code is a 7-bit code.
- eighth and most significant bit used to hold parity
_parity will be discussed later._

Many Windows-based applications use the Unicode system to store alphanumeric data.
– stores each character as 16-bit data

- Codes 0000H–00FFH are the same as standard ASCII code.
- Remaining codes, 0100H–FFFFH, store all special characters from many character sets.

# BCD Data
the range of a BCD digit is between 0000<sub>2</sub> and 1001<sub>2</sub>.
stored in 2 forms:
- packed form:
	- packed BCD data stored as two digits per byte.
	- used for BCD addition and subtraction in the instruction set of the mp.
- unpacked form:
	- unpacked BCD data stored as one digit per byte.
	- returned from a keypad or keyboard.

![[Pasted image 20231104043925.png]]

POS terminals requires BCD data.
- also devices that perform a minimal amount of simple arithmetic.

if a system requires complex arithmetic, BCD data are _seldom_ used.
- there is no simple and efficient method of performing complex BCD arithmetic.

# byte-sized data
stored as unsigned and signed integers.
- difference in these forms is the weight of the leftmost bit position.

![[Pasted image 20231104050310.png]]

unsigned integers range => 00H - FFH || 0 - 255
signed integers range => -128 - +127

# real numbers (floating-point number)
contains two parts:
- mantissa, significand, fraction (all are the same but alias)
- exponent

4-byte number is called **single-precision**
8-byte number is called **double-precision**

![[Pasted image 20231128205239.png]]

next: [[03 - The Microprocessor and Its Architecture]]
