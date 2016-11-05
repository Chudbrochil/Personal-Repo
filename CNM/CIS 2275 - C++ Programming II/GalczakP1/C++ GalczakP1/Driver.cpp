// Anthony Galczak
// agalczak@cnm.edu
// Driver.cpp

#include <iostream>
#include <string>
// For using setw
#include <iomanip>
using namespace std;

// Including the header file
#include "Functions.h"

int main()
{
	Header();

// Declaring variables
	double vel, angle;
	int total = 0;
	string fName;
	bool bFile;

// Declaring array
	double pos[SIZE][2];

// Starting a do while loop
	char lpProg;
	lpProg = 'y';
	do
	{
		// Asking user for velocity and angle
		AskForInitialValues(vel, angle);

		// Initial shooting of the cannon
		Shoot(vel, angle, pos, total);

		// Outputting data to user
		
		cout << setw(15) << "x-Axis" << setw(15) << "y-Axis\n";
		for (int i = 0; i < total; i++)
		{
			cout << setw(15) << pos[i][0] << setw(15) << pos[i][1] << "\n";
		}
		cout << "Your initial velocity is:" << vel << "\nThe angle of the cannon is: " << angle << "\n";

		// Asking user for file name and writing to a file
		cout << "What would you like to name your file? (Append with .csv)\n";
		cin.ignore();
		getline(cin, fName);
		bFile = WriteData(pos, fName, total);

		// Checking to make sure that the file was written and giving user output for success or failure
		if (bFile == true)
		{
			cout << "File successfully written to " << fName << ".\n";
		}
		else
		{
			cout << "Computer error, no file written.\n";
		}

// Asking if user wants to do another calculation
		cout << "Would you like to shoot another cannonball? (y/n)\n";
		cin >> lpProg;
		cin.ignore();

	} while (lpProg == 'y');


// Ending main
	cout << "Thanks for using my cannonball shooting program!\n";
	return 0;
}