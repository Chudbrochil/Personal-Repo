#include "Enigma.h"

using namespace std;

class ShiftyEnigma : public Enigma
{

public:
	ShiftyEnigma();
	void SetMessage(string m, int k);
	void SetMessage(string m);
	void SetCodedMessage(string m, int k);

private:
	void Encode();
	void Decode();
};

