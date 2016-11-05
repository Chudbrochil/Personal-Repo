// Anthony Galczak
// agalczak@cnm.edu
// Cannonball.cpp

#include <iostream>
#include <string>
#include <cmath>
#include <fstream>
#include "Cannonball.h"
#include <sstream>
using namespace std;

// Default constructor
Cannonball::Cannonball()
{
	// Variables initialization
	double velX = 0, velY = 0, posX = 0, posY = 0, rad = 0;
}

void Cannonball::SetInitialValues(double initVel, double ang)
{
	vel0 = initVel;
	angle = ang;
	Shoot();
}

void Cannonball::Shoot()
{
	// Initialization
	posX = posY = total = 0;
	double rad = 0;

	// Initializing the array to 0 in case the user is shooting again
	for (int i = 0; i < total; i++)
	{
		pos[i][0] = 0;
		pos[i][1] = 0;
	}

	// Converting the angle that the user has given into radians for calculations
	rad = angle * PI / 180;

	// Trig functions in order to calculate velX and velY
	velX = cos(rad) * vel0;
	velY = sin(rad) * vel0;

	// Declaring iterator
	int i = 0;
	do
	{
		Move();

		// Changing the velocity in the y-axis multiplied by negative gravity G and time step DELTA_T
		velY = velY + G * DELTA_T;
		pos[i][0] = posX;
		pos[i][1] = posY;
		i++;

		//Iterator to keep track of how many array elements have been made
		total++;
	} while (posY >= 0 && total < SIZE);
}

// Method for moving the cannonball via DELTA_T (time iterator)
void Cannonball::Move()
{
	posX = posX + velX * DELTA_T;
	posY = posY + velY * DELTA_T;
}

// Method for outputting string to user
string Cannonball::GetPositionData()
{
	stringstream ss;

	ss << "The angle is: " << angle << "\n";
	ss << "The velocity is: " << vel0 << "\n";

	for (int i = 0; i < total; i++)
	{
		ss << pos[i][0] << "  " << pos[i][1] << endl;
	}

	// Returning the built stringstream as a string
	return ss.str();
}

bool Cannonball::WritePositionData(string fName)
{
	// Declaring an output object and inputting the user's userFile filename
	ofstream output;
	output.open(fName.c_str());

	// If the file isn't opened then return false
	if (!output)
	{
		return false;
	}
	else
	{
		for (int i = 0; i < total; ++i)
		{
			output << pos[i][0] << "," << pos[i][1] << "\n";
		}
	}

	// Closing file
	output.close();
	return true;
}