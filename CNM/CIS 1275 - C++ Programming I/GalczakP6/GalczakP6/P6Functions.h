// Anthony Galczak
// agalczak@cnm.edu
// P6Functions.h

// Include Guard
#ifndef _P6FUNCTIONS_H
#define _P6FUNCTIONS_H

// Prototypes
void WriteHeader();
void ObtainTimeOut(int &rin_HR, int &rin_MIN);
void AskTimeIn(int &rin_HR, int &rin_MIN);
bool ValidateTimes(bool &allOK, int rin_HR, int rin_MIN, int rout_HR, int rout_MIN);
int CalculateFee(int &minutes, int &hrs, bool &allOK, int rin_HR, int rin_MIN, int rout_HR, int rout_MIN);
bool WriteReceipt(int minutes, int hrs, int rin_HR, int rin_MIN, int rout_HR, int rout_MIN, int fee);

// Struct for parking times
struct ParkingTimes
{
	int HR;
	int MIN;
};

// Ending include guard
#endif