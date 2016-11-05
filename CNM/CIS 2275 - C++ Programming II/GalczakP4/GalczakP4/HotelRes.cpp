//Anthony Galczak
//agalczak@cnm.edu
//HotelRes.cpp

#include "HotelRes.h"
#include "Date.h"
#include <fstream>
#include <iostream>
#include <string>
#include <sstream>
using namespace std;
#define FILE_IN "ResData.txt"


HotelRes::HotelRes()
{
	// Variable initialization

	//TODO:  Where is the variable initialization?  -5
	errorstring = "";
	ReadReservationData();
}


HotelRes::HotelRes(string resName, int party, Date &arr, Date &dep, Date &book) :name(resName), guests(party), arrival(arr), departure(dep), booking(book)
{
	// Initialize the rest of the variables

	//TODO:  Need to initialize here, too  -2
	ReadReservationData();
	MakeReservation();
}

int HotelRes::resNumber = 10000;

void HotelRes::SetDates(Date &arr, Date &dep, Date &book)
{
	arrival = arr;
	departure = dep;
	booking = book;
}


void HotelRes::MakeReservation()
{
	ValidateReservation();

	if (errorstring == "OK")
	{
		resNumber++;
		CalculateCostOfVisit();
		WriteConfirmationFile();
	}
	else
		reservationDescription = errorstring;
}


void HotelRes::ValidateReservation()
{
	if (arrival.ValidateDate() == true && departure.ValidateDate() == true)
	{
		// check order of dates, make sure that the departure is later than the arrival
		if (arrival.GetYear() > departure.GetYear())
		{
			errorstring = "Invalid Departure";
		}
		else if (arrival.GetMonth() > departure.GetMonth())
		{
			errorstring = "Invalid Departure";
		}
		else if (arrival.GetMonth() == departure.GetMonth() && arrival.GetDay() > departure.GetDay())
		{
			errorstring = "Invalid Departure";
		}
		else
		{
			errorstring = "OK";
		}
	}
	else
	{
		errorstring = "Invalid Date";
	}
}

void HotelRes::ReadReservationData()
{
	// reading in the resdata.txt file
	ifstream input(FILE_IN);

	// If the file didn't open then don't do anything but set the flag to false, otherwise capture data.
	if (!input)
	{
		bReadIn = false;
	}
	else
	{
		bReadIn = true;
		input >> roomRate;
		input >> lodgingTax;
		input >> discountRate;
	}
	
}

void HotelRes::CalculateCostOfVisit()
{
	// Finding the amount of days they are staying, this will need to be bigger
	numberOfNights = departure.GetDayOfYear() - arrival.GetDayOfYear();

	// Calculating to see if they get a discountrate at 5 days or above
	if (numberOfNights >= 5)
	{
		roomCost = (roomRate * (1 - (discountRate/100))) * numberOfNights;
	}
	else
	{
		roomCost = roomRate * numberOfNights;
	}

	// Calculating total tax for all nights stayed
	tax = roomCost * (lodgingTax/100);

	// Calculating grand total based on total room cost (all nights stayed) and tax (all nights stayed)
	totalCost = roomCost + tax;

	//TODO:  Need to include numbet of guests  -2
}

void HotelRes::WriteConfirmationFile()
{
	// Creating confrimation number
	int spaceDelim;
	string lastNm;
	char cstrNm[15], char1, char2;
	
	// Finding where the space is, i.e. where the last name is located
	spaceDelim = name.find(' ');

	// Splitting the two strings and getting just the last name
	lastNm = name.substr(spaceDelim);

	// Converting the last name string into a char array
	strcpy_s(cstrNm, lastNm.c_str());
	
	// Converting last name char's to uppercase
	char1 = toupper(cstrNm[1]);
	char2 = toupper(cstrNm[2]);

	stringstream ss2;

	ss2 << resNumber << char1 << char2;
	confirmationNumber = ss2.str();

	// Capturing stringstream with all the output information
	stringstream ss;
	ss << "Welcome to the C++ Spa and Resort: " << booking.GetFormattedDate() << "\r\nGuest: " << name << "  Confirmation Number: " <<
		confirmationNumber << "\r\n" << arrival.GetFormattedDate() << "  " << departure.GetFormattedDate() << "\r\nNumber of Nights: " << numberOfNights <<
		"  Number of Guests: " << guests;
	if (numberOfNights >= 5)
	{
		ss << "\r\nRoom Cost (with " << discountRate << "% discount): $" << roomCost;
	}
	else
	{
		ss << "\r\nRoom Cost (no discount): $" << roomCost;
	}

	ss << "\r\nLodging Tax: $" << tax << "\r\nTotal Cost: $" << totalCost;

	// Declaring monName
	string monName[12] = { "January", "February ", "March",
		"April", "May", "June", "July", "August",
		"September", "October", "November", "December" };

	int displayMonth = (arrival.GetMonth() - 1);

	ss << "\r\nWe look forward to seeing you in " << monName[displayMonth];

	// Inputting stringstream into reservationdescript
	reservationDescription = ss.str();

	// Adding .txt to the ss to convert into the confirmation#
	ss2 << ".txt";
	confirmationNumber = ss2.str();

	// Creating a file
	ofstream output;
	
	// If the file is created then output my information into it, otherwise just set bwrittenout to false
	if (!output)
	{
		bWrittenOut = false;
	}
	else
	{
		bWrittenOut = true;
		output.open(confirmationNumber, ofstream::out | ofstream::app);
		output << ss.str();
	}

	output.close();
}