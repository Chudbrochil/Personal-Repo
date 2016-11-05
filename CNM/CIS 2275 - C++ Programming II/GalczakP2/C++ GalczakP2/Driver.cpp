// Anthony Galczak
// agalczak@cnm.edu
// Driver.cpp

#include <iostream>
#include <string>
#include "Functions.h"
#include "Cannonball.h"

int main()
{
	// Displaying header
	Header();
	
	// Declaring variables
	double pos[SIZE][2];
	double vel0, angle;
	string userFile;

	// Instantiating my cannonball object
	Cannonball ball;

	// Starting a do while loop in case the user wants to shoot again
	char lpProg = 'y';
	do
	{
		// Asking user for velocity and angle
		AskForInitialValues(vel0, angle);
		ball.SetInitialValues(vel0, angle);
		cout << ball.GetPositionData();

		// Asking user for what they'd like their file name to be
		cout << "What would you like to name your file? (Append with .csv)\n";
		cin.ignore();
		getline(cin, userFile);

		// Setting their file name into write position data to output a file
		bool bFileOk = ball.WritePositionData(userFile);

		// Outputting file name to user if the file is successfully written, otherwise an error will be thrown
		if (bFileOk == true)
		{
			cout << "File successfully written to " << userFile << ".\n";
		}
		else
		{
			cout << "Computer error, no file written.\n";
		}

		// Asking if user wants to shoot another cannonball
		cout << "Would you like to shoot another cannonball?(y/n)\n";
		cin >> lpProg;
		cin.ignore();
	} while (lpProg == 'y');

	// Display good-bye message
	cout << "Thanks for using my cannonball shooting program!\n";
	return 0;
}