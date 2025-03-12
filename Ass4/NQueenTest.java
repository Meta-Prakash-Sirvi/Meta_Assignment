
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class NQueenTest {
    @Test 
     public void idTest(){
         NQueen ob = new NQueen() ; 
         int board1[][] = new int[1][1]; 
         int board2[][] = new int[2][2]; 
         int board3[][] = new int[3][3]; 
         int board4[][] = new int[4][4];
         int board5[][] = new int[5][5];
         int board6[][] = new int[6][6];
         int board7[][] = new int[7][7]; 
         int board8[][] = new int[8][8];  

         assertTrue(ob.nQueen(board1, 0,  1));
         assertFalse(ob.nQueen(board2, 0,  2));
         assertFalse(ob.nQueen(board3, 0,  3));
         assertTrue(ob.nQueen(board4, 0,  4));
         assertTrue(ob.nQueen(board5, 0,  5));
         assertTrue(ob.nQueen(board6, 0,  6));
         assertTrue(ob.nQueen(board7, 0,  7));
         assertTrue(ob.nQueen(board8, 0,  8));

         
         

     }
}
