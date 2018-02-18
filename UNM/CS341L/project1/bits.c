/* 
 * CS:APP Data Lab 
 * 
 * <Please put your name and userid here>
 * 
 * bits.c - Source file with your solutions to the Lab.
 *          This is the file you will hand in to your instructor.
 *
 * WARNING: Do not include the <stdio.h> header; it confuses the dlc
 * compiler. You can still use printf for debugging without including
 * <stdio.h>, although you might get a compiler warning. In general,
 * it's not good practice to ignore compiler warnings, but in this
 * case it's OK.  
 */

#if 0
/*
 * Instructions to Students:
 *
 * STEP 1: Read the following instructions carefully.
 */

You will provide your solution to the Data Lab by
editing the collection of functions in this source file.

INTEGER CODING RULES:
 
  Replace the "return" statement in each function with one
  or more lines of C code that implements the function. Your code 
  must conform to the following style:
 
  int Funct(arg1, arg2, ...) {
      /* brief description of how your implementation works */
      int var1 = Expr1;
      ...
      int varM = ExprM;

      varJ = ExprJ;
      ...
      varN = ExprN;
      return ExprR;
  }

  Each "Expr" is an expression using ONLY the following:
  1. Integer constants 0 through 255 (0xFF), inclusive. You are
      not allowed to use big constants such as 0xffffffff.
  2. Function arguments and local variables (no global variables).
  3. Unary integer operations ! ~
  4. Binary integer operations & ^ | + << >>
    
  Some of the problems restrict the set of allowed operators even further.
  Each "Expr" may consist of multiple operators. You are not restricted to
  one operator per line.

  You are expressly forbidden to:
  1. Use any control constructs such as if, do, while, for, switch, etc.
  2. Define or use any macros.
  3. Define any additional functions in this file.
  4. Call any functions.
  5. Use any other operations, such as &&, ||, -, or ?:
  6. Use any form of casting.
  7. Use any data type other than int.  This implies that you
     cannot use arrays, structs, or unions.

 
  You may assume that your machine:
  1. Uses 2s complement, 32-bit representations of integers.
  2. Performs right shifts arithmetically.
  3. Has unpredictable behavior when shifting an integer by more
     than the word size.

EXAMPLES OF ACCEPTABLE CODING STYLE:
  /*
   * pow2plus1 - returns 2^x + 1, where 0 <= x <= 31
   */
  int pow2plus1(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     return (1 << x) + 1;
  }

  /*
   * pow2plus4 - returns 2^x + 4, where 0 <= x <= 31
   */
  int pow2plus4(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     int result = (1 << x);
     result += 4;
     return result;
  }

FLOATING POINT CODING RULES

For the problems that require you to implent floating-point operations,
the coding rules are less strict.  You are allowed to use looping and
conditional control.  You are allowed to use both ints and unsigneds.
You can use arbitrary integer and unsigned constants.

You are expressly forbidden to:
  1. Define or use any macros.
  2. Define any additional functions in this file.
  3. Call any functions.
  4. Use any form of casting.
  5. Use any data type other than int or unsigned.  This means that you
     cannot use arrays, structs, or unions.
  6. Use any floating point data types, operations, or constants.


NOTES:
  1. Use the dlc (data lab checker) compiler (described in the handout) to 
     check the legality of your solutions.
  2. Each function has a maximum number of operators (! ~ & ^ | + << >>)
     that you are allowed to use for your implementation of the function. 
     The max operator count is checked by dlc. Note that '=' is not 
     counted; you may use as many of these as you want without penalty.
  3. Use the btest test harness to check your functions for correctness.
  4. Use the BDD checker to formally verify your functions
  5. The maximum number of ops for each function is given in the
     header comment for each function. If there are any inconsistencies 
     between the maximum ops in the writeup and in this file, consider
     this file the authoritative source.

