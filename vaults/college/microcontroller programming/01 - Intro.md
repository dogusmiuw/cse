---
dg-publish: true
---
### Reminders
- 4-bit wide memory location => nibble
- 8-bit wide memory location => _**byte**_

Word => a collection of 2 bytes (16-bits)
1KB => 1024 bytes => 2<sup>10</sup> bytes
1MB => 1024 KB => 2<sup>20</sup> bytes
1GB => 1024 MB => 2<sup>30</sup> bytes
1TB => 1024 GB => 2<sup>40</sup> bytes
B => byte
b => bit

---
##### First microprocessor => Intel 4004
- 4-bit programmable microprocessor
- Addressed 4096, 4-bit-wide memory locations.
- 4004 instruction set, 45 instructions.

> 8-bit, 16-bit, 32-bit, and 64-bit microprocessor: refers to number of bits manipulated in one operation. It requires external memory to execute programs. It cannot directly interface to I/O devices, peripheral chips are needed.

##### Difference between microprocessor and microcontroller
Microprocessor contains:
> - ALU
> - Control Unit
> - Registers
> _It houses a more powerful CPU on a single chip that connects to external peripherals._

Microcontroller contains:
> - ...
> - ROM
> - RAM
> - Peripherals (timer, I/O ports, etc.)
> _It has all this things on the same chip._

Microprocessor based computer system
![[Pasted image 20231103053142.png]]

Memory structure of all Intel-based PC's
![[Pasted image 20231103053527.png]]

- TPA => transient program area
- System area
- XMS => extended memory system

Real (conventional) memory => System area + XMS
> Intel microprocessors designed to function in this area using real mode operation.

### TPA (transient program area)
it holds the DOS (disk operating system), and the other programs that control the computer system.
- it is a DOS concept. not really applicable on Windows.
- stores any currently active/inactive DOS application programs.
- length => 640K bytes
### System Area
contains programs on ROM or flash memory, and areas of RAM memory for data storage.
- smaller than TPA.
### I/O Space
I/O devices allow the microprocessor to communicate with the outside world.
- extends _from_ I/O port 0000H _to_ port FFFFH.
- under 0400H is considered reserved for system devices.
**generally:**
- 0000H - 00FFH => main board components
- 0100H - 03FFH => handles devices located on plug-in cards or also on main board.

next: [[02 - Intro cont.]]
