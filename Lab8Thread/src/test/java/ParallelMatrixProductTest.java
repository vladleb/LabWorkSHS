import static java.lang.System.err;
import static java.lang.System.out;
import static org.junit.Assert.*;

public class ParallelMatrixProductTest {

    @org.junit.Test
    public void getResult() {
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
//TODO OPTIONAL(JAVA 8), TRY WITH RESOURCES
    @org.junit.Test
    public void multiplyMatrix()
    {
        UsualMatrix matrA = new UsualMatrix (2, 4);
        UsualMatrix matrB = new UsualMatrix (4, 2);
        UsualMatrix ansThread = null;
        UsualMatrix ansOne = null;
        try {
            ParallelMatrixProduct matr = new ParallelMatrixProduct ();
            ansThread = matr.getResult(matrA, matrB, 10);
            out.println ("Result: " + '\n' + ansThread);
        }
        catch (MatrixException e){
            err.println ( e.getMessage ());
        } catch (Exception e) {
            err.println ( e.getMessage ());
        }
        try {
            ansOne = new UsualMatrix (ParallelMatrixProduct.multiplyMatrix (matrA,matrB));
            out.println ("Result: " + '\n' + ansOne);
        }
        catch (Exception e){
            err.println ( e.getMessage ());
        }
        assertArrayEquals (ansThread.getMatrix (), ansOne.getMatrix ());
    }
}