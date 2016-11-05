// Anthony Galczak
// agalczak@cnm.edu
// Program2

//For cin/cout
#include <iostream>
//For Strings
#include <string>
//For Pi
const double pi = 3.141592653589793;
//Declaring Namespace
using namespace std;

int main()
{

// Outputting Program Details and Name
	cout << "Anthony Galczak \n";
	cout << "Program 2 \n";
	cout << "This program will calculate the weight of a cylinder of metal. \n\n" ;

// Setting Precision
	cout.setf(ios::fixed | ios::showpoint);
	cout.precision(2);

// Declaring Loop Variable and Starting While Loop
string lpProg;
lpProg = "y";
while (lpProg == "y")
	{

// Delaring  and Initializing Variables
	string userMetal;
	string displayMetal;
	double userDiameter, userLength;
	double metalDensity;
	double volume;
	double weight;
	userDiameter = 0;
	userLength = 0;
	volume = 0;
	weight = 0;
	
// Obtaining User Input for Metal, Diameter, Length
	cout << "What type of metal are you calculating? \n";
	cout << "Au for Gold, Pb for Lead, Ag for Silver, Al for Aluminum. \n";
	cin >> userMetal;
	cout << "What is the diameter of your cylinder? (In inches) \n";
	cin >> userDiameter;
	cout << "What is the length of your cylinder? (In inches) \n";
	cin >> userLength;

// If/Else Block for Setting The Density for the User's Metal and String Display
	if (userMetal == "Au")
	{
		metalDensity = 0.698;
		displayMetal = "Gold";
	}
	else if (userMetal == "Pb")
	{
		metalDensity = 0.410;
		displayMetal = "Lead";
	}
	else if (userMetal == "Ag")
	{
		metalDensity = 0.379;
		displayMetal = "Silver";
	}
	else if (userMetal == "Al")
	{
		metalDensity = 0.098;
		displayMetal = "Aluminum";
	}
	else
	{
		cout << "That isn't one of the metals!";
	}

// Calculation for Volume
	volume = (pi * userDiameter * userDiameter * userLength) / 4 ;

// Calculation for Weight
	weight = volume * metalDensity;

// Outputs to User 
	cout << "You chose " << displayMetal << ".\n";
	cout << "The diameter of the cylinder is " << userDiameter << " inches.\n";
	cout << "The length of the cylinder is " << userLength << " inches.\n";
	cout << "The total volume of the cylinder is " << volume << " inches3.\n";
	cout << "The total weight of the cylinder is " << weight << " pounds.\n";

// Loop for Another
	cout << "Would you like to do another calculation? (y/n)\n";
	cin >> lpProg;
	}

// Ending The Function
cout << "Thank you for using my metal weight and volume calculator! \n";
return 0;

}
