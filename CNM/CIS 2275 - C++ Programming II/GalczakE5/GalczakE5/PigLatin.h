#include <string>
using namespace std;

class PigLatin
{
public:
	PigLatin();
	PigLatin(string phrase);
	bool IsValid();
	void SetSentence(string phrase);
	string GetTranslation();

private:
	void Validate();
	void Translate();
	string translated, needTrans;
	bool phraseValid;
};

