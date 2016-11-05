// Anthony Galczak
// agalczak@cnm.edu
// Driver.cpp

#include <iostream>
#include <string>
#include "Functions.h"
#include "PigLatin.h"
using namespace std;

int main()
{
	// Displaying header
	Header();

	// Declaring variables
	string userPhrase, newPhrase;

	// Starting a do while loop in case the user wants more pig latin translations
	char lpProg = 'y';
	do
	{
		//MAIN
		PigLatin pig;

		// Asking user for a phrase to convert to pig latin
		cout << "Enter a phrase that you would like translated to pig latin.\nNo funny characters like punctuation in the phrase please.\n";
		getline(cin, userPhrase);

		// Setting the phrase into the piglatin object
		pig.SetSentence(userPhrase);

		// If there isn't any funny symbols in the phrase then isvalid will be true, then return the translated phrase to the user
		if (pig.IsValid() == true)
		{
			cout << "Your phrase is valid.\n";
			newPhrase = pig.GetTranslation();
			cout << "Your translated pig latin phrase is:\n\n" << newPhrase << "\n\n\n";
		}
		else
		{
			cout << "Your phrase is not valid. Please try again.\n";
		}

		// Asking if user wants to do another
		cout << "Would you like to translate another phrase?(y/n)\n";
		
		// Gathering the char for the do while loop
		cin >> lpProg;
		cin.ignore();
	} while (lpProg == 'y');

	cout << "Thanks for using my Pig Latin Translation software!\n";
}