// Anthony Galczak
// agalczak@cnm.edu
// Whiskey.cpp

// For cins/couts
#include <iostream>
// For Strings
#include <string>
// For modf
#include <math.h>
// Header
#include "Whiskey.h"
// Declaring Namespace
using namespace std;
// Declaring pi
const double pi = 3.141592653589793;

// Displaying Header
void WriteHeader()
{
	cout << "Anthony Galczak \n";
	cout << "Program 5 \n";
	cout << "This program will calculate how many 2L bottles of whiskey \n";
	cout << "will be made from a batch of aged barrels. \n\n";
}

// Asking for the name of the whiskey
string AskName()
{
	string name;
	cout << "What is the name of your whiskey? \n";
	getline(cin,name);
	return name;
}

// Asking how many barrels are being used
void AskForBarrels(int *pdiam, int *pheight, int *pnumBar)
{
	cout << "What is the diameter of the barrels? (In inches)\n";
	cin >> *pdiam;
	cout << "What is the height of the barrels? (In inches)\n";
	cin >> *pheight;
	cout << "How many aging barrels do you have?\n";
	cin >> *pnumBar;
}

// Calculating 5% Loss due to aging and filtering process
double CalcNetWhiskeyVol(int diam, int height, int numBar, double &r_totalVol)
{
	double vol = 0;
	double radius = 0;
// Setting individual variable for radius
	radius = diam/2.0;
// Cylinder calculation
	r_totalVol = pi * pow(radius, 2) * height * numBar;
	vol = r_totalVol * .95;
	return vol;
}

// Determining Calculations
void DetermineBottles(double vol, int &r_cases, int &r_wholeBtl, int &r_leftOver, double &r_crewTreat)
{
// 231 Cubic inches in a gallon, 3.7854118L to a gallon. 2L to a bottle
	r_wholeBtl = static_cast<double>((vol/231) * 3.7854118) / 2;
// 12 Bottles to a case
	r_cases = r_wholeBtl/12;
// Left Over Bottles calculated using a modulus
	r_leftOver = r_wholeBtl % 12;
// Declaring Variables for Calculating the Remainder of a bottle
	double wholeRaw, treatDec;
// Re-doing calculation to get raw double
	wholeRaw = (vol/231) * 3.7854118 / 2;
	treatDec = wholeRaw - r_wholeBtl;
// Calculating Crew Treat in Liters
	r_crewTreat = 2.0 * treatDec;
}

// Function for displaying results to the user
void WriteResults(string name, int diam, int height, double vol, double totalVol, int cases, int wholeBtl, int leftOver, double crewTreat)
{
// Output
	cout << "Whiskey Name: " << name << "\n";
	cout << "Your barrels are " << diam << " inches by " << height << " inches.\n";
	cout << "The total volume of whiskey is " << totalVol << " inches3.\n";
// Declaring variable to calculate loss due to aging
	double loss = 0;
	loss = totalVol - vol;
// Output
	cout << "The volume of whiskey lost due to aging is " << loss << " inches3.\n";
	cout << "The total volume after aging is " << vol << " inches3.\n";
	cout << "The total cases of whiskey is " << cases << " cases.\n";
	cout << "The total amount of bottles produced is " << wholeBtl << " bottles.\n";
	cout << "The left over bottles from the cases produced is " << leftOver << " bottles.\n";
// Setting Precision
	cout.setf(ios::fixed | ios::showpoint);
	cout.precision(2);
// Output
	cout << "The crew treat is " << crewTreat << " liters of whiskey.\n";
}
 
void WriteResults(string name, int diam, int height, double vol, double totalVol, int cases, int wholeBtl, int leftOver)
{
// Output
	cout << "Whiskey Name: " << name << "\n";
	cout << "Your barrels are " << diam << " inches by " << height << " inches.\n";
	cout << "The total volume of whiskey is " << totalVol << " inches3.\n";
// Declaring variable to calculate loss due to aging
	double loss = 0;
	loss = totalVol - vol;
// Output
	cout << "The volume of whiskey lost due to aging is " << loss << " inches3.\n";
	cout << "The total volume after aging is " << vol << " inches3.\n";
	cout << "The total cases of whiskey is " << cases << " cases.\n";
	cout << "The total amount of bottles produced is " << wholeBtl << " bottles.\n";
	cout << "The left over bottles from the cases produced is " << leftOver << " bottles.\n";
}