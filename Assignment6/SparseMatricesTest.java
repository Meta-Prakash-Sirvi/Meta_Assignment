import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SparseMatricesTest {
    @SuppressWarnings("deprecation")
    @Test

    public void idTest() {
        int [][]input1 = {{0,0,5}, {1,0,0}};
        SM s = new SM(input1, 2, 3) ; 
        int [][]output = {{0,1}, {0,0}, {5,0}} ; 
        assertEquals(output, s.transposeMatrix());

        int[][]input2 = {{0 , 0 , 0 , 0 , 9, 0 },
        {0 , 8 , 0 , 0 , 0, 0 },
        {4 , 0 , 0 , 2 , 0, 0 },
        {0 , 0 , 0 , 0 , 0, 5 },
        {0 , 0 , 2 , 0 , 0, 0 }
       };
        SM s1 = new SM(input2, 5,6) ; 
       int [][]output1  = {{0,0,4,0,0},{0,8,0,0,0},{0,0,0,0,2},{0,0,2,0,0}, {9,0,0,0,0}, {0,0,0,5,0}};
       assertEquals(output1, s1.transposeMatrix()); 

    }

    @Test
    public void idTest1() {
        int [][]input1 = {{0,0,5}, {1,0,0}};
        SM s = new SM(input1, 2, 3) ; 
        assertFalse(s.symmetricalMatrix());
    }


    @Test
    public void idTest2() {
        int [][]input1 = {{0,0,5}, {1,0,0}};
        int [][]input2 = {{0,5,0},{4,0,1}}; 
        SM s = new SM(input1, 2, 3) ; 
        SM s1 = new SM(input2 , 2,3) ; 
        int [][]o1 = {{0,5,5}, {5,0,1}} ; 
        assertEquals(o1, s.addMartix(s1));

         
    }

    @Test
    public void idTest3() {
        int [][]input1 = {{0,0,5}, {1,0,0}};
        int [][]input2 = {{0,5,0},{4,0,1}}; 
        SM s = new SM(input1, 2, 3) ; 
        SM s1 = new SM(input2 , 2,3) ; 

        assertThrows(IllegalArgumentException.class, ()->{
            s.multiplyMatrix(s1) ;   
        });

        int [][]input3 = {{0,0,5}, {1,0,0}};
        int [][]input4 = {{0,5,0},{4,0,1}, {0,2,1}}; 
        SM s2 = new SM(input3, 2, 3) ; 
        SM s3 = new SM(input4 , 3,3) ; 
        int [][]o1 = {{0,10,5},{0,5,0}};
        assertEquals(o1, s2.multiplyMatrix(s3));;

    }
}
