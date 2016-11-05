// Anthony Galczak
// agalczak@cnm.edu
// Functions.cpp

#include <iostream>
#include "Functions.h"
using namespace std;

// Header display function
void Header()
{
	cout << "Anthony Galczak\nProgram 2\nThis program will calculate the trajectory of a shot cannonball using classes.\n\n";
}

void AskForInitialValues(double &vel0, double &angle)
{
	cout << "What is the velocity of your cannon?(In meters/sec)\n";
	cin >> vel0;
	cout << "What is the angle of your cannon?\n";
	cin >> angle;
}