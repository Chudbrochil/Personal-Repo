#include "ShiftyEnigma.h"

#include <sstream>


ShiftyEnigma::ShiftyEnigma()
{

}

void ShiftyEnigma::SetMessage(string m, int k)
{
	// Setting the message into the enigma base class
	Enigma::SetMessage(m, k);

	// Running the shifty enigma encode
	Encode();
}

void ShiftyEnigma::SetMessage(string m)
{
	// Setting the enigma base class with the message and then running the shifty encode
	Enigma::SetMessage(m);
	Encode();
}

void ShiftyEnigma::SetCodedMessage(string m, int k)
{
	// Assigning variables and then running decode
	codedMessage = m;
	key = k;
	Decode();
	Enigma::Decode();
}

void ShiftyEnigma::Encode()
{
	// Declaring a character array and assigning codedMessage into it
	char shiftyMessageArray[200];
	strcpy_s(shiftyMessageArray, 200, codedMessage.c_str());

	// Declaring a new character array to hold the converted chars
	char shiftedMessageArray[600];

	for (int k = 0; k < 600; ++k)
	{
		shiftedMessageArray[k] = NULL;
	}

	// Converting each character into ascii so I can run the shifty algorithm
	for (int i = 0; i < int(strlen(shiftyMessageArray)); ++i)
	{
		// Changing the character to it's ascii value
		int shiftyAsciiValue = int(shiftyMessageArray[i]);

		// Subtracting the value from 1000
		shiftyAsciiValue = 1000 - shiftyAsciiValue;

		// Converting the 3-digit int to a string
		string shiftyValues = to_string(shiftyAsciiValue);

		// Converting the 3-digit string with a null terminator into a character array for manipulation
		char shiftyConvert[4];
		strcpy_s(shiftyConvert, 10, shiftyValues.c_str());

		// Identifying each number in the character array for conversion to !@#$%^&*()
		for (int j = 0; j < int(strlen(shiftyConvert)); ++j)
		{
			if (shiftyConvert[j] == '1') { shiftyConvert[j] = '!'; }
			else if (shiftyConvert[j] == '2') { shiftyConvert[j] = '@'; }
			else if (shiftyConvert[j] == '3') { shiftyConvert[j] = '#'; }
			else if (shiftyConvert[j] == '4') { shiftyConvert[j] = '$'; }
			else if (shiftyConvert[j] == '5') { shiftyConvert[j] = '%'; }
			else if (shiftyConvert[j] == '6') { shiftyConvert[j] = '^'; }
			else if (shiftyConvert[j] == '7') { shiftyConvert[j] = '&'; }
			else if (shiftyConvert[j] == '8') { shiftyConvert[j] = '*'; }
			else if (shiftyConvert[j] == '9') { shiftyConvert[j] = '('; }
			else if (shiftyConvert[j] == '0') { shiftyConvert[j] = ')'; }
		}

		for (int l = 0; l < int(strlen(shiftyConvert)); ++l)
		{
			// Assigning elements 1 2 3 into the shifty message array, doing it based
			// on length of shiftymessagearray so I'm not overwriting previously values
			int derp = int(strlen(shiftedMessageArray));
			shiftedMessageArray[strlen(shiftedMessageArray)] = shiftyConvert[l];
		}

	}

	// Assigning this giant character array into a string and then assigning it as coded message
	string shiftyNewMessage(shiftedMessageArray);
	codedMessage = shiftyNewMessage;
}

void ShiftyEnigma::Decode()
{
	// Declaring a character array and assigning codedMessage into it
	char decodeShiftyMessageArray[600];
	strcpy_s(decodeShiftyMessageArray, 600, codedMessage.c_str());

	// Declaring a new character array to hold the converted chars
	char decodeShiftedMessageArray[200];

	for (int k = 0; k < 200; ++k)
	{
		decodeShiftedMessageArray[k] = NULL;
	}

	// Converting each character into numbers for further manipulation
	for (int j = 0; j < int(strlen(decodeShiftyMessageArray)); ++j)
	{
		// Converting the !@#$%^&*() into proper ints
		if (decodeShiftyMessageArray[j] == '!') { decodeShiftyMessageArray[j] = '1'; }
		else if (decodeShiftyMessageArray[j] == '@') { decodeShiftyMessageArray[j] = '2'; }
		else if (decodeShiftyMessageArray[j] == '#') { decodeShiftyMessageArray[j] = '3'; }
		else if (decodeShiftyMessageArray[j] == '$') { decodeShiftyMessageArray[j] = '4'; }
		else if (decodeShiftyMessageArray[j] == '%') { decodeShiftyMessageArray[j] = '5'; }
		else if (decodeShiftyMessageArray[j] == '^') { decodeShiftyMessageArray[j] = '6'; }
		else if (decodeShiftyMessageArray[j] == '&') { decodeShiftyMessageArray[j] = '7'; }
		else if (decodeShiftyMessageArray[j] == '*') { decodeShiftyMessageArray[j] = '8'; }
		else if (decodeShiftyMessageArray[j] == '(') { decodeShiftyMessageArray[j] = '9'; }
		else if (decodeShiftyMessageArray[j] == ')') { decodeShiftyMessageArray[j] = '0'; }
	}

	// Declaring a new int so that I can iterate through my 200 char array of assigning ascii chars
	int x = 0;

	for (int i = 0; i < int(strlen(decodeShiftyMessageArray)); ++i)
	{
		// Declaring a stringstream to hold my integer from the next 3 characters (numbers) in the decodeShiftyMessageArray
		stringstream ss;
		int decodedInt;
		ss << decodeShiftyMessageArray[i] << decodeShiftyMessageArray[i + 1] << decodeShiftyMessageArray[i + 2];
		ss >> decodedInt;

		// Running math on my new int to get it back to a proper ascii character
		decodedInt = 1000 - decodedInt;
		decodeShiftedMessageArray[x] = (char)decodedInt;

		// Iterating the x so that i can go sequentially in the shifted message array
		x++;

		// Skipping 2 characters as we need to go in sets of 3s
		i = i + 2;

	}

	// Assigning my ascii values back into codedmessage so that the enigma base class can key-shift it back to the regular message
	string decodeShiftyNewMessage(decodeShiftedMessageArray);
	codedMessage = decodeShiftyNewMessage;
}

