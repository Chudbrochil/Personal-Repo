#include <string>
#include <fstream>
#ifndef _LOGGER_H
#define _LOGGER_H
using namespace std;

class Logger
{
public:
	Logger();
	Logger::~Logger();
	void StartLog(string beginningLog);
	void WriteLog(string writeInLog);
	void CloseLog(string moneyString);
	bool IsLogOpen();

private:
	string filename;
	bool bLogOpen;
	ofstream output;
	int day, month, year, hour, min, sec;
	void Filename();
	void Time();
};

#endif