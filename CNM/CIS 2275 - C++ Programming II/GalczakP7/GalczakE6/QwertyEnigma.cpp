#include "QwertyEnigma.h"


QwertyEnigma::QwertyEnigma()
{

}

void QwertyEnigma::SetMessage(string m, int k)
{
	// Setting the message into the enigma base class
	Enigma::SetMessage(m, k);

	// Running the qwerty enigma encode
	Encode();
}

void QwertyEnigma::SetMessage(string m)
{
	// Setting the enigma base class with the message, allowing it to use the key as 1 and then running qwerty encode
	Enigma::SetMessage(m);
	Encode();
}

void QwertyEnigma::SetCodedMessage(string m, int k)
{
	// Assigning variables and then running decode
	codedMessage = m;
	key = k;
	Decode();
	Enigma::Decode();
}

void QwertyEnigma::Encode()
{
	// Declaring a new character array and assigning codedMessage into it
	char qwertyMessageArray[200];
	strcpy_s(qwertyMessageArray, 200, codedMessage.c_str());

	for (int i = 0; i < int(strlen(qwertyMessageArray)); ++i)
	{
		// It isn't necessary to convert numbers, punctuation, or the letter P so those are not included in these statements
		// Capital letter conversions - 1st column
		if (qwertyMessageArray[i] == 'Q') { qwertyMessageArray[i] = 'A'; }
		else if (qwertyMessageArray[i] == 'A') { qwertyMessageArray[i] = 'Z'; }
		else if (qwertyMessageArray[i] == 'Z') { qwertyMessageArray[i] = 'Q'; }
		// 2nd column
		else if (qwertyMessageArray[i] == 'W') { qwertyMessageArray[i] = 'S'; }
		else if (qwertyMessageArray[i] == 'S') { qwertyMessageArray[i] = 'X'; }
		else if (qwertyMessageArray[i] == 'X') { qwertyMessageArray[i] = 'W'; }
		// 3rd column
		else if (qwertyMessageArray[i] == 'E') { qwertyMessageArray[i] = 'D'; }
		else if (qwertyMessageArray[i] == 'D') { qwertyMessageArray[i] = 'C'; }
		else if (qwertyMessageArray[i] == 'C') { qwertyMessageArray[i] = 'E'; }
		// 4th column
		else if (qwertyMessageArray[i] == 'R') { qwertyMessageArray[i] = 'F'; }
		else if (qwertyMessageArray[i] == 'F') { qwertyMessageArray[i] = 'V'; }
		else if (qwertyMessageArray[i] == 'V') { qwertyMessageArray[i] = 'R'; }
		// 5th column
		else if (qwertyMessageArray[i] == 'T') { qwertyMessageArray[i] = 'G'; }
		else if (qwertyMessageArray[i] == 'G') { qwertyMessageArray[i] = 'B'; }
		else if (qwertyMessageArray[i] == 'B') { qwertyMessageArray[i] = 'T'; }
		// 6th column
		else if (qwertyMessageArray[i] == 'Y') { qwertyMessageArray[i] = 'H'; }
		else if (qwertyMessageArray[i] == 'H') { qwertyMessageArray[i] = 'N'; }
		else if (qwertyMessageArray[i] == 'N') { qwertyMessageArray[i] = 'Y'; }
		// 7th column
		else if (qwertyMessageArray[i] == 'U') { qwertyMessageArray[i] = 'J'; }
		else if (qwertyMessageArray[i] == 'J') { qwertyMessageArray[i] = 'M'; }
		else if (qwertyMessageArray[i] == 'M') { qwertyMessageArray[i] = 'U'; }
		// 8th column
		else if (qwertyMessageArray[i] == 'I') { qwertyMessageArray[i] = 'K'; }
		else if (qwertyMessageArray[i] == 'K') { qwertyMessageArray[i] = 'I'; }
		// 9th column
		else if (qwertyMessageArray[i] == 'O') { qwertyMessageArray[i] = 'L'; }
		else if (qwertyMessageArray[i] == 'L') { qwertyMessageArray[i] = 'O'; }

		// Lowercase letter conversions
		else if (qwertyMessageArray[i] == 'q') { qwertyMessageArray[i] = 'a'; }
		else if (qwertyMessageArray[i] == 'a') { qwertyMessageArray[i] = 'z'; }
		else if (qwertyMessageArray[i] == 'z') { qwertyMessageArray[i] = 'q'; }
		// 2nd column
		else if (qwertyMessageArray[i] == 'w') { qwertyMessageArray[i] = 's'; }
		else if (qwertyMessageArray[i] == 's') { qwertyMessageArray[i] = 'x'; }
		else if (qwertyMessageArray[i] == 'x') { qwertyMessageArray[i] = 'w'; }
		// 3rd column
		else if (qwertyMessageArray[i] == 'e') { qwertyMessageArray[i] = 'd'; }
		else if (qwertyMessageArray[i] == 'd') { qwertyMessageArray[i] = 'c'; }
		else if (qwertyMessageArray[i] == 'c') { qwertyMessageArray[i] = 'e'; }
		// 4th column
		else if (qwertyMessageArray[i] == 'r') { qwertyMessageArray[i] = 'f'; }
		else if (qwertyMessageArray[i] == 'f') { qwertyMessageArray[i] = 'v'; }
		else if (qwertyMessageArray[i] == 'v') { qwertyMessageArray[i] = 'r'; }
		// 5th column
		else if (qwertyMessageArray[i] == 't') { qwertyMessageArray[i] = 'g'; }
		else if (qwertyMessageArray[i] == 'g') { qwertyMessageArray[i] = 'b'; }
		else if (qwertyMessageArray[i] == 'b') { qwertyMessageArray[i] = 't'; }
		// 6th column
		else if (qwertyMessageArray[i] == 'y') { qwertyMessageArray[i] = 'h'; }
		else if (qwertyMessageArray[i] == 'h') { qwertyMessageArray[i] = 'n'; }
		else if (qwertyMessageArray[i] == 'n') { qwertyMessageArray[i] = 'y'; }
		// 7th column
		else if (qwertyMessageArray[i] == 'u') { qwertyMessageArray[i] = 'j'; }
		else if (qwertyMessageArray[i] == 'j') { qwertyMessageArray[i] = 'm'; }
		else if (qwertyMessageArray[i] == 'm') { qwertyMessageArray[i] = 'u'; }
		// 8th column
		else if (qwertyMessageArray[i] == 'i') { qwertyMessageArray[i] = 'k'; }
		else if (qwertyMessageArray[i] == 'k') { qwertyMessageArray[i] = 'i'; }
		// 9th column
		else if (qwertyMessageArray[i] == 'o') { qwertyMessageArray[i] = 'l'; }
		else if (qwertyMessageArray[i] == 'l') { qwertyMessageArray[i] = 'o'; }
	}

	// Converting the character array into the class variable string codedMessage
	string qwertyNewMessage(qwertyMessageArray);
	codedMessage = qwertyNewMessage;
}

