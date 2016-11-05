// Anthony Galczak
// agalczak@cnm.edu
// GalczakP4.h

// Include Guard
#ifndef _GALCZAKP4_H
#define _GALCZAKP4_H
#endif

// Declaring Namespace
using namespace std;

// Prototypes
void WriteHeader();
double AskPitcherRadius();
double AskPitcherHeight();
double AskPebbleDiameter();
double AskBeakLength();
int CalcPebbles(double pitcherRadius, double pitcherHeight, double pebbleDiameter, double beakLength);
void ShowResults(double pitcherRadius, double pitcherHeight, double pebbleDiameter, double beakLength, int numPebbles);
