#include "DateManager.h"
#include <iostream>
int main()
{
	// Instantiating a new object and reading the file Dates.txt
	DateManager manager;

	// Cout'ing the header
	manager.Header();

	// Asking user for a file and then opening that file
	bool bOk = manager.ReadFile();

	// If the file opened then display the menu
	if (bOk)
	{
		manager.Menu();
	}
	else
	{
		cout << "No file read";
	}

	return 0;
}