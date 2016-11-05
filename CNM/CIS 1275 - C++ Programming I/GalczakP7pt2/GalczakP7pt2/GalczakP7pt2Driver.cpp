// Anthony Galczak
// agalczak@cnm.edu
// GalczakP7pt2Driver.cpp

// For cins/couts
#include <iostream>
// For if/ofstream
#include <fstream>
// For setw
#include <iomanip>
// For Strings
#include <string>
// For Header/Prototypes
#include "GalczakP7pt2Functions.h"
// Declaring namespace
using namespace std;

// Starting main
int main()
{

// File name
string fileName = "EIA Data.csv";

// Declaring reference variables from the functions
double improved, average;
string countryimp, userFile;

// Declaring arrays, there is 218 lines worth of useful data
string countriesArr[218];
double data2008Arr[218];
double data2009Arr[218];
double data2010Arr[218];
double data2011Arr[218];
double data2012Arr[218];

// Declaring calculation arrays
double improveArr[218];
double highestArr[218];
string countriesArr2[218];

// Displaying Header to user
WriteHeader();

// Reading in arrays
Read(countriesArr, data2008Arr, data2009Arr, data2010Arr, data2011Arr, data2012Arr);

// Analyzing the arrays to get most improved, avg CO2 and highest 5 countries
Analyze(countriesArr, improveArr, highestArr, data2008Arr, data2009Arr, data2010Arr, data2011Arr, 
		data2012Arr, countriesArr2, improved, countryimp, average);

// Writing data to a file
Write(improved, countryimp, average, countriesArr2, highestArr, userFile);

// Letting the user know where their file is located as well as the exact filename
cout << "Your file is \"" << userFile << "\" within the projects folder of this program\n";

// Thanking user for using the program
cout << "Thanks for using my CO2 emissions by country program!\n";

// Opening file within notepad
string openfile = "notepad.exe " + userFile;
system(openfile.c_str());

// Ending main
return 0;

}