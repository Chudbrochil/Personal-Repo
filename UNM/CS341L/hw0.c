/**
Anthony Galczak - WGalczak@gmail.com - agalczak@unm.edu
CS341L - HW0

Computer Systems: A Programmer's Perspective 3rd Edition
Problems 2.59, 2.61, 2.68
**/


/**
Answers to questions without methods and tests
2.59
return ((x & 0xFF) ^ (y & 0xFFFFFF00));
2.61A
return x && 1;
2.61B
return ~x && 1;
2.61C
return (x & 0xFF) && 1;
2.61D
return ((x & 0xFF000000) ^ 0xFF000000) && 1;
2.68
int mask_shift = ((sizeof(int) << 3) - n);
return (0xFFFFFFFF >> mask_shift);
**/

#include <stdio.h>
#include <limits.h>

// 2.59
int combine(int x, int y)
{
	return ((x & 0xFF) ^ (y & 0xFFFFFF00));
}

// 2.61 A
int has1(int x)
{
	return x && 1;
}

// 2.61 B
int has0(int x)
{
	return ~x && 1;
}

// 2.61 C
int oneInLSB(int x)
{
	return (x & 0xFF) && 1;
}

// 2.61 D
int zeroInMSB(int x)
{
	return ((x & 0xFF000000) ^ 0xFF000000) && 1 ;
}


// 2.68
int lower_one_mask(int n)
{
	int mask_shift = ((sizeof(int) << 3) - n);
	return (0xFFFFFFFF >> mask_shift);
}


int main()
{
	// Test cases

	printf("Ex. 2.59: Return least significant byte of x and remaining bytes of y\n");
	int x = 0x89ABCDEF;
	int y = 0x76543210;
	printf("least significant byte of 0x%X and remaining bytes of 0x%X returns 0x%X\n\n", x, y, combine(x, y));


	printf("Ex. 2.61: Evaluate to 1 when the following conditions are true and to 0 when they are false.\n\n");
	int allOnes = 0xFFFFFFFF;
	int allZeros = 0x00000000;
	int oneInLSBInt = 0x00000008;
	int zeroInMSBInt = 0x8FFFFFFF;
	printf("A. Any bit of x equals 1\n0x%X returns %d\n0x%X returns %d\n0x%X returns %d\n0x%X returns %d\n\n", 
		allOnes, has1(allOnes), allZeros, has1(allZeros), oneInLSB, has1(oneInLSB), zeroInMSB, has1(zeroInMSB));
	printf("B. Any bit of x equals 0\n0x%X returns %d\n0x%X returns %d\n0x%X returns %d\n0x%X returns %d\n\n", 
		allOnes, has0(allOnes), allZeros, has0(allZeros), oneInLSB, has0(oneInLSB), zeroInMSB, has0(zeroInMSB));
	printf("C. Any bit in the least significant byte of x equals 1\n0x%X returns %d\n0x%X returns %d\n\n", 
		oneInLSBInt, oneInLSB(oneInLSBInt), allZeros, oneInLSB(allZeros));
	printf("D. Any bit in the most significant byte of x equals 0\n0x%X returns %d\n0x%X returns %d\n\n", 
		zeroInMSBInt, zeroInMSB(zeroInMSBInt), allOnes, zeroInMSB(allOnes));


	printf("Ex. 2.68 Write lower_one_mask(int n) function.\n");
	printf("6:%X, 17:%X, 32:%X\n", lower_one_mask(6), lower_one_mask(17), lower_one_mask(32));
	

	return 0;
}







