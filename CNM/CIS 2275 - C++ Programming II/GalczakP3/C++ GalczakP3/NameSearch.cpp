// Anthony Galczak
// agalczak@cnm.edu
// NameSearch.cpp

#include "NameSearch.h"
#include "P3_TemplateSort.h"
#include <string>
#include <fstream>
#include <iostream>
#include <array>

NameSearch::NameSearch()
{
	// Initializing names array to nothing, initializing other variables to nothing
	for (int i = 0; i > 10000; i++)
	{
		names[i] = "";
	}
	total = 0;
	name = "";
	filename = "";
	bReady = false;
}

NameSearch::NameSearch(string fName)
{
	// Initializing names array to nothing, initializing other variables to nothing
	for (int i = 0; i > 10000; i++)
	{
		names[i] = "";
	}
	total = 0;
	name = "";
	filename = "";
	bReady = "";

	// Using overloaded constructor to be able to read file using fName, assigning this into class variable filename
	filename = fName;

	// Calling method for reading the file
	ReadFile();
}

// Using regular constructor and then manually setting filename
void NameSearch::SetFileName(string f)
{
	filename = f;
	ReadFile();
}

void NameSearch::ReadFile()
{
	ifstream input;
	input.open(filename.c_str());

	// Setting bready variable based on whether or not the file opened
	if (!input)
	{
		bReady = false;
	}
	else
	{
		bReady = true;

		// Declaring variable to be captured as a single line
		string line;

		// For every line string that is in my file, getline it into input
		for (line; getline(input, line);)
		{
			do
			{
				names[total] = line;
				// Iterate total to continue going through the file
				total++;
			} while (total > 10000);

		}
	}


	// Closing file
	input.close();

	// Sorting the names after opening file
	Sort();
}

void NameSearch::Sort()
{
	string temp[10000];

	// Assigning names into temp
	for (int i = 0; i < total; i++)
	{
		temp[i] = names[i];
	}

	TemplateSort <string> tSort;
	tSort.shellSort(temp, total);

	// Assigning the sorted temp back into names
	for (int i = 0; i < total; i++)
	{
		names[i] = temp[i];
	}
}

// Boolean method for finding out if the file is ready
bool NameSearch::IsReady()
{
	if (bReady == true)
	{
		return true;
	}
	else
	{
		return false;
	}
}

// Get method for getting the total names in the list
int NameSearch::GetTotal()
{
	return total;
}

// Find method for looking for a first name
bool NameSearch::FindFirstNames(vector<string> &vsFirst, string name)
{
	int commaInt = 0, foundPos = 0;
	
	// Clearing vector in case the user goes again
	vsFirst.clear();

	// for loop to find the names that match string name
	for (int i = 0; i < total; i++)
	{
		// Finding what position the comma is at
		commaInt = names[i].find(",");

		// Finding if the user's name they entered is in my string
		foundPos = names[i].find(name);

		// If foundpos (the c.str element position of the found user's name) is greater than 0 (it found something)
		// which in turn is bigger than commaInt (which is always a greater than 0 value) then add the string to the vector
		if (foundPos > commaInt)
		{
			vsFirst.push_back(names[i]);
		}
		
		// for last names, if (foundPos > 0 && foundPos < commaInt - name.length())
	}

	// Logic for returning correct boolean if the vector has a non zero value (it has members)
	if (vsFirst.size() > 0)
	{
		return true;
	}
	else
	{
		return false;
	}
}


bool NameSearch::FindLastNames(vector<string> &vsLast, string name)
{
	int commaInt = 0, foundPos = 0;

	// Clearing vector in case the user goes again
	vsLast.clear();

	// for loop to find the names that match string name
	for (int i = 0; i < total; i++)
	{
		// Finding what position the comma is at
		commaInt = names[i].find(",");

		// Finding if the user's name they entered is in my string
		foundPos = names[i].find(name);

		// If foundpos (the c.str element position of the found user's name) is less than comma placement + name length, then
		// add the string to the vector. This logic avoids false positives on first names
		
		int actualComma = ((commaInt - name.length()) + 1); // Logic wasn't being calculated correctly within if statement
															// comma location - length of name + 1 for 0 indexing within .find
		if (foundPos < actualComma && foundPos >= 0)
		{
			vsLast.push_back(names[i]);
		}

	}

	// Logic for returning correct boolean if the vector has a non zero value (it has members)
	if (vsLast.size() > 0)
	{
		return true;
	}
	else
	{
		return false;
	}
}