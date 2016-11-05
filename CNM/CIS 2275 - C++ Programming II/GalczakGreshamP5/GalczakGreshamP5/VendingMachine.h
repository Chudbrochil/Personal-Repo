#pragma once
#ifndef _VENDINGMACHINE_H
#define _VENDINGMACHINE_H
#include <string>
#include "Dispenser.h"
#include "Logger.h"
#include "MoneyCtr.h"

using namespace std;

class VendingMachine{
private:
	int dollar, quarter, dime, nickel;
	MoneyCtr bank;
	Dispenser D[5];
	Logger theLogger;
	int selection;//set by form
	string dispenseString;//string set to form
	void DispenseItem();

public:
	VendingMachine();
	~VendingMachine();
	void SetInput(int dol, int q, int dim, int nick, int sel);
	double GetMoneyInput();
	string GetDispenseString();
	string ReturnAllMoneyAndExit();
};

#endif