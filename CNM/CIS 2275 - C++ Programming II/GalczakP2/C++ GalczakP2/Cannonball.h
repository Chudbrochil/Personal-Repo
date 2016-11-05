// Anthony Galczak
// agalczak@cnm.edu
// Cannonball.h

#ifndef _CANNONBALL_H
#define _CANNONBALL_H
#include <string>
using namespace std;

// Constant declarations
static int const SIZE = 100;
double const DELTA_T = 0.1;
double const G = -9.8;
double const PI = 3.14159265359;

// Field and method declarations
class Cannonball
{
private:
	// Field declarations
	double pos[SIZE][2];
	double posX;
	double posY;
	double velX;
	double velY;
	double vel0;
	double angle;
	int total;
	bool bFileOk;
	void Move();
	void Shoot();

public:
	Cannonball();
	void SetInitialValues(double initVel, double angle);
	bool WritePositionData(string fileName);
	string GetPositionData();
};
#endif