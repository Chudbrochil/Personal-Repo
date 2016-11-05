#pragma once
#include <string>
#ifndef _DISPENSER_H
#define _DISPENSER_H

using namespace std;

class Dispenser
{
private:
	string name;
	double cost;
	int total; // number of characters

public:
	Dispenser();
	void SetStock(string r, double c, int t);
	string DispenseSoda();
	string GetWrittenEntry();
	string GetSodaName();
	double GetCost();
	int GetTotal();
};

#endif