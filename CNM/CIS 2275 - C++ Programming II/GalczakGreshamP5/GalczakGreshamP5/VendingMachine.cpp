//Shane Gresham
//sgresham1@cnm.edu

#include "VendingMachine.h"
#include<string>
#include<sstream>
#include<iomanip>

VendingMachine::VendingMachine()
{
	int selection = 0;
	string dispenseString = "";

	// Declaring the dispenser objects including names, price and how many of each object there is
	D[0].SetStock("Annie", 1.95, 10);
	D[1].SetStock("Fizz", 1.95, 10);
	D[2].SetStock("Thresh", 3.95 , 10);
	D[3].SetStock("Jinx", 3.95, 10);
	D[4].SetStock("Sivir", 1.95, 10);
	dispenseString = bank.GetBeginningFile();

	// Checking if the file is open
	theLogger.StartLog(bank.GetBeginningFile());
	theLogger.WriteLog(bank.GetWriteInLog());
}

VendingMachine::~VendingMachine()
{
	double temp;
	temp = (10 - D[selection].GetTotal()) * D[selection].GetCost();
	theLogger.CloseLog(bank.GetReturnMoney());
}


void VendingMachine::DispenseItem()
{
	dispenseString = bank.GetBeginningFile();

	// If the selection is between 0 and 4, as there are 5 possible selections
	if (selection >= 0 && selection <= 4)
	{
		// Making sure that the amount of money paid is enough and there is more than 0 items in stock
		if (D[selection].GetCost() <= bank.GetHowMuchInput() && D[selection].GetTotal() >= 0)
		{
			D[selection].DispenseSoda();
			bank.SetSodaCost(D[selection].GetCost());
			dispenseString = bank.GetChange();
		}
		else
		{
			ReturnAllMoneyAndExit();
		}
	}

	
}
	
	
	

void VendingMachine::SetInput(int dol, int quar, int dim, int nick, int sel)
{
	
	dollar = dol;
	quarter = quar;
	dime = dim;
	nickel = nick;
	selection = sel;
	bank.SetInputMoney(dol, quar, dim, nick);
	GetMoneyInput();
	
	
	return;
}

double VendingMachine::GetMoneyInput()
{
	double temp = 0;
	temp = dollar * 1;
	temp += quarter * .25;
	temp += dime * .10;
	temp += nickel * .01;
	
	
	DispenseItem();
	
	return temp;
}

string VendingMachine::GetDispenseString()
{
	// If the cost is less than or equal to how much was put in and there is more than 0 items in stock then add a stringsream
	if (D[selection].GetCost() <= bank.GetHowMuchInput() && D[selection].GetTotal() >= 0)
	{
		stringstream ss;
		ss << D[selection].GetWrittenEntry() << "\r\n" << bank.GetWriteInLog() << "\r\n " << bank.GetReturnMoney() << "\r\n" << "Name\tStock\r\n";
		ss << D[0].GetSodaName() << "\t" << D[0].GetTotal() << "\r\n" << D[1].GetSodaName() << "\t" << D[1].GetTotal() << "\r\n" << D[2].GetSodaName() << "\t" << D[2].GetTotal() << "\r\n" << D[3].GetSodaName() << "\t" << D[3].GetTotal() << "\r\n" << D[4].GetSodaName() << "\t" << D[4].GetTotal() << "\r\n";
		dispenseString = ss.str();
	}

	// Return dispensestring
	return dispenseString;
}

string VendingMachine::ReturnAllMoneyAndExit()
{
	// Returning a string in case the user hasn't paid enough
	stringstream temp;
	temp << "Sorry you have not given enough change to cover the cost or we are out of " << D[selection].GetSodaName() << " we will give back " << dollar << " one dollar bills " << quarter << " quarters " << dime << " dimes  and " << nickel << " Nickels\r\n";
	dispenseString = temp.str();
	bank.ReturnMoney();
	

	return dispenseString;
}


