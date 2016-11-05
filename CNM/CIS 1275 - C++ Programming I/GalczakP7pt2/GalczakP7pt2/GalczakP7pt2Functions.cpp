// Anthony Galczak
// agalczak@cnm.edu
// GalczakP7pt2Functions.cpp

// For cins/couts
#include <iostream>
// For ifstream/ofstream
#include <fstream>
// For Strings
#include <string>
// For stringstream
#include <sstream>
// To use .size()
#include <array>
// For Header/Prototypes
#include "GalczakP7pt2Functions.h"
// Declaring Namespace
using namespace std;

// Displaying header
void WriteHeader()
{
	cout << "Anthony Galczak \n";
	cout << "Program 7 Part 2 \n";
	cout << "This program will read data from the US Energy Information Administration \n";
	cout << "for total CO2 emissions from the consumption of energy by country and show the \n";
	cout << "most improved country over the 2008-2012 period, average CO2 per country\n";
	cout << "for 2012, and highest 5 CO2 emitting countries for 2012.\n\n";
}

bool Read(string countriesArr[], double data2008Arr[], double data2009Arr[], double data2010Arr[], 
		  double data2011Arr[], double data2012Arr[])
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
			getline(file, countriesArr[i], '"');
// Trashing first comma
			file >> trash;
			}
			else
			{
			getline(file, countriesArr[i], ',');
			}

// Logic for capturing data into arrays
// This is starting at the comma before the real data, the first comma has to be trashed
			file >> trash;
// After the first comma I capture real data into the array and then trash the next comma
			file >> data2008Arr[i] >> trash;
			file >> data2009Arr[i] >> trash;
			file >> data2010Arr[i] >> trash;
			file >> data2011Arr[i] >> trash;
			file >> data2012Arr[i];
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

// Notifying user that the data has been read
	cout << "Data successfully read from EIA_Data.csv\n";

// Returning true for successful read of file's data
	return true;
}


void Analyze(string countriesArr[], double improveArr[], double highestArr[], double data2008Arr[], 
			 double data2009Arr[], double data2010Arr[], double data2011Arr[], double data2012Arr[], 
			 string countriesArr2[], double &improved, string &countryimp, double &average)
{
// Logic for calculating most improved country

	for(int i=0; i<218; i++)
	{
// Getting the percentage of improvement, 2008 as 100 and 2012 as 80 will result in 80%
// I will do calculations again to output correct %
	improveArr[i] = data2008Arr[i] / data2012Arr[i];

// Assigning the highest array element to improved, this is the most improved
		if(improved < improveArr[i])
		{
		improved = improveArr[i];

// Capturing country that goes along with most improved
		countryimp = countriesArr[i];
		}
	}

// Making improved look like a whole %
improved = (improved-1)*100;

// Logic for calculating avg CO2 per country by 2012

// Declaring total to use for average calculations in the for loop
double total = 0;

// For loop to calculate avg CO2 per country
	for(int i=0; i<218; i++)
	{
	total += data2012Arr[i];
	}
// Calculating Average by diving size by total of CO2
// Using 218 as size instead of calculation as size() doesn't work in C++ 11
average = total / 218;

// Calculating highest 5 countries of CO2 emission by year

// Assigning each value of data2012Arr into highestArr array
for(int i=0; i < 218; ++i)
{
	highestArr[i] = data2012Arr[i];
}

// Assigning each value of countriesArr into countriesArr2 array
for(int i=0; i < 218; ++i)
{
	countriesArr2[i] = countriesArr[i];
}

// Declaring variables for use in bubble sort
int j;
double temp;
string tempc;

// Bubble sort to get values sorted lowest to highest
for(int i=0; i < 217; ++i)
{
	for(j=1; j < 218; ++j)
	{
// Replacing the further element (j) with j-1 if j-1 is bigger therefore
// sorting low to highest from 0-217
		if(highestArr[j-1] > highestArr[j])
		{
			temp = highestArr[j];
			highestArr[j] = highestArr[j-1];
			highestArr[j-1] = temp;

// Country sort
			tempc = countriesArr2[j];
			countriesArr2[j] = countriesArr2[j-1];
			countriesArr2[j-1] = tempc;
		}
	}
}

// Notifying user that the data has been analyzed
cout << "Data has been succesfully analyzed\n";
}

// Write function
void Write(double &improved, string &countryimp, double &average, string countriesArr2[], 
		   double highestArr[], string &userFile)
{
// Stringstream declaration to capture data to send to a file
stringstream ss;

// Prompting user to name the file
cout << "What would you like to name your file? (Add .txt)\n";
cin >> userFile;

// Declaring the file as what the user picked. (Hopefully they added a .txt)
string file = ss.str();
ofstream myFile;
myFile.open(userFile);

// Verifying file has opened and then writing to it
	if(!myFile)
	{
	cout << "Whoops! Can't find the file!\n";
	}
	else
	{
// Setting precision
	ss.setf(ios::fixed | ios::showpoint);
	ss.precision(2);

// Writing my information and general data information to user
	ss << "Anthony Galczak\n";
	ss << "This data shows the emissions for each country for the production of energy\n";
	ss << "from years 2008-2012. I will show you which country has improved the most\n";
	ss << "from 2008 to 2012, the average emissions for each country in 2012 and the\n";
	ss << "5 highest countries in emissions for 2012.\n";
	ss << "This data is readily available at the Energy Information Administration\n";
	ss << "http://www.eia.gov/cfapps/ipdbproject/iedindex3.cfm?tid=90&pid=44&aid=8\n\n";

// Writing most improved country info
	ss << "Most improved country for 2008-2012 is " << improved << "% decrease in emissions by " << countryimp << ".\n";

// Writing average CO2 per country info
	ss << "The average CO2 emissions per country for 2012 is " << average << " million metric tons.\n";

// Writing highest 5 countries for 2012 info
	ss << "The highest 5 countries for emissions in 2012 is:\n";
	ss << countriesArr2[217] << " with " << highestArr[217] << " million metric tons of CO2\n"; 
	ss << countriesArr2[216] << " with " << highestArr[216] << " million metric tons of CO2\n"; 
	ss << countriesArr2[215] << " with " << highestArr[215] << " million metric tons of CO2\n"; 
	ss << countriesArr2[214] << " with " << highestArr[214] << " million metric tons of CO2\n"; 
	ss << countriesArr2[213] << " with " << highestArr[213] << " million metric tons of CO2\n"; 

// Inserting the stringstream directly into user's file
	myFile << ss.str();

// Closing file
	myFile.close();

	}
}