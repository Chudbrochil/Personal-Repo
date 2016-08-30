#include <stdio.h>
#include <math.h>

// Euler problem 3
// The prime factors of 13195 are 5, 7, 13 and 29.
// What is the largest prime factor of the number 600851475143 ?


int main(void){

	double long num = 600851475143;
	double long num2 = 10000000000;
	long int i;
	int mod;

	//double long newNum;
	//newNum = num / 2;

	for(i = 0; i < num2; ++i){//(num / 2); ++i){
		
		
		mod = fmod(num, i);
		
		//printf("%d\n", i);
		
		//printf("i: %d, mod: %d\n", i, mod);
		//printf("Mod: %d\n", mod);
		if (mod == 0){
			printf("Num: %d\n", i);
		}

	}







	return 0;





}



