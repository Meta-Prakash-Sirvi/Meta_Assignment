import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class KnightTest{
     @Test
     public void idTest(){
        KnightTour ob = new KnightTour() ; 
            int [][]input1 = new int[5][5] ; 
            int [][]input2 = new int[2][2] ; 
            int [][]input3 = new int[4][4] ; 

            
            assertFalse(ob.solve(0, 0, input2, 2, 1));
            assertFalse(ob.solve(0, 0, input3, 4, 1));

     }
}