// Anthony Galczak
// agalczak@cnm.edu
// Functions.cpp

#include <iostream>
#include "Functions.h"
using namespace std;

// Header display function
void Header()
{
	cout << "Anthony Galczak\nProgram 3\nThis program will find a user-defined first or last name and display it on screen using a menu.\n\n";
}

// My own custom ToUpper function, the other one isn't very good for strings.
string ToUpper(string lower)
{
	for (int i = 0; i < lower.length(); i++)
	{
		lower[i] = toupper(lower[i]);
	}

	return lower;
}