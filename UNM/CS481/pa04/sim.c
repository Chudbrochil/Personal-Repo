#include <stdio.h>
#include <getopt.h>
#include <time.h>
#include <pthread.h>
#include "random437.h"

// Constants given in the prompt, global declaration.
int MAXWAITPEOPLE = 800;
int CARNUM = -1; // N
int MAXPERCAR = -1; // M


struct car_info {
	int * waiting_time_list;
	int single_car_load;
};


// calculate_time_string()
void calculate_time_string(int iteration, char* time_buf)
{
	int hour = 9 + iteration / 60;
	int minute = iteration % 60;
	int second = 0;

	char time_string[10];
	// Initializes values to 0.
	struct tm time_struct = {0};

	time_struct.tm_sec = second;
	time_struct.tm_min = minute;
	time_struct.tm_hour = hour;

	strftime(time_string, 10, "%H:%M:%S", &time_struct);

	int i;
	for(i = 0; i < 8; ++i)
	{
		time_buf[i] = time_string[i];
	}
	time_buf[8] = '\0';
}


// parse_options()
// Gathers command line arguments. This method assigns the globals
// CARNUM and MAXPERCAR.
void parse_options(int argc, char **argv)
{
	extern char *optarg;

	int c;

	while ((c = getopt (argc, argv, "N:M:")) != -1)
	{
		switch(c)
		{
			case 'N':
				// NOTE: Dangerous atoi cast, may want to do a check
				CARNUM = atoi(optarg);
				printf("CARNUM set to %d\n", CARNUM);
				break;
			case 'M':
				// NOTE: Dangerous atoi cast, may want to do a check
				MAXPERCAR = atoi(optarg);
				printf("MAXPERCAR set to %d\n", MAXPERCAR);
				break;
		}
	}

	if(CARNUM == -1 || MAXPERCAR == -1)
	{
		printf("CARNUM or MAXPERCAR wasn't set. Exit'ing.\n");
		exit(1);
	}

}


// output_figure_data()
// Writing out data useful for plotting. Arrivals, rejects, waiting list.
// Also generates the end of day report for output.
void output_figure_data(int arrivals_list[], int rejects_list[], int waiting_list[], int waiting_time_sum, int time_steps, FILE* ride_status_file)
{
	// Open a file, write a header and then write all of the relevant plot data.
	FILE *plot_data_file = fopen("plot_data.csv", "w");
	int i;
	int total_num_arrived = 0;
	int total_num_rode = 0;
	int total_num_rejected = 0;
	int max_waiting_line = 0;
	int max_waiting_iteration = 0;
	char time_buf[10];

	fprintf(plot_data_file, "Arrivals, Rejects, Waiting Time\n");
	for(i = 0; i < time_steps; ++i)
	{
		// Writing plot data
		fprintf(plot_data_file, "%d,%d,%d\n", arrivals_list[i], rejects_list[i], waiting_list[i]);

		// Finding our totals
		total_num_arrived += arrivals_list[i];
		total_num_rejected += rejects_list[i];

		// If we haven't already hit the maximum wait, look for highest waiting line.
		if(max_waiting_line < MAXWAITPEOPLE)
		{
			if(waiting_list[i] > max_waiting_line)
			{
				max_waiting_line = waiting_list[i];
				max_waiting_iteration = i;
			}
		}
	}

	// We still need to reject all the people waiting when Jurassic park closes...
	total_num_rejected += ((waiting_list[599]) - (arrivals_list[599] - rejects_list[599]));
	total_num_rode = total_num_arrived - total_num_rejected;
	int average_waiting_time = waiting_time_sum / total_num_rode;

	// Generate end of day report
	printf("\n-------------------------\n");
	printf("    End of day report\n");
	printf("-------------------------\n");
	printf("Total passenger arrivals: %d\n", total_num_arrived);
	printf("Total passengers that rode the ride: %d\n", total_num_rode);
	printf("Total passengers turned away because of wait: %d\n", total_num_rejected);
	printf("Average waiting time: %d minutes\n", average_waiting_time);
	calculate_time_string(max_waiting_iteration, time_buf);
	printf("Highest waiting line: %d passengers, occured at %s",
					max_waiting_line, time_buf);
	printf("\n\n");

	// Also write end of day report to ride_status.txt
	fprintf(ride_status_file, "\n-------------------------\n");
	fprintf(ride_status_file, "    End of day report\n");
	fprintf(ride_status_file, "-------------------------\n");
	fprintf(ride_status_file, "Total passenger arrivals: %d\n", total_num_arrived);
	fprintf(ride_status_file, "Total passengers that rode the ride: %d\n", total_num_rode);
	fprintf(ride_status_file, "Total passengers turned away because of wait: %d\n", total_num_rejected);
	fprintf(ride_status_file, "Average waiting time: %d minutes\n", average_waiting_time);
	fprintf(ride_status_file, "Highest waiting line: %d passengers, occured at %s",
					max_waiting_line, time_buf);
	fprintf(ride_status_file, "\n\n");

	// Outputting the table data for this run
	float rejection_ratio = 0.0;
	if(total_num_rejected > 0)
	{
		rejection_ratio = ((float)total_num_arrived) / ((float)total_num_rejected);
	}
	FILE *table_data_file = fopen("table_data.csv", "w");
	fprintf(table_data_file, "%d,%d,%d,%d,%f,%d,%d,%s\n", CARNUM, MAXPERCAR, total_num_arrived, total_num_rejected,
					rejection_ratio, average_waiting_time, max_waiting_line, time_buf);

	fclose(plot_data_file);
	fclose(table_data_file);
}


