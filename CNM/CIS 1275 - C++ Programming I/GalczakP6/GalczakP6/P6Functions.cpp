// Anthony Galczak
// agalczak@cnm.edu
// P6Functions.cpp


// For cins/couts
#include <iostream>
// For fabs
#include <math.h>
// For ofstream
#include <fstream>
// For Stringstream
#include <sstream>
// For time functions
#include <ctime>
// For Strings
#include <string>
// Header
#include "P6Functions.h"
// Declaring Namespace
using namespace std;

// Displaying header
void WriteHeader()
{
	cout << "Anthony Galczak \n";
	cout << "Program 6 \n";
	cout << "This program will print a receipt for a parking lot visit.\n\n";
}

// TimeIn Function
void AskTimeIn(int &rin_HR, int &rin_MIN)
{
	cout << "What is the hour you came into the lot? (In 00 format)\n";
	cin >> rin_HR;
	cout << "What is the minute you came into the lot? (In 00 format)\n";
	cin >> rin_MIN;
}

// TimeOut Function
void ObtainTimeOut(int &rout_HR, int &rout_MIN)
{
// UTC Time Format Variable
	time_t SystemTime;
// Pass address of systemtime to time function
	time(&SystemTime);
// Convert UTC to usable data - declaring time struct
	struct tm OSTime;
// Pass address of SystemTime and localtime function
	localtime_s(&OSTime, &SystemTime);
// Insert HR & MIN into variables
	rout_HR  = OSTime.tm_hour;
	rout_MIN = OSTime.tm_min;
}

// ValidateTimes Function, making sure the times are valid
bool ValidateTimes(bool &allOK, int rin_HR, int rin_MIN, int rout_HR, int rout_MIN)
{
// Validating that the in time to the parking lot was not between 12AM and 4AM
	if(rin_HR >=23 && rin_HR <=4)
	{
		return allOK;
	}

// This is the else now that we know it's within valid hours for in time
	else
	{
// Validating to make sure the in time is between 0 and 59
		if(rin_MIN > 59 || rin_MIN < 0)
		{
			return allOK;
		}
		else
		{

// The in hour cannot possibly be more than the out time to be valid
			if(rin_HR > rout_HR)
			{
				return allOK;
			}

// The in hour can be the same, but if the in min is greater also it isn't valid
			else if(rin_HR == rout_HR && rin_MIN > rout_MIN)
			{
				return allOK;
			}

// If the in time is between 4AM-12AM and min/hr is greater for out time, output true.
			else
			{
				allOK = true;
				return allOK;
			}
		}
	}
}


// Calculate Fee Function, Calculating the cost of parking
int CalculateFee(int &minutes, int &hrs, bool &allOK, int rin_HR, int rin_MIN, 
				 int rout_HR, int rout_MIN)
{

// Declaring Variables to use for calculations
	int fee = 0;

// Verifying Validation is okay from previous function
// If validation isn't okay, it will not perform calculations
	if(allOK == 1)
	{

// Calculating minutes and hrs
		minutes = rout_MIN - rin_MIN;
		hrs = rout_HR - rin_HR;

// If minutes ends up being negative, properly calculating with 1 less hour
		if(minutes < 0)
		{
		hrs -= 1;
		minutes = 60 + minutes;
		}

// Adding $ to fee for minutes calculated
		if(minutes == 0)
		{
			fee = 0;
		}
		else if(minutes <= 30)
		{
			fee = 2;
		}
		else if(minutes > 30)
		{
			fee = 4;
		}

// Adding $ to fee for hours calculated
		fee += (hrs*4);
		return fee;
	}

// If allOK isn't okay then drop out
	else
	{
		cout << "This is not a valid time in!\n";
		return 0;
	}
}

// Write Receipt Function
bool WriteReceipt(int minutes, int hrs, int rin_HR, int rin_MIN, int rout_HR, int rout_MIN, int fee)
{

// Declaring cstring to hold the date
	errno_t date;

// Finding the date
	char dateNow[20];
	date = _strdate_s(dateNow, 20);

// Stringstream declaration
	stringstream ss;

// Setting up file to be inputted
	ss << rin_HR << "_" << rin_MIN << ".txt";
	string file = ss.str();
	ofstream myFile;

// FILE NAME FOR HR_IN & HR_MIN
	myFile.open(file.c_str());

// If statement for whether or not the file opened
	if(!myFile)
	{
		cout << "Whoops! Can't find the file!\n";
		return false;
	}
	else
	{

// If minutes are single digits then add a leading zero

// Inputting data into the stringstream
	ss << "\nC++ International Airport\n";
	ss << "Airport\n\n";
	ss << "   " << dateNow << "\n\n";
	ss << "Time In:	" << rin_HR << ":";

// If minutes are single digits then add a leading zero for in minutes
	if(rin_MIN < 10)
	{
		ss << "0";
	}
	ss << rin_MIN << "\n";


// If minutes are single digits then add a leading zero for out minutes
	ss << "Time Out:	" << rout_HR << ":"; 
	if(rout_MIN < 10)
	{
		ss << "0";
	}
	ss << rout_MIN << "\n\n";
	ss << "Total Time Parked\n";
// Using absolute value of minutes just in case minutes is negative
	ss<< hrs << " HR and " << abs(minutes) << " MIN \n\n";
	ss << "Fee		" << "$  " << fee << "\n\n";
	ss << " HAVE A NICE DAY!\n\n\n";
// Inserting directly into file
	myFile << ss.str();

// Outputting to user
	cout << ss.str();

	return true;
	}

// Closing the file
	myFile.close();
}