// Anthony Galczak
// agalczak@cnm.edu
// GalczakP4Functions.cpp

// For cins/couts
#include <iostream>
// For Strings
#include <string>
// Header
#include "GalczakP4.h"
// Declaring namespace
using namespace std;
// Declaring pi
const double pi = 3.141592653589793;

// Displaying Header
void WriteHeader()
{
	cout << "Anthony Galczak \n";
	cout << "Program 4 \n";
	cout << "This program will calculate how many pebbles a crow \n";
	cout << "needs to drop in a pitcher to drink some water. \n\n";
}

// Asking Pitcher Radius and Inserting into Variable
double AskPitcherRadius()
{
	double pitcherRadius;
	cout << "What is the radius of the pitcher? (In inches) \n";
	cin >> pitcherRadius;
	return pitcherRadius;
}

// Asking Pitcher Height and Inserting into Variable
double AskPitcherHeight()
{
	double pitcherHeight;
	cout << "What is the height of the pitcher? (In inches) \n";
	cin >> pitcherHeight;
	return pitcherHeight;
}

// Asking Pebble Diameter and Inserting into Variable
double AskPebbleDiameter()
{
	double pebbleDiameter;
	cout << "What is the diameter of the pebbles? (In inches) \n";
	cin >> pebbleDiameter;
	return pebbleDiameter;
}

// Asking Beak Length and Inserting into Variable
double AskBeakLength()
{
	double beakLength;
	cout << "What is the length of the crow's beak? (In inches) \n";
	cin >> beakLength;
	return beakLength;
}

// Calculating Pebbles
int CalcPebbles(double pitcherRadius, double pitcherHeight, double pebbleDiameter, double beakLength)
{
	double goalHeight = 0;
	double volumeC = 0;
	double volumeP = 0;
	double pebs = 0;
	int numPebbles = 0;
	goalHeight = (pitcherHeight * 0.5) - beakLength + 0.5;
	if (goalHeight <= 0)
	{
		numPebbles = 0;
	}
	else if (goalHeight > 0)
	{
		volumeC = pi * (pitcherRadius * pitcherRadius) * goalHeight;
		volumeP = pi * 4.0/3.0 * pow((pebbleDiameter * 0.5), 3);
		pebs = volumeC/volumeP;
		numPebbles = static_cast<int>(ceil(pebs));
	}
	return numPebbles;
}

// Showing the Results
void ShowResults(double pitcherRadius, double pitcherHeight, double pebbleDiameter, double beakLength, int numPebbles)
{
	cout << "The pitcher's radius is " << pitcherRadius << " inches.\n";
	cout << "The pitcher's height is " << pitcherHeight << " inches.\n";
	cout << "The pebble's diameter is " << pebbleDiameter << " inches.\n";
	cout << "The crow's beak length is " << beakLength << " inches.\n";
	cout << "The number of pebbles you need to place in the pitcher is ";
	cout << numPebbles << "! \n";
}