back: [[02 - Intro cont.]]

# real mode addressing
allows addressing of only the first 1M byte of the memory space.
- the first 1M byte of the memory called as **real memory, conventional memory, or DOS memory system.**

## segments and offsets
all real mode memory addresses must contain:
- segment address
- offset address
**segment address**: defines the beginning address of any 64K byte memory segment
**offset address**: selects any location within the 64K byte memory segment

![[Pasted image 20231129024924.png]]

Once the beginning address is known, the ending address is found by adding **FFFFH.**

> address = address in the segment register x 10H + offset address

## default segment and offset registers

![[Pasted image 20231129030506.png]]

