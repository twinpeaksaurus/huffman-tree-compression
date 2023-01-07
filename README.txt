This program implements a Huffman Tree encoding and decoding process. While it does not work currently with complex, multi-file directories, nor convert binary strings into bit/boolean vectors, it achieves approximately 400% compression. (It current writes binary as strings in order to simplify the demonstration of the process.)

(NOTE: as of this submission, character precedence needs to be corrected when characters have equal frequency.)

In order to run this program on test data, please follow the steps below.

1. Create a text file formatted as follows:

<NAME OF OUTPUT FILE (stored in same directory)>
<CHAR - WEIGHT>
<A - 19  
B - 16  
[...]>
!!!
<EXAMPLE TEXT STRINGS TO DECODE>
!!!
<EXAMPLE BINARY STRINGS TO DECODE>
!!!

2. Run the program with java HuffmanDriver <INPUT FILE NAME>

3. To use my initial inputs, you may use test.txt after deleting output.txt or changing the name of the output file in the first line.
