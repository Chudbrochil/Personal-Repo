//Anthony Galczak
//agalczak@cnm.edu
//HotelRes.h


#include <string>
#include "Date.h"
using namespace std;

class HotelRes
{
private:
	string name;	 //name of reservation party
	int guests;	//number of guests in a room, 1 or 2
	Date arrival, departure, booking;	 //three date objects for guest data
	int numberOfNights;	 //length of stay at the spa
	double roomRate;	 //basic rate of room, not including potential discount
	double lodgingTax;	 //tax rate percentage based on the city/county/state laws
	double discountRate;	 //percentage discount for staying 5 nights or more
	string confirmationNumber;    //confirmation number based on 10001+lastname 
	string errorstring; 	//string reporting validation error
	double roomCost, tax, totalCost;
	bool bReadIn, bWrittenOut;	//shows input/output files read/written status
	string reservationDescription;

	static int resNumber;	//the number of the reservation made, declaration only


	void ReadReservationData();	//reads file with hotel reservation data
	void CalculateCostOfVisit();	 //calculates total cost to guest(s)
	void WriteConfirmationFile();	 //writes the complete confirmation file
	void ValidateReservation(); //validates arrival/departure dates
	void MakeReservation();


public:
	HotelRes(); 	//default constructor—calls ReadReservationData 
	HotelRes(string resName, int guests, Date &arr, Date &dpt, Date &book);

	void SetName(string n){ name = n; }	//reservation name	
	void SetDates(Date &arr, Date &dpt, Date& book);
	void SetNumberOfGuests(int g){ guests = g; }	//number of guests in a room

	string PresentRoomMenu(); // formatting for displaying to the form.  \r and \n
	string GetReservationDescription(){ return reservationDescription; }
};
