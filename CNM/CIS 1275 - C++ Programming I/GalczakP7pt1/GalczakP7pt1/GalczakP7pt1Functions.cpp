// Anthony Galczak
// agalczak@cnm.edu
// GalczakP7pt1Functions.cpp

// For cins/couts
#include <iostream>
// For ifstream/ofstream
#include <fstream>
// For Strings
#include <string>
// For Header/Prototypes
#include "GalczakP7pt1Functions.h"
// Declaring Namespace
using namespace std;

// Displaying header
void WriteHeader()
{
	cout << "Anthony Galczak \n";
	cout << "Program 7 Part 1 \n";
	cout << "This program will read data from the US Energy Information Administration \n";
	cout << "for total CO2 emissions from the consumption of energy by country \n";
	cout << "and display it on the screen to the user.\n\n";
}

bool Read(string countries[], double data2008[], double data2009[], double data2010[], 
		  double data2011[], double data2012[])
{
// filename declared, skip and trash are trash variables
string fileName = "EIA Data.csv";
string skip;
char trash;

// Opening file
ifstream file;
file.open(fileName);

// If statement for successful file open
	if (file.is_open())
	{
// iteration declaration
		int i = 0;

// For loop to remove header of first 4 lines
		for(int i = 0; i < 4; i++)
		{
			getline(file, skip);
		}

// While loop to keep looping until reaching end of file
		while(!file.eof())
		{
// Setting c to peek at the next line, if it's a #, skip it
			char c = file.peek();
			while(c == '#')
			{
				getline(file,skip);
// Re-peeking in case there is a second # or "
				c = file.peek();
			}
// Fixing Quoted Countries such as "Bahamas, The"
			c = file.peek();
			if(c == '"')
			{
// Trashing first quote
			file >> trash;
// Capturing data into array and also trashing 2nd quote
			getline(file, countries[i], '"');
// Trashing first comma
			file >> trash;
			}
			else
			{
			getline(file, countries[i], ',');
			}

// Logic for capturing data into arrays
// This is starting at the comma before the real data, the first comma has to be trashed
			file >> trash;
// After the first comma I capture real data into the array and then trash the next comma
			file >> data2008[i] >> trash;
			file >> data2009[i] >> trash;
			file >> data2010[i] >> trash;
			file >> data2011[i] >> trash;
			file >> data2012[i];
// Getting rid of the line/character return
			getline(file, skip);

// I increment while not at end of file
			i++;
		}
			
	}
// Conditional for if the file isn't opened.
	else
	{
		cout << "\n Sorry I couldn't open your file!\n";
		return false;
	}
// Closing file
	file.close();

// Returning true for successful read of file's data
	return true;
}