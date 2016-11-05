// Anthony Galczak
// agalczak@cnm.edu
// SimpleCalc.cpp

#include <iostream>
#include <string>
// for stringstream
#include <sstream>
#include "SimpleCalc.h"
using namespace std;

// Default constructor
SimpleCalc::SimpleCalc()
{
	operation = ' ';
	operand1 = 0;
	operand2 = 1;
	answer = 0;
	results = ' ';
	desc = ' ';
}

// Method to set user's ops and oper into class variables, also if/else for divide by 0 exception
void SimpleCalc::SetOperation(char oper, double op1, double op2)
{
	operation = oper;
	operand1 = op1;
	operand2 = op2;

	// If statement to check if operation is division and op2 is 0, throw an error
	if (operation == '/' && operand2 == 0)
	{
		cout << "Cannot divide by zero!\n";
	}
	// Exception handling for if the operation isn't set to  + - * or /
	else if (operation != '+' && operation != '-' && operation != '*' && operation != '/')
	{
		cout << "That is not a valid operator!\n";
	}
	else
	{
		Calculate();
	}
}

// Calculation method based on what the operation is
void SimpleCalc::Calculate()
{
	if (operation == '+')
	{
		answer = operand1 + operand2;
		desc = "Addition";
	}
	else if (operation == '-')
	{
		answer = operand1 - operand2;
		desc = "Subtraction";
	}
	else if (operation == '*')
	{
		answer = operand1 * operand2;
		desc = "Multiplication";
	}
	else if (operation == '/')
	{
		answer = operand1 / operand2;
		desc = "Division";
	}
}

// Method for outputting the results to the user
string SimpleCalc::GetResults()
{
// Creating a stringstream to capture all my output data
	stringstream ss;
	ss << "Your operation is: " << desc << "\n";
	ss << operand1 << operation << operand2 << "=" << answer << "\n";
// Returning the stringstream as a converted string	
	return ss.str();
}