// gen_arrivals()
// Generate a number of passengers arrival per minute.
int gen_arrivals(int iteration)
{
	int mean_arrivals = 0;

	// We are given what the mean arrivals are for given time periods.
	// 0-119 25pp/min, 120-299 45pp/min, 300-419 35pp/min, 420-600 25pp/min
	if(iteration < 120) mean_arrivals = 25;
	else if(iteration < 300) mean_arrivals = 45;
	else if(iteration < 420) mean_arrivals = 35;
	else mean_arrivals = 25;

	// Feed the mean arrivals into poissonRandom to get a random num of passengers.
	return(poissonRandom(mean_arrivals));
}


// run_car()
// Method that is used to load and run a single car through a tour.
// This will ultimately be called in a thread creation.
void* run_car(void *car_info_instance)
{
	int i;
	struct car_info *car = car_info_instance;

	for(i = 0; i < MAXWAITPEOPLE; ++i)
	{
		// Shift the line by a car load
		if(i < MAXWAITPEOPLE - car->single_car_load)
		{
			car->waiting_time_list[i] = car->waiting_time_list[i + car->single_car_load];
		}
		// Re-initializing new waiting list spots from right as -1's.
		else
		{
			car->waiting_time_list[i] = -1;
		}
	}
}


void main(int argc, char **argv)
{
	parse_options(argc, argv);

	FILE *ride_status_file = fopen("ride_status.txt", "w");

	int time_steps = 600;

	// Making lists to eventually create plots of the data.
	int arrivals_list[time_steps];
	int rejects_list[time_steps];
	int waiting_list[time_steps];

	// Iteration variables
	int num_arrivals = 0;
	int num_waiting = 0;
	int single_car_load = 0;
	int i, j, k;
	pthread_t tid;
	char time_buf[10];

	// Making a waiting time list so that we can get an average waiting time.
	int waiting_time_list[MAXWAITPEOPLE];
	for(j = 0; j < MAXWAITPEOPLE; ++j)
	{
		waiting_time_list[j] = -1;
	}
	int waiting_time_sum = 0;
	int num_to_add_to_waiting = -1;

	// Iterate over 600 timesteps, this maps 1 to 1 with 600 minutes from 9AM-7PM
	for(i = 0; i < time_steps; ++i)
	{
		// Now, we have to declare that people in the waiting line have "waited"
		for(j = 0; j < MAXWAITPEOPLE; ++j)
		{
			if(waiting_time_list[j] > -1)
			{
				waiting_time_list[j]++;
			}
		}

		num_arrivals = gen_arrivals(i);

		// Adding people into waiting list
		num_waiting += num_arrivals;

		// If we added over the max, we have to reject them.
		int num_rejected = 0;
		if(num_waiting > MAXWAITPEOPLE)
		{
			num_rejected = num_waiting - MAXWAITPEOPLE;
			num_waiting = MAXWAITPEOPLE;
		}

		// Putting actual people for waiting list into waiting_time_list
		num_to_add_to_waiting = num_arrivals - num_rejected;
		for(j = 0; j < MAXWAITPEOPLE; ++j)
		{
			if(waiting_time_list[j] == -1)
			{
				waiting_time_list[j] = 0;
				num_to_add_to_waiting--;

				if(num_to_add_to_waiting == 0) break;
			}
		}

		arrivals_list[i] = num_arrivals;
		waiting_list[i] = num_waiting;
		rejects_list[i] = num_rejected;

		calculate_time_string(i, time_buf);
		fprintf(ride_status_file, "%d. Arrivals: %d, Rejected: %d Wait-Line: %d at %s\n", \
						i, num_arrivals, num_rejected, num_waiting, time_buf);

		// Send people on tours, i.e. reduce waiting list.
		for(j = 0; j < CARNUM; ++j)
		{
			// If there are people waiting to ride, send a car.
			if(num_waiting > 0)
			{
				// Take the min(num_waiting, MAXPERCAR) to send in a car.
				single_car_load = num_waiting < MAXPERCAR ? num_waiting : MAXPERCAR;

				for(k = 0; k < single_car_load; ++k)
				{
					if(k < single_car_load)
					{
						waiting_time_sum += waiting_time_list[k];
					}
				}

				// Creating a structure that contains all the information needed to
				// load a car with passengers and adjust the waiting list appropriately.
				struct car_info *car_info_instance = (struct car_info *)malloc(sizeof(struct car_info));
				car_info_instance->waiting_time_list = waiting_time_list;
				car_info_instance->single_car_load = single_car_load;

				// Sending a car on the tour via a thread.
				pthread_create(&tid, NULL, run_car, (void *) car_info_instance);
				pthread_join(tid, NULL);

				num_waiting -= single_car_load;
			}

		}

	}

	output_figure_data(arrivals_list, rejects_list, waiting_list, waiting_time_sum, time_steps, ride_status_file);
	fclose(ride_status_file);
}
