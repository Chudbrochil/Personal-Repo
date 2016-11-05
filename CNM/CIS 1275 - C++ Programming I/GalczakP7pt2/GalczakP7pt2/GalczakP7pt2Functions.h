// Anthony Galczak
// agalczak@cnm.edu
// P7Galczakpt2Functions.h

// Including string
#include <string>

// Include Guard
#ifndef _GALCZAKP7PT2FUNCTIONS_H
#define _GALCZAKP7PT2FUNCTIONS_H

// Namespace
using namespace std;

// Prototypes
void WriteHeader();
bool Read(string countriesArr[], double data2008Arr[], double data2009Arr[], double data2010Arr[], 
		  double data2011Arr[], double data2012Arr[]);
void Analyze(string countriesArr[], double improveArr[], double highestArr[], double data2008Arr[], 
			 double data2009Arr[], double data2010Arr[], double data2011Arr[], double data2012Arr[], 
			 string countriesArr2[], double &improved, string &countryimp, double &average);
void Write(double &improved, string &countryimp, double &average, string countriesArr2[], 
		   double highestArr[], string &userFile);

// Ending Include Guard
#endif