void QwertyEnigma::Decode()
{
	// Declaring a new character array and assigning codedMessage into it
	char decodeQwertyMessageArray[200];
	strcpy_s(decodeQwertyMessageArray, 200, codedMessage.c_str());

	for (int i = 0; i < int(strlen(decodeQwertyMessageArray)); ++i)
	{
		// It isn't necessary to convert numbers, punctuation, or the letter P so those are not included in these statements
		// Capital letter conversions - 1st column
		if (decodeQwertyMessageArray[i] == 'A') { decodeQwertyMessageArray[i] = 'Q'; }
		else if (decodeQwertyMessageArray[i] == 'Z') { decodeQwertyMessageArray[i] = 'A'; }
		else if (decodeQwertyMessageArray[i] == 'Q') { decodeQwertyMessageArray[i] = 'Z'; }
		// 2nd column
		else if (decodeQwertyMessageArray[i] == 'S') { decodeQwertyMessageArray[i] = 'W'; }
		else if (decodeQwertyMessageArray[i] == 'X') { decodeQwertyMessageArray[i] = 'S'; }
		else if (decodeQwertyMessageArray[i] == 'W') { decodeQwertyMessageArray[i] = 'X'; }
		// 3rd column
		else if (decodeQwertyMessageArray[i] == 'D') { decodeQwertyMessageArray[i] = 'E'; }
		else if (decodeQwertyMessageArray[i] == 'C') { decodeQwertyMessageArray[i] = 'D'; }
		else if (decodeQwertyMessageArray[i] == 'E') { decodeQwertyMessageArray[i] = 'C'; }
		// 4th column
		else if (decodeQwertyMessageArray[i] == 'F') { decodeQwertyMessageArray[i] = 'R'; }
		else if (decodeQwertyMessageArray[i] == 'V') { decodeQwertyMessageArray[i] = 'F'; }
		else if (decodeQwertyMessageArray[i] == 'R') { decodeQwertyMessageArray[i] = 'V'; }
		// 5th column
		else if (decodeQwertyMessageArray[i] == 'G') { decodeQwertyMessageArray[i] = 'T'; }
		else if (decodeQwertyMessageArray[i] == 'B') { decodeQwertyMessageArray[i] = 'G'; }
		else if (decodeQwertyMessageArray[i] == 'T') { decodeQwertyMessageArray[i] = 'B'; }
		// 6th column
		else if (decodeQwertyMessageArray[i] == 'H') { decodeQwertyMessageArray[i] = 'Y'; }
		else if (decodeQwertyMessageArray[i] == 'N') { decodeQwertyMessageArray[i] = 'H'; }
		else if (decodeQwertyMessageArray[i] == 'Y') { decodeQwertyMessageArray[i] = 'N'; }
		// 7th column
		else if (decodeQwertyMessageArray[i] == 'J') { decodeQwertyMessageArray[i] = 'U'; }
		else if (decodeQwertyMessageArray[i] == 'M') { decodeQwertyMessageArray[i] = 'J'; }
		else if (decodeQwertyMessageArray[i] == 'U') { decodeQwertyMessageArray[i] = 'M'; }
		// 8th column
		else if (decodeQwertyMessageArray[i] == 'K') { decodeQwertyMessageArray[i] = 'I'; }
		else if (decodeQwertyMessageArray[i] == 'I') { decodeQwertyMessageArray[i] = 'K'; }
		// 9th column
		else if (decodeQwertyMessageArray[i] == 'L') { decodeQwertyMessageArray[i] = 'O'; }
		else if (decodeQwertyMessageArray[i] == 'O') { decodeQwertyMessageArray[i] = 'L'; }

		// Lowercase letter conversions
		else if (decodeQwertyMessageArray[i] == 'a') { decodeQwertyMessageArray[i] = 'q'; }
		else if (decodeQwertyMessageArray[i] == 'z') { decodeQwertyMessageArray[i] = 'a'; }
		else if (decodeQwertyMessageArray[i] == 'q') { decodeQwertyMessageArray[i] = 'z'; }
		// 2nd column
		else if (decodeQwertyMessageArray[i] == 's') { decodeQwertyMessageArray[i] = 'w'; }
		else if (decodeQwertyMessageArray[i] == 'x') { decodeQwertyMessageArray[i] = 's'; }
		else if (decodeQwertyMessageArray[i] == 'w') { decodeQwertyMessageArray[i] = 'x'; }
		// 3rd column
		else if (decodeQwertyMessageArray[i] == 'd') { decodeQwertyMessageArray[i] = 'e'; }
		else if (decodeQwertyMessageArray[i] == 'c') { decodeQwertyMessageArray[i] = 'd'; }
		else if (decodeQwertyMessageArray[i] == 'e') { decodeQwertyMessageArray[i] = 'c'; }
		// 4th column
		else if (decodeQwertyMessageArray[i] == 'f') { decodeQwertyMessageArray[i] = 'r'; }
		else if (decodeQwertyMessageArray[i] == 'v') { decodeQwertyMessageArray[i] = 'f'; }
		else if (decodeQwertyMessageArray[i] == 'r') { decodeQwertyMessageArray[i] = 'v'; }
		// 5th column
		else if (decodeQwertyMessageArray[i] == 'g') { decodeQwertyMessageArray[i] = 't'; }
		else if (decodeQwertyMessageArray[i] == 'b') { decodeQwertyMessageArray[i] = 'g'; }
		else if (decodeQwertyMessageArray[i] == 't') { decodeQwertyMessageArray[i] = 'b'; }
		// 6th column
		else if (decodeQwertyMessageArray[i] == 'h') { decodeQwertyMessageArray[i] = 'y'; }
		else if (decodeQwertyMessageArray[i] == 'n') { decodeQwertyMessageArray[i] = 'h'; }
		else if (decodeQwertyMessageArray[i] == 'y') { decodeQwertyMessageArray[i] = 'n'; }
		// 7th column
		else if (decodeQwertyMessageArray[i] == 'j') { decodeQwertyMessageArray[i] = 'u'; }
		else if (decodeQwertyMessageArray[i] == 'm') { decodeQwertyMessageArray[i] = 'j'; }
		else if (decodeQwertyMessageArray[i] == 'u') { decodeQwertyMessageArray[i] = 'm'; }
		// 8th column
		else if (decodeQwertyMessageArray[i] == 'k') { decodeQwertyMessageArray[i] = 'i'; }
		else if (decodeQwertyMessageArray[i] == 'i') { decodeQwertyMessageArray[i] = 'k'; }
		// 9th column
		else if (decodeQwertyMessageArray[i] == 'l') { decodeQwertyMessageArray[i] = 'o'; }
		else if (decodeQwertyMessageArray[i] == 'o') { decodeQwertyMessageArray[i] = 'l'; }
	}

	// Converting the character array into the class variable string codedMessage
	string decodeQwertyNewMessage(decodeQwertyMessageArray);
	codedMessage = decodeQwertyNewMessage;
}