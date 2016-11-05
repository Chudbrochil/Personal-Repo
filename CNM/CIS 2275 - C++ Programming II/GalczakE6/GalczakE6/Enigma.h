// Anthony Galczak
// agalczak@cnm.edu
// Enigma.h

#include <string>
using namespace std;

#ifndef _ENIGMA_H
#define _ENIGMA_H

class Enigma
{
private:
	string message;
	string codedMessage;
	int key;
	void Encode();
	void Decode();

public:
	Enigma();
	void SetMessage(string mess, int k);
	void SetMessage(string mess);
	void SetCodedMessage(string codedM, int key);
	string GetCodedMessage(){ return codedMessage; }
	string GetDecodedMessage(){ return message; }
	int GetKey(){ return key; }
};


#endif