/*
 * STEP 2: Modify the following functions according the coding rules.
 * 
 *   IMPORTANT. TO AVOID GRADING SURPRISES:
 *   1. Use the dlc compiler to check that your solutions conform
 *      to the coding rules.
 *   2. Use the BDD checker to formally verify that your solutions produce 
 *      the correct answers.
 */


#endif
/* Copyright (C) 1991-2016 Free Software Foundation, Inc.
   This file is part of the GNU C Library.

   The GNU C Library is free software; you can redistribute it and/or
   modify it under the terms of the GNU Lesser General Public
   License as published by the Free Software Foundation; either
   version 2.1 of the License, or (at your option) any later version.

   The GNU C Library is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   Lesser General Public License for more details.

   You should have received a copy of the GNU Lesser General Public
   License along with the GNU C Library; if not, see
   <http://www.gnu.org/licenses/>.  */
/* This header is separate from features.h so that the compiler can
   include it implicitly at the start of every compilation.  It must
   not itself include <features.h> or any other header that includes
   <features.h> because the implicit include comes before any feature
   test macros that may be defined in a source file before it first
   explicitly includes a system header.  GCC knows the name of this
   header in order to preinclude it.  */
/* glibc's intent is to support the IEC 559 math functionality, real
   and complex.  If the GCC (4.9 and later) predefined macros
   specifying compiler intent are available, use them to determine
   whether the overall intent is to support these features; otherwise,
   presume an older compiler has intent to support these features and
   define these macros by default.  */
/* wchar_t uses Unicode 8.0.0.  Version 8.0 of the Unicode Standard is
   synchronized with ISO/IEC 10646:2014, plus Amendment 1 (published
   2015-05-15).  */
/* We do not support C11 <threads.h>.  */
/* 
 * allEvenBits - return 1 if all even-numbered bits in word set to 1
 *   Examples allEvenBits(0xFFFFFFFE) = 0, allEvenBits(0x55555555) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 2
 */
int allEvenBits(int x) {
/*
Implementation takes 7 operations.
First few lines are making the number 0x55555555.
Then we want to bitwise and with that number to extract the even bits.
The return tells us if it is equal to the number 0x55555555
*/
  int mask = 0x55;
  int maskDouble = mask + (mask << 8);
  int maskQuadruple = maskDouble + (maskDouble << 16);
  int result = (x & maskQuadruple);  
  return !(result ^ maskQuadruple);
}
/*
 * bitParity - returns 1 if x contains an odd number of 0's
 *   Examples: bitParity(5) = 0, bitParity(7) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 4
 */
int bitParity(int x) {
/*
Doing a log based xor for the number.
Continuously halving the number with each xor retains the
parity of the number as a whole. Do this for each power of 2
until we are at the last bit. At that point if x == 1 then
it had an odd number of 0's, otherwise even number. 
*/
  x = x ^ (x >> 16);
  x = x ^ (x >> 8);
  x = x ^ (x >> 4);
  x = x ^ (x >> 2);
  x = x ^ (x >> 1);
  return x & 1;
}
/* 
 * bitXor - x^y using only ~ and & 
 *   Example: bitXor(4, 5) = 1
 *   Legal ops: ~ &
 *   Max ops: 14
 *   Rating: 1
 */
int bitXor(int x, int y) {
/*
Implementation takes 7 ops.
Logically, xor can be expresesd as:
p ^ q = (p | q) & ~(p & q)
DeMorgan's allows us to change first term from (p | q) to:
~(~p & ~q)
results in p ^ q = ~(~p & ~q) & ~(p & q)

And that is what we have in this function.
*/
  return ~(~x & ~y) & ~(x & y);
}
/* 
 * replaceByte(x,n,c) - Replace byte n in x with c
 *   Bytes numbered from 0 (LSB) to 3 (MSB)
 *   Examples: replaceByte(0x12345678,1,0xab) = 0x1234ab78
 *   You can assume 0 <= n <= 3 and 0 <= c <= 255
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 10
 *   Rating: 3
 */
