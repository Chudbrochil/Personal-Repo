// Anthony Galczak
// agalczak@cnm.edu
// Driver.cpp

// For cins/couts
#include <iostream>
// For Strings
#include <string>
// For Header/Prototypes
#include "Whiskey.h"
// Declaring namespace
using namespace std;

// Starting Main
int main()
{

// Declaring Variables
string name;
int diam = 0;
int height = 0;
int numBar = 0;
double totalVol = 0;
double vol = 0;
int cases = 0;
int wholeBtl = 0;
int leftOver = 0;
char crewToggle;
double crewTreat = 0;

// Using Header function to display program information
WriteHeader();

// Starting a Do While Loop 
char lpProg;
lpProg = 'y';
do
	{

// Getting name of Whiskey
	name = AskName();
// Asking User for Barrels Information	
	AskForBarrels(&diam, &height, &numBar);
// Asking user if there is a crew treat or not
	cout << "Would you like to give the remainder whiskey to the crew? (y/n)\n";
	cin >> crewToggle;
// Subtracting the 5% loss
	vol = CalcNetWhiskeyVol(diam, height, numBar, totalVol);
// Calculating the Amount of Bottles, Crewtreat, etc.
	DetermineBottles(vol, cases, wholeBtl, leftOver, crewTreat);
// Writing Results to user, If statement for whether you want a crew treat or not
	if (crewToggle == 'y')
	{
	WriteResults(name, diam, height, vol, totalVol, cases, wholeBtl, leftOver, crewTreat);
	}
	else if (crewToggle == 'n')
	{
	WriteResults(name, diam, height, vol, totalVol, cases, wholeBtl, leftOver);
	}

// Ending Do While Loop
	cout << "\nWould you like to calculate another batch of whiskey?\n";
	cin >> lpProg;
	cin.ignore();
	} while (lpProg == 'y');

// Ending Main
cout << "Thanks for using my whiskey bottle calculator! \n";
return 0;

}