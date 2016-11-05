// Anthony Galczak
// agalczak@cnm.edu
// Driver.cpp

#include <iostream>
#include <string>
using namespace std;

// Including the header file
#include "Functions.h"

int main()
{
	// Displaying header
	Header();

	// Declaring Variables
	string userStr;
	string origStr;

	// Starting a do while loop
	char lpProg;
	lpProg = 'y';
	do
	{

	// Obtaining the user's string
		cout << "What is the phrase that you would like to reverse?\n";
		getline(cin, userStr);

	// Reversing the phrase/string
		ReverseIt(userStr, origStr);

	// Asking if they want to do another
		cout << "Would you like to reverse another phrase? (y/n)\n";
		cin >> lpProg;
		cin.ignore();

	// Ending do while loop
	} while (lpProg == 'y');

	// Ending Main
	cout << "Thanks for using my reversing program!\n";
	return 0;
}