int replaceByte(int x, int n, int c) {
/*
Implementation takes 7 ops.
n << 3 gives the actual amount of bits we want to shift, e.g.
1 << 3 -> 8, 8 bytes to shift.
Build a mask to mask the byte we are changing.
Make a byte representation with the byte we want to inject
of only the new byte(c).
x & ~mask gives the bytes we want to save then or'ed with
the byte we want to replace.
*/
  int mask = (0xFF << (n << 3));        // Of form 0x0000FF00
  int byteToInject = (c << (n << 3));    // Of form 0x0000ab00
  int newX = (x & ~mask) | byteToInject;    // Of form 0x1234ab78
  return newX;
}
/* 
 * TMax - return maximum two's complement integer 
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 4
 *   Rating: 1
 */
int tmax(void) {
/*
Implementation takes 2 ops.
Have to use (+ -1) as - isn't a "legal op".
1 << 31 gives us the number right outside the bounds of 2s complement
and then subtracting 1 gives us the absolute maximum.
Note that 1 << 31 technically gives us the sign bit, but as we subtract
1 right away we get the maximum non-negative value.
*/
  return (1 << 31) + -1;
}
/* 
 * fitsBits - return 1 if x can be represented as an 
 *  n-bit, two's complement integer.
 *   1 <= n <= 32
 *   Examples: fitsBits(5,3) = 0, fitsBits(-4,3) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 15
 *   Rating: 2
 */
int fitsBits(int x, int n) {
/*
Implementation is 12 ops.
First, we want to check if the number is negative. If it's negative
then we have another bit to be able to use in our n.
Shifting the number the appropriate amount of times to see if there
is a remainder.
*/

  int isNegative = !!(x & (1 << 31));
  int mask = ~(isNegative + ~0);
  x ^= mask;
  x >>= (n + ~0);
  return !x;
}
/* 
 * isEqual - return 1 if x == y, and 0 otherwise 
 *   Examples: isEqual(5,5) = 1, isEqual(4,5) = 0
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 5
 *   Rating: 2
 */
int isEqual(int x, int y) {
/*
Implementation takes 2 ops.
If you xor the same two numbers you will get 0.
Taking the ! of 0 gives 1.
*/
  return !(x ^ y);
}
/* 
 * isPositive - return 1 if x > 0, return 0 otherwise 
 *   Example: isPositive(-1) = 0.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 8
 *   Rating: 3
 */
int isPositive(int x) {
/*
Implementation takes 5 ops.
Frustratingly enough, !((1 << 31) & x) easily checks for negative
numbers, but we also have to check the "zero case". In order to do
that we have to also | with !x. This catches the zero case as we are
or'ing with !x (in this case all 0x7fffffff).
*/
  return !(((1 << 31) & x) | !x);  
}
/* 
 * subOK - Determine if can compute x-y without overflow
 *   Example: subOK(0x80000000,0x80000000) = 1,
 *            subOK(0x80000000,0x70000000) = 0, 
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 3
 */
int subOK(int x, int y) {

  int difference = x + (~y + 1);

  // Find out if x y and difference are negative, this will be useful for
  // figring out if we have an oveflow.
  int sizeX = (x >> 31) & 1;
  int sizeY = (y >> 31) & 1;
  int sizeDifference = (difference >> 31) & 1;
  
  // We are doing x - y
  // case1: Positive number - negative number = negative number CAN be overflow
  // case2: Negative num - positive number = positive number IS overflow
  int case1 = !sizeX & sizeY & sizeDifference;
  int case2 = sizeX & !sizeY & !sizeDifference;

  return !(case1 | case2);
}
/* howManyBits - return the minimum number of bits required to represent x in
 *             two's complement
 *  Examples: howManyBits(12) = 5
 *            howManyBits(298) = 10
 *            howManyBits(-5) = 4
 *            howManyBits(0)  = 1
 *            howManyBits(-1) = 1
 *            howManyBits(0x80000000) = 32
 *  Legal ops: ! ~ & ^ | + << >>
 *  Max ops: 90
 *  Rating: 4
 */
