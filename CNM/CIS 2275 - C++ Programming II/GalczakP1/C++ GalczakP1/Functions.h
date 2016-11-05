// Anthony Galczak
// agalczak@cnm.edu
// Functions.h

#ifndef _FUNCTIONS_H
#define _FUNCTIONS_H

// Defining constants
int const SIZE = 100;
double const DELTA_T = 0.1;
double const G = -9.8;
double const PI = 3.14159265359;
using namespace std;

// Prototypes
void Header();
void AskForInitialValues(double &vel0, double &angle);
void Shoot(double vel0, double angle, double pos[SIZE][2], int &total);
void Move(double velX, double velY, double *posX, double *posY);
bool WriteData(double pos[SIZE][2], string &fName, int total);

// Ending include guard
#endif