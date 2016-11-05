#include "Date.h"
const int MAX_DATES = 15;

class DateManager
{
public:
	DateManager();
	bool ReadFile();
	void Menu();
	void Header();

private:
	Date myDates[MAX_DATES];
	void CurrentList();
	void SearchForDate();
	void SearchForEvent();
	void SortDate();
	void SortDateWYear();
};

