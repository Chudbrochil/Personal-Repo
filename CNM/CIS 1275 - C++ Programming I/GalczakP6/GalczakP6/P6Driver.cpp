// Anthony Galczak
// agalczak@cnm.edu
// P6Driver.cpp

// For cins/couts
#include <iostream>
// For Strings
#include <string>
// For Header/Prototypes
#include "P6Functions.h"
// Declaring Namespace
using namespace std;

// Starting Main
int main()
{

// Declaring Variables
bool allOK = false;
int fee = 0, minutes = 0, hrs = 0;

// Declaring Struct
ParkingTimes in_time;
ParkingTimes out_time;

// Displaying Header to the user
WriteHeader();

// Starting a Do While Loop
char lpProg;
lpProg = 'y';
do 
	{

// Bringing customers input time into the struct/in time
	AskTimeIn(in_time.HR, in_time.MIN);

// Bringing current time into the struct/out time
	ObtainTimeOut(out_time.HR, out_time.MIN);

// Validation
	ValidateTimes(allOK, in_time.HR, in_time.MIN, out_time.HR, out_time.MIN);

// Calculating the Fee
	fee = CalculateFee(minutes, hrs, allOK, in_time.HR, in_time.MIN, out_time.HR, out_time.MIN);

// Prompting user if they would like a receipt or not
	char rcpt;
	cout << "Would you like a receipt? (y/n)\n";
	cin >> rcpt;

// Producing a Write Receipt if the fee was calculated
	if(fee > 0 && rcpt == 'y')
	{
		WriteReceipt(minutes, hrs, in_time.HR, in_time.MIN, out_time.HR, out_time.MIN, fee);
	}
	else 
	{
		cout << "Your fee is " << fee << " $\n";
	}

// Ending Do While Loop
	cout << "\nWould you like to park again?(y/n)\n";
	cin >> lpProg;
	cin.ignore();
	} while (lpProg == 'y');

// Ending Main
cout << "Thanks for using my parking lot program!\n";
return 0;

}