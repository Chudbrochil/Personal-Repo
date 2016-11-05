// Anthony Galczak
// agalczak@cnm.edu
// GalczakP4Driver.cpp

// For cins/couts
#include <iostream>
// For Strinngs
#include <string>
// For Header/Prototypes
#include "GalczakP4.h"
//Declaring Namespace
using namespace std;

//Starting Main
int main()
{

// Declaring Variables
double pitcherRadius = 0;
double pitcherHeight = 0;
double pebbleDiameter = 0;
double beakLength = 0;
int numPebbles = 0;
string showResults;

// Using Header Function
WriteHeader();

// Starting Do While Loop
char lpProg;
lpProg = 'y';
do
	{

// Using Functions to Ask User for Data
	pitcherRadius = AskPitcherRadius();
	pitcherHeight = AskPitcherHeight();
	pebbleDiameter = AskPebbleDiameter();
	beakLength = AskBeakLength();

// Running Calculation Function
	numPebbles = CalcPebbles(pitcherRadius, pitcherHeight, pebbleDiameter, beakLength);
	ShowResults(pitcherRadius, pitcherHeight, pebbleDiameter, beakLength, numPebbles);

// Ending Do While Loop
	cout << "Would you like to try another pitcher to drink from? (y/n) \n";
	cin >> lpProg;
	} while (lpProg == 'y');

// Ending Main
cout << "Thanks for using my Crow and the Pitcher calculator! \n";
return 0;

}