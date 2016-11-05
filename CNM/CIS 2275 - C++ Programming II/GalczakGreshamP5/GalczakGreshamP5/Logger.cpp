#include "Logger.h"
#include <string>
#include <fstream>
#include <ctime>
#include <sstream>
using namespace std;

Logger::Logger()
{
	Filename();
	
	// Opening the file with the filename that was generated for today's date
	output.open(filename);

	// Checking if file is open
	if (!output)
	{
		bLogOpen = false;
	}
	else
	{
		bLogOpen = true;
	}

}

Logger::~Logger()
{
	// Opening the log in notepad
	string openfile = "notepad.exe " + filename;
	system(openfile.c_str());
}

void Logger::StartLog(string aString)
{
	output << aString << "\n";
}

void Logger::WriteLog(string writeInLog)
{
	output << writeInLog << "\n";
}

void Logger::CloseLog(string moneyString)
{
	output << moneyString << "\n";
	output.close();
}

bool Logger::IsLogOpen()
{
	return bLogOpen;
}

void Logger::Filename()
{
	// Calling time so that I can get the month day and year. This will also allow me to do the timestamp within the constructor
	Time();

	// Making filename into the "Log_month-day-year.txt" format
	stringstream ss;
	ss << "Log_" << month << "-" << day << "-" << year << ".txt";
	filename = ss.str();
}

void Logger::Time()
{
	// Getting the time
	time_t t = time(0);
	struct tm * now = localtime(&t);

	// Getting all the individual time integers from the tm struct
	sec = now->tm_sec;
	min = now->tm_min;
	hour = now->tm_hour;
	day = now->tm_mday;
	month = now->tm_mon + 1;
	year = now->tm_year + 1900;
}