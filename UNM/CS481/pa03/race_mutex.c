/*=========================================================*/
/* race.c
---
for playing with ECE437 */
/*=========================================================*/
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

struct
{
    int balance[2];
} Bank = {{100,100}}; //global variable defined

pthread_mutex_t shared_mutex;

void* MakeTransactions()
{
    //routine for thread execution
    int i, j, tmp1, tmp2, random_int, tempPlusRandInt, tempMinusRandInt;
    double dummy;

    for (i=0; i < 100; i++)
    {
        pthread_mutex_lock(&shared_mutex);
        random_int = (rand()%30)-15;
        tmp1 = Bank.balance[0];
        tmp2 = Bank.balance[1];
        tempPlusRandInt = tmp1 + random_int;
        tempMinusRandInt = tmp2 - random_int;

        if (tempPlusRandInt >= 0 && tempMinusRandInt >=0)
        {
            Bank.balance[0] = tempPlusRandInt;

            // spend time on purpose
            for (j=0; j < random_int*1000; j++)
            {
                dummy=2.345*8.765/1.234;
            }

            Bank.balance[1] = tempMinusRandInt;
        }
        pthread_mutex_unlock(&shared_mutex);
    }

    return NULL;
}

int main(int argc, char **argv)
{
    int i;
    void* voidptr=NULL;
    pthread_t tid[2];

    srand(getpid());
    printf("Init balances A:%d + B:%d ==> %d!\n", Bank.balance[0],Bank.balance[1],Bank.balance[0]+Bank.balance[1]);

    for (i=0; i<2; i++)
    {
        if (pthread_create(&tid[i], NULL, MakeTransactions, NULL))
        {
            perror("Error in thread creating\n");
            return(1);
        }
    }
    for (i=0; i<2; i++)
    {
        if (pthread_join(tid[i], (void*)&voidptr))
         {
             perror("Error in thread joining\n");
             return(1);
         }
    }
    printf("Let's check the balances A:%d + B:%d ==> %d ?= 200\n", Bank.balance[0],Bank.balance[1],Bank.balance[0]+Bank.balance[1]);

    return 0;
}
