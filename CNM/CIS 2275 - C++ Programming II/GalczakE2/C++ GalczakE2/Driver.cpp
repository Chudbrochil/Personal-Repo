// Anthony Galczak
// agalczak@cnm.edu
// Driver.cpp

#include <iostream>
#include <string>
using namespace std;

// Including header file
#include "SimpleCalc.h"
#include "Functions.h"

int main()
{
	Header();
// Declaring variables
	char oper;
	int op1, op2;
	string output;

// Starting do while loop
	char lpProg;
	lpProg = 'y';
	do
	{
// Instantiating the simplecalc class
		SimpleCalc calc;

// Asking user to enter the operator and the numbers
		cout << "What calculation would you like to do? + - * /\n";
		cin >> oper;
		cout << "What is the first number you would like to calculate?\n";
		cin >> op1;
		cout << "What is the second number you would like to calculate?\n";
		cin >> op2;
		
// Setting the users information into simplecalc's setoperation
		calc.SetOperation(oper, op1, op2);

// Outputting the user's calculation
		output = calc.GetResults();
		cout << output;

// Asking if user wants to do another calculation
		cout << "Would you like to do another calculation? (y/n)\n";
		cin >> lpProg;
		cin.ignore();

	} while (lpProg == 'y');

// Good-bye message
	cout << "Thanks for using my calculator program!\n";
}