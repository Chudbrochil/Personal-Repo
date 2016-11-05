// Anthony Galczak
// agalczak@cnm.edu
// Functions.cpp

#include <iostream>
#include <string>
#include "Functions.h"
// For reverse
#include <algorithm>
using namespace std;

// Header function
void Header()
{
	cout << "Anthony Galczak\nExercise 1\nThis program will be used to ";
	cout << "reverse a string object.\n\n";
}

// Reversing function
void ReverseIt(string &userStr, string &origStr)
{
	// Displaying original phrase to user
	cout << "Your phrase is " << userStr << "\n";

	// Saving the original string
	origStr = userStr;

	// Reversing the string
	reverse(begin(userStr), end(userStr));

	// Outputting the info to the user
	cout << "Your original phrase is \"" << origStr << "\"\n";
	cout << "Your reversed phrase is \"" << userStr << "\"\n";
}