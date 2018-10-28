import org.junit.Test;

import static java.lang.System.err;
import static java.lang.System.out;
import static org.junit.Assert.*;

public class CombinationsTest {

    @Test
    public void getResultThread()
    {
        int resMultipleThreads = 0;
        int res = 6;
        Combinations multipleThreads = new Combinations ();
        try {
            resMultipleThreads = multipleThreads.getResultThread (3, 2);
        }
        catch (CombinationsException e)
        {
            err.println ( e.getMessage ());
        }
        assertEquals (resMultipleThreads,res);
    }

    @Test
    public void getResultOneThread()
    {
        int resOneThreads = 0;
        int res = 6;
        Combinations oneThread = new Combinations ();
        try {
            resOneThreads = oneThread.getResultOneThread (3, 2);
        }
        catch (CombinationsException e)
        {
            err.println ( e.getMessage ());
        }
        assertEquals(res, resOneThreads);
    }
}