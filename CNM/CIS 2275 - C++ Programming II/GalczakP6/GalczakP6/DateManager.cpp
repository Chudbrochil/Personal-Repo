#include "DateManager.h"
#include <iostream>
#include <fstream>
#include <string>
using namespace std;

DateManager::DateManager()
{
	
}

bool DateManager::ReadFile()
{
	// Gather the file name from the user
	string fileName, line;
	cout << "What is the name of your file? (Dates.txt)\n";
	cin >> fileName;

	// Opening the filename that the user requested
	ifstream input(fileName);

	// If the file has been successfully opened, start reading lines into the date class array
	if (input)
	{
		// Iterator for getline and temp variables for assignments
		int i = 0, month, day, year;
		string desc, strMonth, strDay, strYear, slash;

		while (i < MAX_DATES)
		{
			// Getting 2 lines worth of input to capture into the date classes
			getline(input, strMonth, '/');
			getline(input, strDay, '/');
			getline(input, strYear);
			getline(input, desc);

			// Converting the strings captured from the line into ints for inserting into Date objects
			month = atoi(strMonth.c_str());
			day = atoi(strDay.c_str());
			year = atoi(strYear.c_str());

			// Setting the date and description into the Date objects
			myDates[i].SetDate(month, day, year, desc);
			++i;
		}
		return true;
	}
	
	// If input doesn't get opened then tell the user
	else
	{
		cout << "This file isn't valid.\n";
		return false;
	}

}

void DateManager::Menu()
{
	int choice;
	do
	{
		// Printing the 1-6 menu and collecting the int entered
		cout << "1.)Print the current list of dates.\n2.)Search for a specific date\n3.)Search for a specific event\n4.)Sort the dates in chronological order and display the list of dates\n5.)Sort the dates in occurrence in the year and display the list of dates\n6.)Return to Main\n";
		cin >> choice;

		// Error checking in case the user enters an invalid number
		if (choice > 6 || choice < 1)
		{
			cout << "This isn't a valid option";
		}
	
		// Menu options as a case/break statement
		switch (choice)
		{
		case 1:
			cout << "List of dates in current order\n";
			CurrentList();
			break;
		case 2:
			cout << "Search for a specific date\n";
			SearchForDate();
			break;
		case 3:
			cout << "Search for a specific event\n";
			SearchForEvent();
			break;
		case 4:
			cout << "Sort the dates in chronological order\n";
			SortDateWYear();
			break;
		case 5:
			cout << "Sort the dates in occurrence order(No year)\n";
			SortDate();
			break;
		case 6:
			cout << "Thank you for using my date program!\n";
			break;

		default:
			cout << "Invalid selection!";
		}

	} while (choice != 6);

}

void DateManager::Header()
{
	// Displaying a header
	cout << "\nAnthony Galczak\nProgram 6\nThis program will allow the user search for a date or an event.\nYou can also sort dates via occurrence or true date including year.\n\n";
}

void DateManager::CurrentList()
{
	// Print the list of dates in myDates array
	for (int i = 0; i < MAX_DATES; ++i)
	{
		cout << myDates[i].GetFormattedDate() << "\n";
	}
}

void DateManager::SearchForDate()
{
	// Asking user for the month and day
	string monthDay, strMonth, strDay;
	int month, day;
	cout << "What is the month and day of the date you are searching for? (Enter as MM/DD)\n";
	//cin >> monthDay;
	Date temp;

	// Separate the month and day and set them into variables
	getline(cin, strMonth, '/');
	getline(cin, strDay);

	// Converting month and day into int's
	month = atoi(strMonth.c_str());
	day = atoi(strDay.c_str());

	// Assigning month and day into the temp date class
	// The last 2 parameters in this do not matter so I am assigning incorrect amounts, we are only comparing month and day
	temp.SetDate(month, day, month, monthDay);

	// Printing the date classes that are equal to this month/day
	for (int i = 0; i < MAX_DATES; ++i)
	{
		if (temp == myDates[i])
		{
			cout << myDates[i].GetFormattedDate() << "\n";
		}
	}
	
}

void DateManager::SearchForEvent()
{
	// Asking user for the event
	string event;
	cout << "What is the name of the event you are looking for?\n";
	cin.ignore();
	getline(cin, event);
	Date temp;

	// Setting the description into the temp date class
	temp.SetDesc(event);

	// Finding out if the event that the user entered is identical to any of the array's descriptions
	for (int i = 0; i < MAX_DATES; ++i)
	{ 
		// Overloaded + operator compares descriptions
		if (temp + myDates[i])
		{
			// If the descriptions are equal or it finds a partial match then output that dates information
			cout << myDates[i].GetFormattedDate() << "\n";
		}
		
	}
	
}

void DateManager::SortDate()
{
	// Sorting the dates in a bubble sort without the year via the < > operators
	for (int i = 0; i < MAX_DATES; ++i)
	{
		for (int j = 0; j < MAX_DATES; ++j)
		{
			if (myDates[i] < myDates[j])
			{
				Date temp = myDates[i];
				myDates[i] = myDates[j];
				myDates[j] = temp;
			}
		}
	}

	// Display the sorted dates
	CurrentList();
}

void DateManager::SortDateWYear()
{
	// Sorting the dates in a bubble sort with the year via the >> << operators
	for (int i = 0; i < MAX_DATES; ++i)
	{
		for (int j = 0; j < MAX_DATES; ++j)
		{
			if (myDates[i] << myDates[j])
			{
				Date temp = myDates[i];
				myDates[i] = myDates[j];
				myDates[j] = temp;
			}
		}
	}

	// Display the sorted dates
	CurrentList();
}