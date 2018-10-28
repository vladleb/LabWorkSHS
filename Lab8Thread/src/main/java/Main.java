import java.util.Scanner;
import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        Combinations multipleThreads = new Combinations();
        try {
            out.println("Result of multiple threads: " + multipleThreads.getResultThread(3, 2));
        }
        catch (CombinationsException e)
        {
            err.println ( e.getMessage ());
        }
        Combinations oneThread = new Combinations();
        try {
            out.println("Single thread result = " + oneThread.getResultOneThread(3, 2));
        }
        catch (CombinationsException e)
        {
            err.println ( e.getMessage ());
        }
        int width;
        int height;
        Scanner in = new Scanner (System.in);
        out.print ("Enter the size of your 1 matrix" + '\n' + "Height: ");
        height = in.nextInt ();
        out.print ("Width:  ");
        width = in.nextInt ();
        UsualMatrix matrA = new UsualMatrix (height, width);
        out.print ("Enter the size of your 2 matrix" + '\n' + "Height: ");
        height = in.nextInt ();
        out.print ("Width:  ");
        width = in.nextInt ();
        UsualMatrix matrB = new UsualMatrix (height, width);

        out.println ("Matrix A: " + '\n' + matrA);
        out.println ("Matrix B: " + '\n' + matrB);
        try {
            ParallelMatrixProduct matr = new ParallelMatrixProduct ();
            UsualMatrix ansThread = matr.getResult (matrA, matrB, 10);
            out.println ("Result: " + '\n' + ansThread);
        }
        catch (MatrixException e){
            err.println ( e.getMessage ());
        } catch (Exception e) {
            err.println ( e.getMessage ());
        }
        try {
            UsualMatrix ansOne = new UsualMatrix (ParallelMatrixProduct.multiplyMatrix (matrA,matrB));
            out.println ("Result: " + '\n' + ansOne);
        }
        catch (Exception e){
            err.println ( e.getMessage ());
        }
    }
}