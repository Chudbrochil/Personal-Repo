
int isEven(int x)
{
    if (x & 0x1 != 0) return 0;
    else return 1;
}



int main()
{
    int evenInc = 0;
    for(int i = 0; i <= 10; ++i)
    {
        if(isEven(i))
        {
            evenInc++;
        }
    }
    return 0;
}




