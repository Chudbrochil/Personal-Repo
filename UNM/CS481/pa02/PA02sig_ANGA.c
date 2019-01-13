// Anthony Galczak - WGalczak@gmail.com - agalczak@unm
// CS481 - Operating Systems Project 2

#include <stdio.h> // for printf
#include <signal.h> // for SIGINT, SIGKILL, etc.
#include <stdlib.h> // for exit()

// Keeping track of whether or not the child is running (STOPPED or RUNNING)
static volatile int childRunning = 1;
pid_t childPid = -1;


// signalHandler()
// Catches SIGINT(2) and SIGTSTP(20). On a SIGINT, it kills the child process
// and exits the program. On a SIGTSTP, it stops or resumes the child depending
// on the current state of the child.
void signalHandler(int sigNum)
{
  if(sigNum == 2)
  {
    // Kill child,
    kill(childPid, SIGKILL);
    printf("\nCtrl-C caught, killing child process and then killing main program.\n");

    // "Kill" main
    exit(0);
  }
  else if(sigNum == 20)
  {
    // If the child was running, stop it.
    if(childRunning == 1)
    {
      printf("\nCtrl-Z caught, stopping child process.\n");
      kill(childPid, SIGSTOP);
      childRunning = 0;
    }
    // If the child was suspended, restart it.
    else if(childRunning == 0)
    {
      printf("\nCtrl-Z caught, resuming child process.\n");
      kill(childPid, SIGCONT);
      childRunning = 1;
    }
  }
}


void main()
{
  childPid = fork();

  // Child
  if(childPid == 0)
  {
    char* const argv[] = {"yes", NULL};
    while(1)
    {
      execvp("yes", argv);
    }

  }
  // Parent
  else
  {
    // Setting signal handler for SIGINT(Ctrl-C) and SIGTSTP(Ctrl-Z)
    signal(SIGINT, signalHandler);
    signal(SIGTSTP, signalHandler);

    // Keeping main in a loop while user decides if they want to restart child.
    while(1) {}

  }

}
