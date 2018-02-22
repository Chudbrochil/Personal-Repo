#include <stdio.h>

int main()
{
    int myInt = 0x87;
    printf("%x - %x", (myInt, myInt >> 2));


    int newInt = 0x87 >>> 2;
    printf("%x", newInt);

}




