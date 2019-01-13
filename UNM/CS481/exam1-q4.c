#include <stdio.h>
#include <pthread.h>


int g = 4;

void *myThreadFun(void *varp)
{
	int *myid = (int*)varp;
	static int s = 3;
	++s;
	++g;
	printf("Thread ID: %d, Static: %d, Global: %d\n", *myid, ++s, ++g);
	return NULL;
}

int main()
{
	int i, j;
	pthread_t tid[3];
	for(i = 0; i < 3; i++)
	{
		pthread_create(&tid[i], NULL, myThreadFun, (void *)&i);
	}
	for(j = 0; j < 3; ++j)
	{
		pthread_join(tid[j], NULL);
	}
	return 0;
}
	


