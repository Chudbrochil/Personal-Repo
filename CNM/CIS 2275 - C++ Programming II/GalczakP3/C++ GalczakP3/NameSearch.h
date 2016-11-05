//NameSearch.h
//This file is provided to CIS2275 students for Program 3
//DO NOT MODIFY THIS FILE unless instructed.
// You will write the NameSearch.cpp file.
//NOTE: There are NO cout << statements in the NameSearch.cpp!

#ifndef  _NAMESEARCH_H
#define  _NAMESEARCH_H

#include <string>
#include <vector>
#include <array>
using namespace std;

class NameSearch
{
private:
	array<string, 10000> names;	//holds names read from file
	//OR array<string, 10000> names;  and #include <array>

	int total;		/*total number of names read from file, 
					class will read up to 10,000, 
					stops reading after 10,000th.*/
					
		/*NOTE: do not assume you'll have 10,000 in the file
		Your program should work with 10 items in file.
		Test program with files with <10,000, 10,000 and >10,000*/

	string name;		//user's requested name to look for in the list
	string filename;	//the name of the file that is read

	bool bReady;	//true if file read successfully
					//false if file not read successfully

	void ReadFile();	//reads the file, fills names array and sorts data
						//assigns total & bReady
						
	void Sort();		//uses the sorter provided to you to sort the names array



public:

	//Two constructors, be sure to initialize data appropriately
	NameSearch();			//no file, does not try to read anything		
	explicit NameSearch(string fName);	//is passed desired file, calls readFile()

	void SetFileName(string fName);	//is passed desired file
										//calls readFile()

	bool IsReady();		/*returns bReady flag.  The programmer *should* check 
						this before attempting to search for a name. The finds
						are designed to anticipate no data just in case*/
	
	/* find functions are passed a reference to a vector and a string. Each looks
	for the name, and fill the vectors with the full names containing that name.
	The name searching algorithm is performed in the find functions.
	Programming note: if name is not found, the find returns a false and the vector 
	contains no names.*/

	bool FindFirstNames(vector<string> &vsFirst, string name);
	bool FindLastNames(vector<string> &vsLast, string name);

	int GetTotal();	//returns the total number read from the file.
					//if the file hasn't been read or was not read successfully, 
					//returns a -1
	
};

#endif