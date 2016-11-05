#include "PrimeShiftEnigma.h"


PrimeShiftEnigma::PrimeShiftEnigma()
{
	int tempPrimes [] = { 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113 };

	// Assigning the values of the primes into the primes array
	for (int i = 0; i < 19; ++i)
	{
		primes[i] = tempPrimes[i];
	}
}

void PrimeShiftEnigma::SetMessage(string m, int k)
{
	// Setting the message and key received into the enigma base class
	Enigma::SetMessage(m, k);

	// Running the prime shift encode in addition to get the new coded message
	Encode();
}


void PrimeShiftEnigma::SetMessage(string m)
{
	// Setting the enigma base class with the message but allowing the key to be 1 from base enigma
	Enigma::SetMessage(m);
	Encode();
}


void PrimeShiftEnigma::SetCodedMessage(string m, int k)
{
	// Assigning the values first into the class, running the decodes in backwards order
	// as that is how we decode the message
	codedMessage = m;
	key = k;
	Decode();
	Enigma::Decode();
}

void PrimeShiftEnigma::Encode()
{
	// Declaring a character array and assigning codedMessage into it
	char primeMessageArray[200];
	strcpy_s(primeMessageArray, 200, codedMessage.c_str());

	// Converting the characters back to ascii to run the prime shift algorithm
	for (int i = 0; i < int(strlen(primeMessageArray)); ++i)
	{
		// Converting the charaters into ascii values
		int primeAsciiValue = int(primeMessageArray[i]);

		// Checking if an item has been shifted
		bool shifted = false;
		
		// For each item in the primes array
		for (int j = 0; j < 19; ++j)
		{
			// If the converted character is the same as the one of the looped prime numbers
			if (primeAsciiValue == primes[j] && shifted == false)
			{
				// For use in case the number needs to wrap the array of primes
				int k = -1;

				// Finding out if the key is prime, if so shift to next prime from the one that was found
				if (IsPrime(key) == true)
				{
					// if j is 18 then assign 0 instead of 19 as 19 doesn't exist
					if (j == 18) { primeAsciiValue = primes[k + 1]; }
					else { primeAsciiValue = primes[j + 1]; }
					
				}

				// If the key is even shift 2 primes
				else if ((key % 2) == 0)
				{
					// If j is 17 or 18 it needs to be wrapped around
					if (j == 18) { primeAsciiValue = primes[k + 2]; }
					else if (j == 17) { primeAsciiValue = primes[k + 1]; }
					else { primeAsciiValue = primes[j + 2]; }
				}
				// If the key isnt' even or prime then shift 3 primes up
				else
				{
					if (j == 18) { primeAsciiValue = primes[k + 3]; }
					else if (j == 17) { primeAsciiValue = primes[k + 2]; }
					else if (j == 16) { primeAsciiValue = primes[k + 1]; }
					else { primeAsciiValue = primes[j + 3]; }
				}

				// Changing shifted to true so that the number isn't modified again through this list
				shifted = true;
			}
			// No else statement required as if it isn't prime we do nothing to the value
		}

		// Assigning the letter back in
		primeMessageArray[i] = char(primeAsciiValue);
	}

	// Converting the character array into the class variable string codedMessage
	string primeNewMessage(primeMessageArray);
	codedMessage = primeNewMessage;
}

void PrimeShiftEnigma::Decode()
{
	// Declaring a character array and assigning codedMessage into it
	char primeMessageArray[200];
	strcpy_s(primeMessageArray, 200, codedMessage.c_str());

	// Converting the characters back to ascii to run the prime shift algorithm
	for (int i = 0; i < int(strlen(primeMessageArray)); ++i)
	{
		// Converting the charaters into ascii values
		int primeAsciiValue = int(primeMessageArray[i]);

		// Checking if an item has been shifted
		bool shifted = false;

		// For each item in the primes array
		for (int j = 0; j < 19; ++j)
		{
			// If the converted character is the same as the one of the looped prime numbers
			if (primeAsciiValue == primes[j] && shifted == false)
			{
				// For use in case the number needs to wrap the array of primes
				int k = 19;

				// Finding out if the key is prime, if so shift to next prime from the one that was found
				if (IsPrime(key) == true)
				{
					// if j is 18 then assign 0 instead of 19 as 19 doesn't exist
					if (j == 0) { primeAsciiValue = primes[k - 1]; }
					else { primeAsciiValue = primes[j - 1]; }

				}

				// If the key is even shift 2 primes
				else if ((key % 2) == 0)
				{
					// If j is 17 or 18 it needs to be wrapped around
					if (j == 0) { primeAsciiValue = primes[k - 2]; }
					else if (j == 1) { primeAsciiValue = primes[k - 1]; }
					else { primeAsciiValue = primes[j - 2]; }
				}
				// If the key isnt' even or prime then shift 3 primes up
				else
				{
					if (j == 0) { primeAsciiValue = primes[k - 3]; }
					else if (j == 1) { primeAsciiValue = primes[k - 2]; }
					else if (j == 2) { primeAsciiValue = primes[k - 1]; }
					else { primeAsciiValue = primes[j - 3]; }
				}

				// Changing shifted to true so that the number isn't modified again through this list
				shifted = true;
			}
			// No else statement required as if it isn't prime we do nothing to the value
		}

		// Assigning the letter back in
		primeMessageArray[i] = char(primeAsciiValue);
	}

	// Converting the character array into the class variable string codedMessage
	string primeNewMessage(primeMessageArray);
	codedMessage = primeNewMessage;
}

bool PrimeShiftEnigma::IsPrime(int num)
{
	// If the inputted number is found then found will = true
	bool found = false;
	found = find(begin(primes), end(primes), num) != end(primes);

	if (num == 2 || num == 3 || num == 5 || num == 7 || num == 11 || num == 13 || num == 17 || num == 19 || num == 23 || num == 29 || num == 31)
	{
		found = true;
	}

	return found;
}