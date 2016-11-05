#include <string>
#ifndef _MONEYCTR_H
#define _MONEYCTR_H
using namespace std;

class MoneyCtr
{
private:
	// Bank's change that is held
	int bNickels, bDimes, bQuarters, bDollars;

	// User input variables for coins from the nuds
	int uNickels, uDimes, uQuarters, uDollars;

	// User return variables for change
	int rDollars, rQuarters, rDimes, rNickels;

	// Class Vars
	double totalMoney;
	double change;
	double inputAmount;
	double purchaseAmount;
	string myChange;

	// Class Methods
	void MakeChange();
	void CalcTotalMoney();
	void CalcInputAmount();

public:
	MoneyCtr();

	// Public Methods
	void SetInputMoney(int dol, int quar, int dim, int nick);
	void SetSodaCost(double purAmt);
	string GetBeginningFile();
	string GetWriteInLog();
	double GetHowMuchInput();
	// Constructor
	string GetReturnMoney();
	double GetChange();
	void ReturnMoney();

};

#endif