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

// Overloaded operators
bool Date::operator + (Date d2)
{
	// Finding out if a partial match is made when comparing descriptions
	int found = d2.description.find(description);

	// If the descriptions are equal then the operator returns true
	if (description == d2.description)
	{
		return true;
	}
	else if (found > -1)
	{
		return true;
	}
	else { return false; }
}

bool Date::operator > (Date d2)
{
	// If the left hand day of the year is bigger than the right hand then the operator returns true
	if (dayOfYear > d2.dayOfYear)
	{
		return true;
	}
	else { return false; }
}

bool Date::operator < (Date d2)
{
	// If the right hand day of the year is bigger than the left hand then the operator returns true
	if (dayOfYear < d2.dayOfYear)
	{
		return true;
	}
	else { return false; }
}

bool Date::operator >> (Date d2)
{
	// If the left hand year is greater than the right hand year return true
	if (year > d2.year)
	{
		return true;
	}
	// If the years are the same, compare the day
	else if (year == d2.year)
	{
		if (dayOfYear > d2.dayOfYear)
		{
			return true;
		}
		else { return false; }
	}
	else { return false; }
}

bool Date::operator << (Date d2)
{
	// If the right hand year is greater than the left hand year return true
	if (year < d2.year)
	{
		return true;
	}
	// If the years are the same, compare the day
	else if (year == d2.year)
	{
		if (dayOfYear < d2.dayOfYear)
		{
			return true;
		}
		else { return false; }
	}
	else { return false; }
}

bool Date::operator == (Date d2)
{
	// If both days of the year are the same the operator returns true
	if (day == d2.day && month == d2.month)
	{
		return true;
	}
	else { return false; }
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