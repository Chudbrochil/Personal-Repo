#include <stdlib.h>
#include <vcclr.h>
#include <string>

using namespace System;
using namespace std;

/*String^ source ia a C++/CLI handle to a string object.  The handle
is like a reference, but since there is automatic optimization and
garbage collection, the reference does not remain pointed at the
same address.  The handle keeps track of the object wherever it is.
You can't pass a handle to a native function, so we will use pinning pointers*/

void To_string(String^ source, string &target)
{
	/*a pinning pointer is needed to "pin" the handle to the
	string object so it can be converted to a char array.
	PtrToStringChars returns  an interior pointer.
	If you pass a pointer to a native function, then it must be a
	pinning pointer; the garbage collector will not be able to
	update any pointers on the unmanaged stack frame.*/

	const int BUFFER_SIZE = 100;

	pin_ptr<const wchar_t> wch = PtrToStringChars(source);

	//Make a new char array, or C-String
	char *ch = new char[BUFFER_SIZE];

	size_t i;		//number of characters converted

	/*
	errno_t wcstombs_s(   size_t *pReturnValue,   char *mbstr,
	size_t sizeInBytes,   const wchar_t *wcstr,   size_t count);
	[out] pReturnValue
	The number of characters converted.
	[out] mbstr
	The address of a buffer for the resulting converted multibyte character string.
	[in]sizeInBytes
	The size in bytes of the mbstr buffer.
	[in] wcstr
	Points to the wide character string to be converted.
	[in] count
	The maximum number of wide characters to be stored in the mbstr buffer,
	not including the terminating null character, or _TRUNCATE.  */


	/*wcstombs converts wide-character string to multibyte string.
	If wcstombs successfully converts the multibyte string, it returns 0.
	If the conversion fails,	it returns an error code*/


	errno_t err = wcstombs_s(&i, ch, (size_t)BUFFER_SIZE, wch, (size_t)BUFFER_SIZE);
	if (err == 0)
	{
		target = ch;
	}

	//Delete the "new" char array
	delete ch;

}

/* To convert a std C++ native string to a managed string, we only need
to convert the string to a char array (.c_str()) and make a new managed
String reference out of it:

std::string phrase;

String ^ newString = gcnew String(phrase.c_str());*/

