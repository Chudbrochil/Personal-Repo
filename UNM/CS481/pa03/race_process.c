/*=========================================================*/
/* race.c
---
for playing with ECE437 */
/*=========================================================*/
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <sys/ipc.h>
#include <unistd.h>
#include <sys/shm.h>

typedef struct
{
    int balance[2];
} bank_t;
bank_t* Bank;


void* MakeTransactions()
{
    //routine for thread execution
    int i, j, tmp1, tmp2, random_int, tempPlusRandInt, tempMinusRandInt;
    double dummy;

    for (i=0; i < 100; i++)
    {
        random_int = (rand()%30)-15;
        tmp1 = Bank->balance[0];
        tmp2 = Bank->balance[1];
        tempPlusRandInt = tmp1 + random_int;
        tempMinusRandInt = tmp2 - random_int;

        if (tempPlusRandInt >= 0 && tempMinusRandInt >=0)
        {
            Bank->balance[0] = tempPlusRandInt;

            // spend time on purpose
            for (j=0; j < random_int*1000; j++)
            {
                dummy=2.345*8.765/1.234;
            }

            Bank->balance[1] = tempMinusRandInt;
        }
    }

    return NULL;
}

int main(int argc, char **argv)
{
    // Making the shared memory segment
    key_t key = ftok("race_process.c", 0);
    int shared_memory_id = shmget(key, sizeof(Bank), IPC_CREAT | 0666);
    Bank = (bank_t *)shmat(shared_memory_id, NULL, 0);

    Bank->balance[0] = 100;
    Bank->balance[1] = 100;

    srand(getpid());
    printf("Init balances A:%d + B:%d ==> %d!\n", Bank->balance[0],Bank->balance[1],Bank->balance[0]+Bank->balance[1]);

    if(fork() == 0)
    {
      MakeTransactions();
    }
    else
    {
      MakeTransactions();
    }

    printf("Let's check the balances A:%d + B:%d ==> %d ?= 200\n", Bank->balance[0],Bank->balance[1],Bank->balance[0]+Bank->balance[1]);

    return 0;
}
