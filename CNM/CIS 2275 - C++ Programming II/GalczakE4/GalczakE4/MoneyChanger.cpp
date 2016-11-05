// Anthony Galczak
// agalczak@cnm.edu
// MoneyChanger.cpp

#include "MoneyChanger.h"

// Default constructor
MoneyChanger::MoneyChanger()
{
	purchase = 0.0;
	money = 0.0;
	change = 0;
}

// Set method for user given variables of the items price and how much money they gave
void MoneyChanger::SetCash(double purchasePrice, double moneyGiven)
{
	// Setting my driver parameters into my class variables
	purchase = purchasePrice;
	money = moneyGiven;

	// Calling the calculate function known as changemaker
	ChangeMaker();
}

// Calculate method
void MoneyChanger::ChangeMaker()
{
	// Change converts the leftover money into an int * 100 so we don't lose precision
	change = (money * 100) - (purchase * 100);
	change / 1;

	// Calculating bills using modulus
	mTwenties = change / 2000;
	mTens = (change % 2000) / 1000; // modulus of 2000 removes the 20s from the tens calculation
	mFives = ((change % 2000) % 1000) / 500;
	mOnes = (change % 500) / 100;

	// Calculating coins using modulus
	mQuarters = (change % 100) / 25;
	mDimes = (change % 25) / 10; // modulus of 25 removes the quarters from the dimes calculation
	mNickels = ((change % 25 ) % 10) / 5;
	mPennies = (change % 5) / 1;
}

// Get method for change
int MoneyChanger::GetTotalChange()
{
	return change;
}

// Get method for bills
void MoneyChanger::GetBills(int *twenties, int *tens, int *fives, int *ones)
{
	*twenties = mTwenties;
	*tens = mTens;
	*fives = mFives;
	*ones = mOnes;
}

// Get method for coins
void MoneyChanger::GetCoins(int &quarters, int &dimes, int &nickels, int &pennies)
{
	quarters = mQuarters;
	dimes = mDimes;
	nickels = mNickels;
	pennies = mPennies;
}