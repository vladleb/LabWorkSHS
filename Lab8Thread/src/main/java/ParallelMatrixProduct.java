import static java.lang.System.err;

class ParallelMatrixProduct  {


    public UsualMatrix getResult (UsualMatrix first, UsualMatrix second, int threadCount) throws MatrixException{
        if(first.getWidth ()== second.getLength ()){
            long startThread = System.currentTimeMillis  ();
            final int rowCount = first.getLength ();
            final int colCount = second.getWidth ();
            final int[][] result = new int[rowCount][colCount];


            final int cellsForThread = (rowCount * colCount) / threadCount;
            int firstIndex = 0;
            final ProductLineAndColumn[] multiplierThreads = new ProductLineAndColumn[threadCount];

            for (int threadIndex = threadCount - 1; threadIndex >= 0; --threadIndex) {
                int lastIndex = firstIndex + cellsForThread;
                if (threadIndex == 0) {
                    lastIndex = rowCount * colCount;
                }
                multiplierThreads[threadIndex] = new ProductLineAndColumn(first, second, result, firstIndex, lastIndex);
                multiplierThreads[threadIndex].start ();
                firstIndex = lastIndex;
            }

            try {
                for (final ProductLineAndColumn multiplierThread : multiplierThreads)
                    multiplierThread.join ();
            }
            catch (Exception e)
            {
                err.println ( e.getMessage ());
            }

            UsualMatrix resultUsual = new UsualMatrix(result);
            long finishThread = System.currentTimeMillis ();
            long timeConsumedMillisThread = finishThread - startThread;
            System.out.println ("Time:" + timeConsumedMillisThread);
            return resultUsual;
        }
        else
        {
            throw new MatrixException("An error in the dimensions of the matrices");
        }
    }

    public static int[][] multiplyMatrix(UsualMatrix firstMatrix, UsualMatrix secondMatrix) throws MatrixException
    {
        if(firstMatrix.getWidth ()== secondMatrix.getLength ()) {
            long startOneThread = System.currentTimeMillis ();
            final int rowCount = firstMatrix.getLength ();
            final int colCount = secondMatrix.getMatrix ()[0].length;
            final int sumLength = secondMatrix.getLength ();
            final int[][] result = new int[rowCount][colCount];

            for (int row = 0; row < rowCount; ++row) {
                for (int col = 0; col < colCount; ++col) {
                    int sum = 0;
                    for (int i = 0; i < sumLength; ++i)
                        sum += firstMatrix.getMatrix()[row][i] * secondMatrix.getMatrix ()[i][col];
                    result[row][col] = sum;
                }
            }
            long finishOneThread = System.currentTimeMillis();
            long timeConsumedMillisOneThread = finishOneThread - startOneThread;
            System.out.println("Time:" + timeConsumedMillisOneThread);
            return result;
        }
        else
        {
            throw new MatrixException ("An error in the dimensions of the matrices");
        }
    }

    private class ProductLineAndColumn extends Thread {

        private final int[][] firstMatrix;
        private final int[][] secondMatrix;
        private final int[][] resultMatrix;
        private final int firstIndex;
        private final int lastIndex;
        private final int sumLength;


        ProductLineAndColumn (UsualMatrix firstMatrix, UsualMatrix secondMatrix, int[][] resultMatrix, int firstIndex, int lastIndex) {

            this.firstMatrix = firstMatrix.getMatrix ();
            this.secondMatrix = secondMatrix.getMatrix ();
            this.resultMatrix = resultMatrix;
            this.firstIndex = firstIndex;
            this.lastIndex = lastIndex;
            sumLength = secondMatrix.getLength ();
        }

        private void calcValue (final int row, final int col)
        {
            int sum = 0;
            for (int i = 0; i < sumLength; ++i)
                sum += firstMatrix[row][i] * secondMatrix[i][col];
            resultMatrix[row][col] = sum;
        }

        @Override
        public void run () {
            System.out.println("Thread " + getName () + " started. Calculating cells from " + firstIndex + " to " + lastIndex + "...");

            final int colCount = secondMatrix[0].length;
            for (int index = firstIndex; index < lastIndex; ++index)
                calcValue (index / colCount, index % colCount);

            System.out.println ("Thread " + getName () + " finished.");
        }
    }
}
