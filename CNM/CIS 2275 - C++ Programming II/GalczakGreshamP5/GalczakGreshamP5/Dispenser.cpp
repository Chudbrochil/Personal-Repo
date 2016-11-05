//Shane Gresham
//sgresham1@cnm.edu

#include "Dispenser.h"
#include <sstream>


Dispenser::Dispenser()
{
	string name = "";
	double cost = 0;
	int total = 0;
}

void Dispenser::SetStock(string r, double c, int t)
{
	name = r;
	cost = c;
	total = t;
}

string Dispenser::DispenseSoda()
{
	stringstream temp;
	temp << "You choose " << name << ", good luck in the field of battle\r\n";
	total--;

	return temp.str();
}

string Dispenser::GetWrittenEntry()
{
	stringstream lTemp;
	lTemp << "The character you chose is " << name << " at a price of $" << cost << "\r\n " << endl;
	return lTemp.str();
}

string Dispenser::GetSodaName()
{
	return name;
}

double Dispenser::GetCost()
{
	return cost;
}

int Dispenser::GetTotal()
{
	return total;
}