#include "PigLatin.h"
#include <string>
#include <locale>
#include <sstream>

using namespace std;


PigLatin::PigLatin()
{

}

PigLatin::PigLatin(string phrase)
{
	needTrans = phrase;
	Validate();
}

bool PigLatin::IsValid()
{
	if (phraseValid == true) { return true; }
	if (phraseValid == false){ return false; }
}

void PigLatin::SetSentence(string phrase)
{
	needTrans = phrase;
	Validate();

	// If the validation comes back good then proceed with the translation
	if (phraseValid == true) { Translate(); }
}

string PigLatin::GetTranslation()
{
	return translated;
}

void PigLatin::Validate()
{
	char phraseArray[100];
	
	// Converting the incoming translation phrase into a character array called phraseArray
	strcpy(phraseArray, needTrans.c_str());

	// -1 on length of phrase array so that it doesn't include the final character (period?)
	for (int i = 0; i < (strlen(phraseArray) - 1); ++i)
	{
		// IsAlpha does not work on spaces, therefore i will first convert all the spaces to a's
		if (phraseArray[i] == ' ')
		{
			phraseArray[i] = 'a';
		}

		// Finds out if each element of the array is an alphanumeric character or not (includes spaces via the if statement before this)
		if (isalpha(phraseArray[i]))
		{
			phraseValid = true;
		}
		else
		{
			// If a character isn't alphanumeric then drop out
			phraseValid = false;
			return;
		}
	}
}

void PigLatin::Translate()
{
	// String array to hold each individual word of the user's phrase
	string wordArray[40];

	int i = 0, howMany = 0;
	stringstream ssin(needTrans);
	
	// While the stringstream is still streaming capture each word into an element of wordarray
	while (ssin.good() && i < 40)
	{
		ssin >> wordArray[i];
		++i;
		++howMany;
	}

	// Shifting the first letter to the end and adding ay to each word
	for (int i = 0; i < howMany; ++i)
	{
		// Turning the element of wordarray into a string
		string lpString = wordArray[i];

		// Capturing the element (word) of wordarray and changing it into a character array
		char charArray[20];
		strcpy(charArray, lpString.c_str());
		
		// Capturing last character of the character array (word)
		int charArraySize = strlen(charArray);
		char firstChar = charArray[0];

		// Conditional for if the word ends with a period (Could only be the last word)
		bool hasPeriod = false;

		// The period will be the 2nd to last character due to the null terminator
		if (charArray[charArraySize - 1] == '.')
		{
			charArray[charArraySize - 1] = NULL;
			hasPeriod = true;
		}

		// Shifting each character in the array over to the left one to remove the first character
		for (int j = 0; j < charArraySize; ++j)
		{
			charArray[0 + j] = charArray[1 + j];
		}

		// New size should be calculated because we have effectively deleted one character from the char array
		charArraySize = strlen(charArray);

		// Adding the first character and 'ay' to the end of the character array
		charArray[charArraySize] = firstChar;
		charArray[charArraySize + 1] = 'a';
		charArray[charArraySize + 2] = 'y';

		// If the word ended with a period previously then add this period to the end of the word
		if (hasPeriod == true)
		{
			charArray[charArraySize + 3] = '.';
			charArray[charArraySize + 4] = '\0';
			hasPeriod = false;
		}
		else
		{
			charArray[charArraySize + 3] = '\0'; // null terminator
		}

		// Making a string containing the characters of the chararray
		string word(charArray);

		// Assigning this string into the wordarray
		wordArray[i] = word;
	}

	// Stringstream to hold the giant string for the end translation
	stringstream transed;

	// Now that I have a word array that contains all of the words modified to pig latin I need to concat them into a stringstream
	for (int i = 0; i < howMany; ++i)
	{
		transed << wordArray[i] << " ";
	}

	// Converting my computed stringstream into a usable/outputtable string
	translated = transed.str();
}