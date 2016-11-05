// Anthony Galczak
// agalczak@cnm.edu
// Enigma.cpp

#include <string>
#include <time.h>
#include <stdlib.h>
#include "Enigma.h"
using namespace std;

Enigma::Enigma()
{
	// initialize variables
	int randNum = 0;

	// Seed a random object to be used in the other methods, using the time to get close to a real random number
	srand(time(NULL));

	// Initializing class variables
	message = "";
	codedMessage = "";
}

void Enigma::Encode()
{
	// Creating a character array to store each character of the original message and eventually the new character
	char messageArray[200];
	
	// Converting the message into a character array
	strcpy(messageArray, message.c_str());

	// Converting the chars into ASCII, adding the key to the ASCII values and then repopulating the new character array
	for (int i = 0; i < strlen(messageArray); ++i)
	{
		// Converting the individual letter into it's ascii value
		int asciiValue = int(messageArray[i]);

		// Adding the key to the ascii value
		asciiValue += key;

		// Changing the ascii value for if it goes over value of 126
		if (asciiValue > 126)
		{
			// There are 95 values between 32 and 126
			asciiValue -= 95;
		}

		// Assigning the asciivalue as a char back into the array
		messageArray[i] = char(asciiValue);
	}

	// Converting the character array into the class variable string codedMessage
	string newMessage(messageArray);
	codedMessage = newMessage;
}

void Enigma::Decode()
{
	// New array to hold the encoded message, waiting to convert to a regular message
	char messageArray[200];

	// Changing my encoded message into a character array
	strcpy(messageArray, codedMessage.c_str());

	// Converting the chars into ASCII, adding the key to the ASCII values and then repopulating the new character array
	for (int i = 0; i < strlen(messageArray); ++i)
	{
		// Converting the individual letter into it's ascii value
		int asciiValue = int(messageArray[i]);

		// Subtracting the key to the ascii value (As we are decoding the message now...)
		asciiValue -= key;

		// Changing the ascii value if it goes under 32
		if (asciiValue < 32)
		{
			// There are 95 values between 32 and 126
			asciiValue += 95;
		}

		// Assigning the asciivalue as a char back into the array
		messageArray[i] = char(asciiValue);
	}

	// Converting the character array into the class variable string codedMessage
	string newMessage(messageArray);
	message = newMessage;

}

void Enigma::SetMessage(string mess, int k)
{
	// Check that key is between 1-50, if not set key to 1
	if (k > 50 || k < 1)
	{
		key = 1;
	}

	// Setting class variables
	message = mess;
	key = k;

	// Encoding the message we just received with the key
	Encode();
}

void Enigma::SetMessage(string mess)
{
	// Setting the class variable message
	message = mess;

	// Create random key between 1 and 50
	key = rand() % 50 + 1;

	// Encoding the message we just received with the random key
	Encode();
}

void Enigma::SetCodedMessage(string codedM, int k)
{
	// Setting the class variables
	codedMessage = codedM;
	key = k;

	// Decode the message
	Decode();
}