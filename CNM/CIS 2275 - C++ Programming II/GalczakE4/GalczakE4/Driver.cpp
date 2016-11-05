// Anthony Galczak
// agalczak@cnm.edu
// Driver.cpp

#include <iostream>
#include "MoneyChanger.h"
#include "Functions.h"

using namespace std;

//TODO:  score 46/50
//TODO:  When I enter non-numerical input, your program crashes.  -2

int main()
{
	// Vars
	double purchasePrice, moneyGiven;
	int outputChange;
	int twenties, tens, fives, ones, quarters, dimes, nickels, pennies;

	// Displaying header
	Header();

	char lpProg = 'y';
	do
	{
		// Asking user for purchase item and amount of money they're going to give
		cout << "What is the cost of the item you would like to purchase?\n";
		cin >> purchasePrice;
		cout << "How much money are you giving?\n";
		cin >> moneyGiven;

		// Instantiating new object of MoneyChanger
		MoneyChanger mny;

		// Setting user's parameters into the SetCash method
		mny.SetCash(purchasePrice, moneyGiven);

		// Getting actual change amount to run calculations on money owed
		outputChange = mny.GetTotalChange();

		// Getting exact cost of change, casting the int outputChange into a double for the calculation
		double realChange = static_cast<double>(outputChange) / 100;

		// Money covers the purchase
		if (outputChange > 0)
		{
			// Getting the amount of bills and coins due to the user from the two Get methods
			mny.GetBills(&twenties, &tens, &fives, &ones);
			mny.GetCoins(quarters, dimes, nickels, pennies);

			// Outputting to user how many of each denomination they owe
			cout << "After your purchase, I owe you:\n" << twenties << "-$20 bill(s)\n" << tens << "-$10 bill(s)\n" << fives << "-$5 bill(s)\n" <<
				ones << "-$1 bill(s)\n" << quarters << "-quarter(s)\n" << dimes << "-dime(s)\n" << nickels << "-nickel(s)\n" << pennies << "-penny(s)\n";
		}

		//TODO:  How do you know that outputChange is exactly 0?  Or 0, for that matter?  -2

		// Money is the exact purchase price
		else if (outputChange == 0)
		{
			cout << "You gave exact change! Thanks for the effort!\n";
		}

		// Money does not cover the purchase
		else if (outputChange < 0)
		{
			cout << "You didn't give enough money for that purchase! You still owe $" << realChange * -1 << "!\n";
		}

		// Finding out if user wants to buy another item/do another change calculation
		cout << "Would you like to buy something else?(y/n)\n";
		cin >> lpProg;
	} while (lpProg == 'y');
	cout << "Thanks for using my purchase/change calculation program!\n";
}