int howManyBits(int x) {
  
}
/* 
 * float_abs - Return bit-level equivalent of absolute value of f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representations of
 *   single-precision floating point values.
 *   When argument is NaN, return argument..
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 10
 *   Rating: 2
 */
unsigned float_abs(unsigned uf) {
/*
Implementation takes 6 ops.
First we check if the number is over Infinity, if so just return it.
Remember the details on floats:
8 bits for exponent, if all 8 are set (7F8 gives this) then the first
value is infinity and remaining values are NaN.
Otherwise, just eliminate the sign bit to give absolute value.
*/
  unsigned nanBound = 0x7F800000;
  unsigned possibleReturn = uf & ((1 << 31) - 1);
  if (possibleReturn > nanBound)
  {
    return uf;
  }
  else
  {
    return possibleReturn;
  }

}
/* 
 * float_twice - Return bit-level equivalent of expression 2*f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representation of
 *   single-precision floating point values.
 *   When argument is NaN, return argument
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned float_twice(unsigned uf) {
/*
Implementation is 18 ops. (Roughly?)
Breakdown your unsigned integer into an exponent portion and 
fractional portion. Exponent is 8 bits, fractional is 23 bits.
There are special cases close to boundary of NaN/Inf, at exponent
0xFE and 0xFF. Also special case for de-normalized as the mantissa
scales differently at this exponent of 0.
Return the original sign bit, exponent and fractional part for 2*uf.
*/

  int exponent = (uf & 0x7F800000) >> 23; // 23 fractional bits
  int fractional = (uf & 0x007FFFFF);

  // Going beyond nan bounds
  if(exponent == 0xFF && fractional >= 0)
  {
    return uf;
  }
  // De-normalized floats because exp = 0
  else if(exponent == 0)
  {
    fractional = fractional << 1;
    
    // If our number is at max fractional, it will transition to next exponent
    if(fractional & (1 << 23))
    {
      exponent = 1;
    }
    fractional = fractional & 0x7FFFFF;
  }
  // Infinity
  else if(exponent == 0xFE)
  {
    exponent = 0xFF;
    fractional = 0;
  }
  // Easy case, not denormalized or near NaN boundaries, just give it *(2^1) 
  else
  {
    exponent = exponent + 1;
  }

  return (uf & (1 << 31)) | (exponent << 23) | fractional;
}
/*
 * trueFiveEighths - multiplies by 5/8 rounding toward 0,
 *  avoiding errors due to overflow
 *  Examples: trueFiveEighths(11) = 6
 *            trueFiveEighths(-9) = -5
 *            trueFiveEighths(0x30000000) = 0x1E000000 (no overflow)
 *  Legal ops: ! ~ & ^ | + << >>
 *  Max ops: 25
 *  Rating: 4
 */
int trueFiveEighths(int x)
{
/*
Implementation is 10 ops.
Idea is to shift the number by 3 to right which is equivalent to
a division by 8. Create another number that holds the bits that
you shifted away. Then take your number that was divided by 8
and multiply it by 5. Great, we have a x * 5/8 now.
In order to recover what we lost by shifting 3 bits, calculate
those shifted bits * 5.
Use a negative sign bit mask to round those numbers toward 0.
Add it all together an shift the signbit by 3 to get x * 5/8.
*/
  int eighths = x >> 3; // Divide by 8
  int shiftedAway = x & 7; // Capture the bits we shifted off
  int eighthsBy5 = (eighths << 2) + eighths;
  int shiftedAwayBy5 = (shiftedAway << 2) + shiftedAway;
  int signBit = x >> 31 & 7; // Gives us the negative number rounding toward zero
  return eighthsBy5 + (shiftedAwayBy5 + signBit >> 3);
}





