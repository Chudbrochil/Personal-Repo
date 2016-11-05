// Anthony Galczak
// agalczak@cnm.edu
// GalczakP7pt1Driver.cpp

// For cins/couts
#include <iostream>
// For if/ofstream
#include <fstream>
// For setw
#include <iomanip>
// For Strings
#include <string>
// For Header/Prototypes
#include "GalczakP7pt1Functions.h"
// Declaring namespace
using namespace std;

// Starting main
int main()
{

// File name
string fileName = "EIA Data.csv";

// Var
char success;

// Declaring arrays, there is 218 lines worth of useful data
string countries[218];
double data2008[218];
double data2009[218];
double data2010[218];
double data2011[218];
double data2012[218];

// Displaying Header to user
WriteHeader();

// Starting a do while loop
char lpProg;
lpProg = 'y';
do
	{

	success = Read(countries, data2008, data2009, data2010, data2011, data2012);

// Setting Precision so data comes out a bit cleaner
	cout.setf(ios::fixed | ios::showpoint);
	cout.precision(2);

// Labeling the output
	cout << "Total CO2 emissions from the consumption of energy by country:\n";

// Outputting a label for the lines, setw for lining up data
	cout << setw(20) << "Countries" << setw(10) << "2008" << setw(10) << "2009" <<
		setw(10) << "2010" << setw(10) << "2011" << setw(10) << "2012\n";

// Outputting each line as an element of the arrays, this entire block is 1 line
	for(int i=0; i<218; i++)
	{
// Using setw to line up data with headers
	cout << setw(20) << countries[i] << setw(10) << data2008[i];
	cout << setw(10) << data2009[i] << setw(10);
	cout << setw(10) << data2010[i] << setw(10);
	cout << setw(10) << data2011[i] << setw(10);
	cout << setw(9) << data2012[i] << "\n";
	}

// Ending do while loop
	cout << "\nWould you like to read the file again? (y/n)\n";
	cin >> lpProg;
	cin.ignore();
	} while (lpProg == 'y');

// Thanking user for using the program
cout << "Thanks for using my CO2 emissions by country program!\n";
return 0;

}