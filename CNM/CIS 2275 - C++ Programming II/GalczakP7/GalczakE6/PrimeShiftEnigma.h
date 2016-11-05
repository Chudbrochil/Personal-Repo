#include "Enigma.h"

using namespace std;

class PrimeShiftEnigma : public Enigma
{
public:
	PrimeShiftEnigma();
	void SetMessage(string m, int k);
	void SetMessage(string m);
	void SetCodedMessage(string m, int k);

private:
	int primes[19];
	void Encode();
	void Decode();
	bool IsPrime(int num);

};

