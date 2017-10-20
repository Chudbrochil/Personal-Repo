public class Fibonacci extends Thread
{
    private static FibWorker fibWorker1;
    private static FibWorker fibWorker2;

    /**
     * main()
     *
     * Runs our worker threads to calculate fib numbers and end the program when the threads die
     */
    public static void main(String[] args)
    {
        fibWorker1 = new FibWorker("Thread1");
        fibWorker2 = new FibWorker("Thread2");
        runWorkers();

        // Checks if both threads are asleep, they take a little while to actually die
        while(true)
        {
            if(fibWorker1.isAlive() == false && fibWorker2.isAlive() == false)
            {
                System.out.println("Program Exit");
                System.exit(0);
            }
        }
    }

    /**
     * runWorkers()
     * Starts both fib worker threads, runs them for a number of iterations, and then
     * sends a stop to both threads.
     */
    private static void runWorkers()
    {
        fibWorker1.start();
        fibWorker2.start();

        for (int i = 0; i < 10; ++i)
        {
            fibWorker1.printStatus();
            fibWorker2.printStatus();
            System.out.println("");
            try { Thread.sleep(2000); }
            catch (InterruptedException e) { System.out.println(e.getMessage()); }
        }

        // When we're done with printing status from each worker thread n times, stop the threads
        fibWorker1.stopThread();
        fibWorker2.stopThread();
    }

    /**
     * FibWorker
     *
     * This is an inner class that serves as a worker thread to output fibonacci numbers in a sequence.
     * The intention of this class is to prove simultaneous operation of two threads producing the same
     * set of output, but at different time steps.
     *
     * Ex. The first thread might be at step 10000, where the second thread might be at step 10029, but both
     * are producing fibonacci numbers in a sequence.
     */
    private static class FibWorker extends Thread
    {
        private final String NAME;
        private long step = 0;
        private long z;
        private long y = 1;
        private long x = 1;
        // LEARNING: Volatile was critical for this boolean to work
        private volatile boolean running;

        public FibWorker(String threadName) { NAME = threadName; }

        /**
         * run()
         * Gets ran when this class' thread is started.
         */
        public void run()
        {
            running = true;
            while(running) { updateFibNums(); }
            System.out.println("This is thread " + NAME + " signing out.");
        }

        /**
         * stopThread()
         * Sets a boolean to false so the thread knows to stop processing fib numbers
         */
        public void stopThread() { running = false; }

        /**
         * printStatus()
         * Prints the status of the current thread. This is useful for seeing if our thread is still chugging along.
         */
        public synchronized void printStatus()
        {
            System.out.println("Name: " + NAME + " Step: " + step + " x:" + x + " y:" + y + " z:" + z);
        }

        /**
         * updateFibNums()
         * Private work horse method that calculates our internal fib numbers. This gets called by the threads run().
         */
        private synchronized void updateFibNums()
        {
            // LEARNING: After I made this method synchronized, this if statement worked
            if(z == 7540113804746346429L)
            {
                x = 1;
                y = 1;
                z = 0;
            }
            step++;

            x = y;
            y = z;
            z = x + y;
        }

    }

}
