// Anthony Galczak
// agalczak@cnm.edu
// Functions.cpp

#include <iostream>
#include <string>
// For cos and sin
#include <cmath>
// For file output/input
#include <fstream>
#include "Functions.h"
using namespace std;

// Header function
void Header()
{
	cout << "Anthony Galczak\nProgram 1\nThis program will be used to calculate ";
	cout << "a cannonball's trajectory.\n\n";
}

// Function for asking the user for velocity and angle
void AskForInitialValues(double &vel0, double &angle)
{
	cout << "What is the velocity of your cannon?(In meters/sec)\n";
	cin >> vel0;
	cout << "What is the angle of your cannon?\n";
	cin >> angle;
}

void Shoot(double vel0, double angle, double pos[SIZE][2], int &total)
{
	double velX = 0, velY = 0, posX = 0, posY = 0, rad = 0;

	// Initializing total to 0 in case user is running it again
	total = 0;

// Clearing the array for fresh use
	for (int i = 0; i < SIZE; i++)
	{
		pos[i][0] = 0;
		pos[i][1] = 0;
	}

// Converting the angle that the user has given into radians for calculations
	rad = angle * PI / 180;

// Trig functions in order to calculate velX and velY
	velX = cos(rad) * vel0;
	velY = sin(rad) * vel0;

	// calculate velX, velY from vel0 using sin and cos
	int i = 0;
	do
	{
		Move(velX, velY, &posX, &posY);
		
		// Changing the velocity in the y-axis multiplied by negative gravity G and time step DELTA_T
		velY = velY + G * DELTA_T;
		pos[i][0] = posX;
		pos[i][1] = posY;

		// Iterating to assign new positions to the array
		i++;

		// This gives the for loop the right amount of iterations to go through later on in WriteData
		total++;

	} while (posY >= 0 && i < SIZE);
}

// Move function, This will be used in the shoot loop,
void Move(double velX, double velY, double *posX, double *posY)
{
	*posX = *posX + (velX * DELTA_T);
	*posY = *posY + (velY * DELTA_T);
}

bool WriteData(double pos[SIZE][2], string &fName, int total)
{
	// Declaring ofstream object and using user's filename
	ofstream output;
	output.open(fName.c_str());
	
	// If the file isn't opened then return false for writedata
	if (!output)
	{
		cout << "Whoops!";
		return false;
	}
	else
	{
		// Writing the array to a file
		for (int i = 0; i < total; ++i)
		{
			output << pos[i][0] << "," << pos[i][1] << "\n";
		}
	}

	// Closing the file
	output.close();
	return true;
}