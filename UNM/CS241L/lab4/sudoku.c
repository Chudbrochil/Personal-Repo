/********************************/
/* Anthony Galczak              */
/* 09-18-16                     */
/* CS-241 Section 001           */
/* Solves a given sudoku puzzle */
/********************************/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>


void processArray(char *oneD){
	char twoD[9][9];
	int i, j, k, errored;
	
	/* Assigning 1D array to 2D array */	
	for (i = 0; i < 9; ++i)
	{
		for(j = 0; j < 9; ++j)
		{
			twoD[i][j] = oneD[(i*9) + j];
		}
		
	}

	/* Checking for problems with array, nums in same column or row */
	errored = 0;
	for(i = 0; i < 9; ++i)
	{
		for(j = 0; j < 9; ++j)
		{
			for(k = j + 1; k < 9; ++k)
			{
				/* Row check */
				if(twoD[i][j] != '.' && twoD[i][k] != '.')
				{
					if(twoD[i][j] == twoD[i][k])
					{
						errored = 1;
					}
				}
				
				/* Column check */
				if(twoD[j][i] != '.' && twoD[k][i] != '.')
				{
					if(twoD[j][i] == twoD[k][i])
					{
						errored = 1;
					}
				
				}

				/* 3x3 check */
				/* TODO: CHECK 3x3 DUPLICATES */
				/* Let's say i is row and j is column and i is 2 and column is 2,
				this puts i, j in quadrant 1 but it needs to search 2 to left
				and 2 up based on i % 3 and j % 3 */

			}
		}

	}

	

	if(errored == 1)
	{
		printf("Error - Dupicate row or column #");
	}
	else
	{	
		/* Solve the sudoku puzzle */
		for(i = 0; i < 81; i++)
		{
			printf("%c", oneD[i]);
		}
	}
}




int main(void){

	/* Character array for holding the 81 elements from the input */
	char initial[81];
	
	int c, errored, i, j, charsPerLine, eof;

	errored = i = j = charsPerLine = eof = 0;

	while(eof == 0)
	{
		/* Processing characters */
		while((c = getchar()) != '\n' && c != EOF)
		{
			
			/* Printing each char regardless of it throws an error */
			printf("%c", c);

			/* Error if there is more than 81 chars per line */
			if (charsPerLine > 80)
			{
				errored = 1;
			}

			/* If errored is false then process the chars into the array */
			if (errored == 0)
			{
				/* Checking if the chars are between 0 and 9 or a . */
				if((c == '.') || (c >= '0' && c <= '9'))
				{
					/* Filling the array with each character */
					initial[charsPerLine] = c;	
					++charsPerLine;
				}
				else
				{
					errored = 1;
				}
			}


		}

		/* If end of file was hit, then exit program */		
		/* TODO: Get rid of this line */
		if (c == EOF)
		{
			eof = 1;
			break;
		}

		/* If the line has less than 81 characters then throw error */
		if(charsPerLine <= 80)
		{
			errored = 1;
		}
		
		if(errored == 1)
		{
			printf("\nError - Line length or char types\n\n");
		}
		else
		{
			printf("\n");	
			processArray(initial);
			printf("\n\n");
		}
		
		if(c == EOF)
		{
			eof = 1;
		}

		/* Resetting variables to continue onto the next line */
		charsPerLine = 0;
		errored = 0;
		
		/* Clearing character array */
		memset(&initial[0], 0, sizeof(initial));

	} 

	return 0;
}





