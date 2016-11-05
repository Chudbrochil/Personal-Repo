// Anthony Galczak
// agalczak@cnm.edu
// Driver.cpp

#include <iostream>
#include <string>
#include "Functions.h"
#include "NameSearch.h"
#include "P3_TemplateSort.h"
#define FILE_IN "P3_Names10K.txt"

int main()
{
	// Displaying header
	Header();

	// Declaring variables
	int realTotal, lpProg, fSize, fileRef;
	string userName, userFile;
	vector<string>vsFirst, vsLast;
	bool bFirst, bLast, ready;


	// Asking user if they'd like to use their own file or use P3_Names10K.txt
	cout << "\nWhat file would you like to use?(Please enter a number)\n1.)Enter my own file\n2.)Use default P3_Names10K.txt\n";
	cin >> fileRef;

	// Instantiating object with default constructor
	NameSearch name;

	// If the user wants to use their own file then use their file for the setfilename method 
	if (fileRef == 1)
	{
		cout << "What would you like to name your file?(Remember your 3 digit extension)\n";
		cin >> userFile;
		name.SetFileName(userFile);
	}
	// If the user wants to use the default file then set the file using the set file method
	else if (fileRef == 2)
	{
		name.SetFileName(FILE_IN);
	}

	// Getting the total amount of names from the class
	realTotal = name.GetTotal();

	// Finding out if the file is ready
	ready = name.IsReady();

	// Cout to user if file is ready or not and how many names were read
	if (ready == true)
	{
		cout << "\nYour file was read successfully.\n" << realTotal << " names read.\n";
	}
	else if (ready == false)
	{
		cout << "\nYour file was not read. Here goes nothing!\n";
	}

	// Do while loop for the main menu in the program
	do
	{
		cout << "\n1.) Search for a first name\n2.) Search for a last name\n3.) How many items are in the array?\n4.) Exit the program\n";
		cin >> lpProg;
		
		// Search for a first name
		if (lpProg == 1)
		{
			// Asking user for first name
			cout << "\nPlease enter a first name:\n";
			cin >> userName;

			// Converting the userName to uppercase using my own ToUpper function
			userName = ToUpper(userName);

			// Populating method with array and users input
			bFirst = name.FindFirstNames(vsFirst, userName);
			cout << "SEARCHING FOR FIRST NAME: " << userName << "\n\n";

			// Outputting the names to the user if the name was found
			if (bFirst == true)
			{
				// Assigning the size of the vector into fSize for use in cout and use in the for loop
				fSize = vsFirst.size();
				cout << "Success! Your name was found.\nWe found " << fSize << " names.\n";

				// Outputting each member of the vector to the screen
				for (int i = 0; i < fSize; i++)
				{
					cout << vsFirst[i] << "\n";
				}
			}

			// If the vector is empty
			else
			{
				cout << "Sorry, it doesn't look like we found that name.\n";
			}

		}

		// Search for a last name
		else if (lpProg == 2)
		{
			// Asking user for last name
			cout << "\nPlease enter a last name:\n";
			cin >> userName;

			// Converting user's name into an uppercase name
			userName = ToUpper(userName);

			// Populating method with array and users input
			bLast = name.FindLastNames(vsLast, userName);
			cout << "SEARCHING FOR LAST NAME: " << userName << "\n\n";

			// Outputting the names to the user if the name was found
			if (bLast == true)
			{
				// Assigning the size of the vector into fSize for use in cout and use in the for loop
				fSize = vsLast.size();
				cout << "Success! Your name was found.\nWe found " << fSize << " names.\n";

				// Outputting each member of the vector to the screen
				for (int i = 0; i < fSize; i++)
				{
					cout << vsLast[i] << "\n";
				}
			}

			// If the vector is empty
			else
			{
				cout << "Sorry, it doesn't look like we found that name.\n";
			}

		}

		// How many items are in the array?
		else if (lpProg == 3)
		{
			cout << "\nThere are a total of " << realTotal << " names in your file.";
		}

	} while (lpProg != 4);

	// Good-bye message
	cout << "\nThank you for using my name-finding program! Good bye!\n";

	return 0;
}