public class FibFun
{
    private static long fibArray[];

    public static void main(String[] args)
    {
        fibArray = new long[1000];
        outputFibRangeAndTiming(43, 0);
        outputFibRangeAndTiming(500, 1);
    }

    private static long recursiveFib(int x)
    {
        if(x < 0) { return 0; }
        else if(x <= 0) return 1;
        else { return recursiveFib(x - 1) + recursiveFib(x - 2); }
    }

    // Attribution:
    // http://www.geeksforgeeks.org/program-for-nth-fibonacci-number/
    // Author - Arnav Kr. Mandal
    private static long fib(int x)
    {
        // Base cases
        if(x <= 0) { return 0; }
        if(x == 1 || x == 2) { return 1; }

        // If fib(n) is in the array
        if(fibArray[x] != 0) { return fibArray[x]; }

        // Otherwise... we're going to do some magic
        int k = (x & 1) == 1 ? ((x + 1) / 2) : (x / 2);

        // Setting our fib number using the math done above
        fibArray[x] = (x & 1) == 1
                ? (fib(k) * fib(k) + fib(k - 1) * fib(k - 1))
                : (2 * fib(k - 1) + fib(k)) * fib(k);

        return fibArray[x];
    }
    private static void outputFibRangeAndTiming(int x, int whichAlgo)
    {
        long fibNum;
        for(int i = 0; i < x; ++i)
        {
            long startTime = System.nanoTime();
            if(whichAlgo == 0) { fibNum = recursiveFib(i); }
            //else if(whichAlgo == 1) { fibNum = fib(i); }
            else { fibNum = fib(i); }

            long endTime = System.nanoTime();
            long millis = (endTime - startTime) / 1000000;
            String timeOut = String.format("%02d.%02d", millis / 1000, millis % 1000) + " second(s).";
            System.out.println("Fib(" + i + ") = " + fibNum + " - Time: " + timeOut);
        }
    }
}
