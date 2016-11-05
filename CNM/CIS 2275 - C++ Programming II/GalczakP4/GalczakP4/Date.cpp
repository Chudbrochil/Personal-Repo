//File: Date.cpp

#include <string>
#include <ctime>		//obtain system date
#include <sstream>
#include "Date.h"

using namespace std;

Date::Date()
{
	//Set the Date variables to the computer's date.
	time_t rawtime;
	/*tm *OStime; */
	tm OStime;  //declaring a local struct

	errno_t err;  //declare an error code, a typedef of an int return type

	//First obtain the raw time from the op system
	time(&rawtime);

	//localtime converts this to data struct
	//containing month, day, year
	//Jan month is 0, must add 1
	//0 year is 1900, 
	//must add 1900 to obtain correct year

	/*OStime = localtime(&rawtime);  localtime is deprecated*/

	//New way, not deprecated
	err = localtime_s(&OStime, &rawtime);
	if (err == 0)
	{
		month = OStime.tm_mon + 1;
		day = OStime.tm_mday;
		year = OStime.tm_year + 1900;

		description = "Today's Date";
		DetermineLeapYear();
		CalcDayOfYear();
	}
	//month = OStime->tm_mon + 1;
	//day = OStime->tm_mday;
	//year = OStime->tm_year + 1900;

	//description = "\nToday's Date";
	//DetermineLeapYear();
	//CalcDayOfYear();
}


Date::Date(int m, int d, int y, string desc)
{
	month = m;
	day = d;
	year = y;
	description = desc;
	DetermineLeapYear();
	CalcDayOfYear();
}


void Date::SetDate(int m, int d, int y, string desc)
{
	month = m;
	day = d;
	year = y;
	description = desc;
	DetermineLeapYear();
	CalcDayOfYear();
}


string Date::GetFormattedDate()
{
	stringstream strDate;
	strDate << description;
	
	string monName[12] = {"January",	"February ","March",
		"April", "May", "June", "July", "August", 
		"September", "October", "November", "December"};


	strDate << ": " << monName[month-1] << " " << day 
			<< ", " << year;

	return strDate.str();
}

void Date::CalcDayOfYear()
{
	//set up array of days in each month
	//for non-leapyear year
	int dayCount[12] = {31,28,31,30,31,30,
		31,31,30,31,30,31};

	dayOfYear = 0;

	//add the days up to the previous month
	for(int i = 1; i < month; ++i)
	{
		dayOfYear += dayCount[i-1];

		//if adding Feb, check if leap year
		if(i == 2 && bLeap == true)
			dayOfYear += 1;
	}

	dayOfYear += day;
}

void Date::DetermineLeapYear()
{
	//A year is a leap year if it is divisible by four, 
	//unless it is a century date (i.e,  1900). 
	//If it is a century date, it is a leap year only 
	//if it is divisible by 400 (i.e., 2000).

	if(year%4 == 0 && year % 100 != 0)
		bLeap = true;
	else if(year % 400 == 0)
		bLeap = true;
	else
		bLeap = false;
}

bool Date::ValidateDate()
{
	bValid = true;

	if (day > 28 && month == 2 && bLeap != true)
		bValid = false; // february where there isn't a leap year
	else if (day > 29 && month == 2 && bLeap == true)
		bValid = false; // even with leap year, february only goes until day 29
	else if (day > 30 && (month == 4 || month == 6 || month == 9 || month == 11))
	{
		bValid = false; // verifying that the dates don't go past 30 in april, june, sept, nov
	}
	else if (month > 12) // month can't be greater than 12
	{
		bValid = false;
	}
	else if (year > 2016 || year < 2015) // years are restricted to 2015 and 2016
	{
		bValid = false;
	}

	return bValid;
}