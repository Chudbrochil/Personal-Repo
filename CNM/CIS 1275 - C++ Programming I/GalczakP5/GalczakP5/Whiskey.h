// Anthony Galczak
// agalczak@cnm.edu
// Whiskey.h

// Include Guard
#ifndef _WHISKEY_H
#define _WHISKEY_H
#endif

// Declaring Namespace
using namespace std;

// Prototypes
void WriteHeader();
string AskName();
void AskForBarrels(int *pdiam, int *pheight, int *pnumBar);
double CalcNetWhiskeyVol(int diam, int height, int numBar, double &r_totalVol);
void DetermineBottles(double vol, int &r_cases, int &r_wholeBtl, int &r_leftOver, double &r_crewTreat);
void WriteResults(string name, int diam, int height, double vol, double totalVol, int cases, int wholeBtl, int leftOver, double crewTreat);
void WriteResults(string name, int diam, int height, double vol, double totalVol, int cases, int wholeBtl, int leftOver);