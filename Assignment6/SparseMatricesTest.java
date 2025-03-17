import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SparseMatricesTest {
    @SuppressWarnings("deprecation")
    @Test

    public void idTest() {               // Transpose matrix
        int [][]input1 = {{0,0,5}, {1,0,0}};
        SparseMatricesOperation s = new SparseMatricesOperation(input1) ; 
        int [][]output = {{0,1}, {0,0}, {5,0}} ; 
        assertEquals(output, s.transposeMatrix());

        int[][]input2 = {{0 , 0 , 0 , 0 , 9, 0 },
        {0 , 8 , 0 , 0 , 0, 0 },
        {4 , 0 , 0 , 2 , 0, 0 },
        {0 , 0 , 0 , 0 , 0, 5 },
        {0 , 0 , 2 , 0 , 0, 0 }
       };
       SparseMatricesOperation s1 = new SparseMatricesOperation(input2) ; 
       int [][]output1  = {{0,0,4,0,0},{0,8,0,0,0},{0,0,0,0,2},{0,0,2,0,0}, {9,0,0,0,0}, {0,0,0,5,0}};
       assertEquals(output1, s1.transposeMatrix()); 

    }

    @Test
    public void idTest1() {                          // SymmetricalMatrix
        int [][]input1 = {{0,0,5}, {1,0,0}};
        SparseMatricesOperation s = new SparseMatricesOperation(input1) ; 
        assertFalse(s.symmetricalMatrix());
    }


    @Test
    public void idTest2() {                       // for Addition of matrix
        int [][]input1 = {{0,0,5}, {1,0,0}};
        int [][]input2 = {{0,5,0},{4,0,1}}; 
        SparseMatricesOperation s = new SparseMatricesOperation(input1) ; 
        SparseMatricesOperation s1 = new SparseMatricesOperation(input2 ) ; 
        int [][]o1 = {{0,5,5}, {5,0,1}} ; 
        assertEquals(o1, s.addMartix(s1));

        int [][]input3 = {{0,0,1}, {0,0,0}} ; 
        int [][]input4 = {{0,1}, {0,0}}; 
        SparseMatricesOperation obj1 = new SparseMatricesOperation(input3); 
        SparseMatricesOperation obj2 = new SparseMatricesOperation(input4 ) ; 

        assertThrows(IllegalArgumentException.class, ()->{
               obj1.addMartix(obj2) ; 
        }); 

         
    }

    @Test
    public void idTest3() {                         // for mutlipication of matrix
        int [][]input1 = {{0,0,5}, {1,0,0}};
        int [][]input2 = {{0,5,0},{4,0,1}}; 
        SparseMatricesOperation s = new SparseMatricesOperation(input1) ; 
        SparseMatricesOperation s1 = new SparseMatricesOperation(input2) ; 

        assertThrows(IllegalArgumentException.class, ()->{
            s.multiplyMatrix(s1) ;   
        });

        int [][]input3 = {{0,0,5}, {1,0,0}};
        int [][]input4 = {{0,5,0},{4,0,1}, {0,2,1}}; 
        SparseMatricesOperation s2 = new SparseMatricesOperation(input3) ; 
        SparseMatricesOperation s3 = new SparseMatricesOperation(input4 ) ; 
        int [][]o1 = {{0,10,5},{0,5,0}};
        assertEquals(o1, s2.multiplyMatrix(s3));;

    }
}
