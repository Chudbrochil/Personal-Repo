#include <stdio.h> // For printf
#include <sys/time.h> // For getrusage
#include <sys/resource.h> // For getrusage
#include <math.h> // For exp()

int main(int argc, char** argv){
	double dum, usertime, systime;
	struct rusage r;
	struct timeval u_start, s_start, u_end, s_end;

	getrusage(RUSAGE_SELF, &r);
	u_start = r.ru_utime;
	s_start = r.ru_stime;

	for(int i = 0; i < 1000000000; i++)
	{ 
		dum = i * exp(0.5) + i; 
	}

	getrusage(RUSAGE_SELF, &r);
	u_end = r.ru_utime;
	s_end = r.ru_stime;
	usertime = u_end.tv_sec - u_start.tv_sec;
	systime = s_end.tv_sec - s_start.tv_sec;

	printf("User time: %fus\n", usertime);
	printf("System time: %fus\n", systime);
	
	return 0;
}




