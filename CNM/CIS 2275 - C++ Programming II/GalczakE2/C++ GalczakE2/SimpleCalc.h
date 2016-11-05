// Anthony Galczak
// agalczak@cnm.edu
// Circle.h

#ifndef _SIMPLECALC_H
#define _SIMPLECALC_H
#include <string>
using namespace std;

// Field and method declarations
class SimpleCalc
{
private:
	char operation;
	double operand1;
	double operand2;
	double answer;
	string results;
	string desc;
	void Calculate();
public:
	SimpleCalc();
	void SetOperation(char oper, double op1, double op2);
	string GetResults();
};
#endif