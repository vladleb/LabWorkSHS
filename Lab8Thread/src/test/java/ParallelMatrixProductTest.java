import static java.lang.System.err;
import static java.lang.System.out;
import static org.junit.Assert.*;

public class ParallelMatrixProductTest {

    @org.junit.Test
    public void getResultWithRandMatrix() {
        UsualMatrix matrA = new UsualMatrix (3, 5);
        UsualMatrix matrB = new UsualMatrix (5, 3);
        UsualMatrix ansThread = null;
        UsualMatrix ansOne = null;
        try {
            ParallelMatrixProduct matr = new ParallelMatrixProduct ();
            ansThread = matr.getResult(matrA, matrB, 10);
            out.println (ansThread);
        }
        catch (MatrixException e){
            err.println ( e.getMessage ());
        } catch (Exception e) {
            err.println ( e.getMessage ());
        }
        try {
            ansOne = new UsualMatrix (ParallelMatrixProduct.multiplyMatrix (matrA,matrB));
            out.println (ansOne);
        }
        catch (Exception e){
            err.println ( e.getMessage ());
        }
        assertArrayEquals (ansThread.getMatrix (), ansOne.getMatrix ());
    }

    @org.junit.Test
    public void getResultWithReadyMatrix()
    {
        int[][] masMatrixA = { {1,2,3}, {1,2,3}, {1,2,3}};
        int[][] masMatrixB = { {3,2,1}, {3,2,1}, {3,2,1}};
        int[][] masMatrixRes = { {18,12,6}, {18,12,6}, {18,12,6}};
        UsualMatrix matrA = new UsualMatrix (masMatrixA);
        UsualMatrix matrB = new UsualMatrix (masMatrixB);
        UsualMatrix ansThread = null;
        try {
            ParallelMatrixProduct matr = new ParallelMatrixProduct ();
            ansThread = matr.getResult(matrA, matrB, 10);
            out.println (ansThread);
        }
        catch (MatrixException e){
            err.println ( e.getMessage ());
        } catch (Exception e) {
            err.println ( e.getMessage ());
        }
        assertArrayEquals (ansThread.getMatrix (), masMatrixRes);
    }
//TODO OPTIONAL(JAVA 8), TRY WITH RESOURCES
    @org.junit.Test
    public void multiplyMatrixWithRandMatrix() {
        UsualMatrix matrA = new UsualMatrix(2, 4);
        UsualMatrix matrB = new UsualMatrix(4, 2);
        UsualMatrix ansThread = null;
        UsualMatrix ansOne = null;
        try {
            ParallelMatrixProduct matr = new ParallelMatrixProduct();
            ansThread = matr.getResult(matrA, matrB, 10);
            out.println("Result: " + '\n' + ansThread);
        } catch (MatrixException e) {
            err.println(e.getMessage());
        } catch (Exception e) {
            err.println(e.getMessage());
        }
        try {
            ansOne = new UsualMatrix(ParallelMatrixProduct.multiplyMatrix(matrA, matrB));
            out.println("Result: " + '\n' + ansOne);
        } catch (Exception e) {
            err.println(e.getMessage());
        }
        assertArrayEquals(ansThread.getMatrix(), ansOne.getMatrix());
    }

    @org.junit.Test
    public void multiplyMatrixWithReadyMatrix ()
    {
        int[][] masMatrixA = { {1,2,3}, {1,2,3}, {1,2,3}};
        int[][] masMatrixB = { {3,2,1}, {3,2,1}, {3,2,1}};
        int[][] masMatrixRes = { {18,12,6}, {18,12,6}, {18,12,6}};
        UsualMatrix matrA = new UsualMatrix (masMatrixA);
        UsualMatrix matrB = new UsualMatrix (masMatrixB);
        UsualMatrix ansOne = null;
        try {
            ansOne = new UsualMatrix (ParallelMatrixProduct.multiplyMatrix (matrA,matrB));
            out.println (ansOne);
        }
        catch (Exception e){
            err.println ( e.getMessage ());
        }
        assertArrayEquals (ansOne.getMatrix (), masMatrixRes);

    }
}