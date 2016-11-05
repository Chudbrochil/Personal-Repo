// Anthony Galczak0
// agalczak@cnm.edu
// MoneyChanger.h

#ifndef _MONEYCHANGER_H
#define _MONEYCHANGER_H

using namespace std;

class MoneyChanger
{
private:
	void ChangeMaker();
	double purchase;
	double money;
	int change;
	int mTwenties, mTens, mFives, mOnes, mQuarters, mDimes, mNickels, mPennies;

public:

	MoneyChanger();
	void SetCash(double purchasePrice, double moneyGiven);
	int GetTotalChange();
	void GetBills(int *twenties, int *tens, int *fives, int *ones);
	void GetCoins(int &quarters, int &dimes, int &nickels, int &pennies);
};

#endif