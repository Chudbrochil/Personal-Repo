#include "MoneyCtr.h"
#include <sstream>
#include <math.h>


MoneyCtr::MoneyCtr()
{
	// Initializing class variables, there is 20 of each "change denomination" to start
	bNickels = bDimes = bQuarters = bDollars = 20;
	totalMoney = change = inputAmount = purchaseAmount = 0.0;
	myChange = "";

	// Calculating the amount of money left in the bank
	CalcTotalMoney();
}

void MoneyCtr::MakeChange()
{
	// Math to figure out how much change should be returned
	change = inputAmount - purchaseAmount;

	// Calculating the change to give back
	int calcChange = int(ceil(change * 100));

	// Running calculation to find each individual denomination to then subtract from the banks total
	rDollars = calcChange / 100;
	rQuarters = (calcChange % 100) / 25;
	rDimes = ((calcChange % 100) % 25) / 10;
	rNickels = (((calcChange % 100) % 25) % 10) /5;

	// Subtracting the "return money" from the bank to give to the customer
	bDollars -= rDollars;
	bQuarters -= rQuarters;
	bDimes -= rDimes;
	bNickels -= rNickels;
	
	// Creating the string that will be used to output the user's change
	stringstream ss3;
	ss3 << "Your change is:\r\nDollar coins: " << rDollars << " - Quarters: " << rQuarters <<
		"\r\nDimes: " << rDimes << " - Nickels: " << rNickels;

	// Converting the stringstream to a string and putting it inside the class variable myChange
	myChange = ss3.str();
}

void MoneyCtr::CalcTotalMoney()
{
	// Calculating the amount of money left in the bank. dollars, quarters, etc. are integers so I am multiplying them by 100 first to do the math
	totalMoney = 0;
	totalMoney += bDollars * 100;
	totalMoney += bQuarters * 25;
	totalMoney += bDimes * 10;
	totalMoney += bNickels * 5;
	totalMoney = totalMoney / 100;
}

void MoneyCtr::CalcInputAmount()
{
	// Calculating the user's input amount total
	inputAmount = 0;
	inputAmount += uDollars * 100;
	inputAmount += uQuarters * 25;
	inputAmount += uDimes * 10;
	inputAmount += uNickels * 5;
	inputAmount = inputAmount / 100;
}

// Public Methods
void MoneyCtr::SetInputMoney(int dol, int quar, int dim, int nick)
{
	// Assigning the input coins into the class variables for the users coins
	uDollars = dol;
	uQuarters = quar;
	uDimes = dim;
	uNickels = nick;

	// Adding the user's coins into the bank
	bDollars += uDollars;
	bQuarters += uQuarters;
	bDimes += uDimes;
	bNickels += uNickels;

	// Calculating the total of each of these denominations. dollars, quarters, dimes and nickels all together
	CalcInputAmount();

	// Calculating the banks inventory of change
	CalcTotalMoney();
}

void MoneyCtr::SetSodaCost(double purAmt)
{

	purchaseAmount = purAmt;

	MakeChange();
}

string MoneyCtr::GetBeginningFile()
{
	// Creating a stringstream to hold the string
	stringstream ss;

	// Bank summarization before any purchase or modification is made
	ss << "Money in the Bank:\r\nDollars: " << bDollars << " - Quarters: " << bQuarters << " - Dimes: " << bDimes << " - Nickels: " << bNickels;

	// Returning stringstream as a string
	return ss.str();
}

string MoneyCtr::GetWriteInLog()
{
	// Stringstream to hold the data for the logger
	stringstream ss2;

	// Total string for putting into the results box for the logger/form
	ss2 << "Change Tendered: " << inputAmount << "\r\nState of the Bank:\r\n" << "Cash left in the bank: " << totalMoney <<
		"\r\nDollars: " << bDollars << " - Quarters: " << bQuarters << " - Dimes: " << bDimes << " - Nickels: " << bNickels;

	// Returning stringstream as a string
	return ss2.str();
}

double MoneyCtr::GetHowMuchInput()
{
	return inputAmount;
}

string MoneyCtr::GetReturnMoney()
{
	return myChange;
}

double MoneyCtr::GetChange()
{
	return change;
}

// Made this into a boolean so that vending machine class can use it later on for validation purposes to return a money string
void MoneyCtr::ReturnMoney()
{
	// Returning the money back to the bank
	bDollars -= uDollars;
	bQuarters -= uQuarters;
	bDimes -= uDimes;
	bNickels -= uNickels;

	// Returning the change back to the bank
	bDollars += rDollars;
	bQuarters += rQuarters;
	bDimes += rDimes;
	bNickels += rNickels;
}