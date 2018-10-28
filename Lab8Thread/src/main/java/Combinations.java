import static java.lang.System.err;

class Combinations
{
    public int getResultThread(int n, int k) throws CombinationsException {
        if((k >= 0) & (n > 0)) {
            long startThread = System.currentTimeMillis();
            final ThreadForCombinations[] threadCombinations = new ThreadForCombinations[3];
            threadCombinations[0] = new ThreadForCombinations(n + k - 1);
            threadCombinations[1] = new ThreadForCombinations(k);
            threadCombinations[2] = new ThreadForCombinations(n - 1);
            for (int i = 0; i < 3; i++) {
                threadCombinations[i].start();
            }
            try {
                for(int  i = 0; i < 3; i++) {
                    threadCombinations[i].join();
                }
            }
            catch (Exception e)
            {
                err.println ( e.getMessage ());
            }
            int thread0Res = threadCombinations[0].getResult ();
            int thread1Res = threadCombinations[1].getResult ();
            int thread2Res = threadCombinations[2].getResult ();
            long finishThread = System.currentTimeMillis();
            long timeConsumedMillisThread = finishThread - startThread;
            System.out.println ("Time of multiple threads: " + timeConsumedMillisThread);
            return (thread0Res / (thread1Res * thread2Res));
        }
        else
            {
                throw new CombinationsException("Values must be positive.");
            }
    }
    public int getResultOneThread(int n, int k) throws CombinationsException {
        if ((k >= 0) & (n > 0)) {
            long startThread = System.currentTimeMillis();
            int result = factorial(n + k - 1) / ((factorial(k)) * (factorial (n-1)));
            long finishThread = System.currentTimeMillis();
            long timeConsumedMillisThread = finishThread - startThread;
            System.out.println ("Single thread time: " + timeConsumedMillisThread);
            return result;
        }
        else
        {
            throw new CombinationsException("Values must be positive.");
        }
    }

    private int factorial(int x)
    {
        if (x == 0) return 1;
        return x * factorial(x-1);
    }

    private class ThreadForCombinations extends Thread  {
        private final int value;
        private int factResult;
        ThreadForCombinations (int value)
        {
            this.value = value;
            this.factResult = 0;
        }

        int factorial(int x)
        {
            if (x == 0) return 1;
            return x * factorial(x-1);
        }

        int getResult()
        {
            return factResult;
        }
        @Override
        public void run() {
            factResult = factorial(value);
        }
    }
}
