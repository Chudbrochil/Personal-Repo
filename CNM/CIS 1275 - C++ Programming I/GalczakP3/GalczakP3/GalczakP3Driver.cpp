// Anthony Galczak
// agalczak@cnm.edu
// GalczakP3Driver.cpp

//For cins/couts
#include <iostream>
//For Vectors
#include <vector>
//For Strings
#include <string>
//For Absolute Value/fabs and floor
#include <math.h>
//Declaring Namespace
using namespace std;

//Starting Main
int main()
{

// Outputting Program Details and Name
cout << "Anthony Galczak \n";
cout << "Program 3 \n";
cout << "This program will calculate your travel time and your weight on ";
cout << "another planet.\n\n";

// Setting Vector and Implementation for Planets
vector<string> vecPlanets(0);
vecPlanets.push_back("mercury");
vecPlanets.push_back("venus");
vecPlanets.push_back("earth");
vecPlanets.push_back("mars");
vecPlanets.push_back("jupiter");
vecPlanets.push_back("saturn");
vecPlanets.push_back("uranus");
vecPlanets.push_back("neptune");

// Setting Vector and Implementation for Distance from the Sun
vector<double> vecDistance(0);
vecDistance.push_back(36);
vecDistance.push_back(67);
vecDistance.push_back(93);
vecDistance.push_back(141);
vecDistance.push_back(483);
vecDistance.push_back(886);
vecDistance.push_back(1782);
vecDistance.push_back(2793);

// Setting Vector and Implementation for Planet Gravity
vector<double> vecGravity(0);
vecGravity.push_back(0.27);
vecGravity.push_back(0.86);
vecGravity.push_back(1.00);
vecGravity.push_back(0.37);
vecGravity.push_back(2064);
vecGravity.push_back(1.17);
vecGravity.push_back(0.92);
vecGravity.push_back(1.44);

// Starting do while loop so you can run program again
char lpProg;
lpProg = 'y';
do
	{

// Setting Precision
	cout.setf(ios::fixed | ios::showpoint);
	cout.precision(2);

// Declaring User's Variables and Initializing
	string userName, userPlanet;
	double userWeight, userSpeed, calcWeight, calcDistance, years, days, hours;
	int glob = 0;
	int calcHrs = 0;
	years = 0;
	days = 0;
	hours = 0;
	userWeight = 0;
	userSpeed = 0;
	calcWeight = 0;
	calcDistance = 0;

// Obtaining User's Information
	cout << "What is your name?\n";
	cin >> userName;
	cout << "What is your Earth weight? (Don't be shy!)\n";
	cin >> userWeight;
	cout << "How fast would you like to go? (Enter as mph)\n";
	cin >> userSpeed;
	cout << "What planet would you like to visit? (Lowercase please)\n";
	cin >> userPlanet;

// Calculation for Assigning Vector Positions, This assigns Variable Glob
// to the User's Planet's Element
	for(int i = 0; i < vecPlanets.size(); ++i)
	{
		if (userPlanet == vecPlanets.at(i))
		{
			glob = i;
		}
	}

// Calculation for User's Weight and User's Distance to Planet
	calcWeight = vecGravity[glob] * userWeight;
	calcDistance = fabs((vecDistance[glob] - 93)*1000000);
	calcHrs = calcDistance / userSpeed;

// Calculation for Time to Travel to the Planet
	years = calcHrs/8760;
	days = calcHrs % 8760/24;
	hours = calcHrs % 8760 % 24;

// Output to User
	cout << "Hello, " << userName << "!\n";
	cout << "You chose to travel to " << userPlanet << "!\n";
	cout << "You entered your Earth weight as " << userWeight << "lbs.\n";
	cout << "Your weight on " << userPlanet << " is " << calcWeight << "lbs.\n";
	cout << "Your distance from Earth to " << userPlanet << " is ";
	cout << calcDistance << " miles.\n";
	cout << "It will take you " << years << " years, " << days << " days, ";
	cout << hours << " days.\n";

// While Loop Would You Like To Do Another?
	cout << "Would you like to travel to another planet? (y/n)";
	cin >> lpProg;
	} while (lpProg == 'y');

// Completing Main
cout << "Thank you for using my space travel program!\n";
return 0;

}