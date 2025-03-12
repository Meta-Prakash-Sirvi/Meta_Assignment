
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class AssignmentTest {
    @Test
         
    public void idTest() {
         ArrOperation s = new ArrOperation() ; 
         int []arr1 = {1,1,1,1,1} ;  
         assertEquals(1, s.clumps(arr1)) ;
         
         int []arr2 ={1,1,2,2,1,1}; 
         assertEquals(3 ,s.clumps(arr2)); 

         int []arr3 = {1,1,2,1,1} ; 
         assertEquals(2,s.clumps(arr3)) ; 

         int []arr4 ={1,2,2,3,4,4} ; 
         assertEquals(2,s.clumps(arr4)) ; 

         int []arr5 = {-1,-1,-1,2,2,-3,-3} ;
         assertEquals(3,s.clumps(arr5)) ;

         int []arr6 = {} ; 
         assertThrows(AssertionError.class, ()->{
            if(arr6.length==0){
                 throw new AssertionError("Array cannot be empty ") ; 
            }
         });
    }

    @Test 
    public void idTest1(){
        ArrOperation s = new ArrOperation() ; 
        int []arr1 = {1,1,1,2,1} ;
         assertEquals(3 , s.splitArray(arr1)) ; 

         int []arr2 = {2,1,1,2,1};
         assertEquals(-1, s.splitArray(arr2)) ; 
         
         int []arr3 = {10,10};
         assertEquals(1 , s.splitArray(arr3)) ; 
         
         int []arr4 = {10,20,30}; 
         assertEquals(2 ,s.splitArray(arr4)) ; 

         int []arr5 = {1,2,3,4,10} ; 
         assertEquals(4 , s.splitArray(arr5)) ; 
    }

    @Test 
    public void idTest2() {
        ArrOperation s = new ArrOperation() ; 
        int []arr1 = {1, 2, 3, 8, 9, 3, 2, 1} ; 
    assertEquals(3, s.mirrorlongest(arr1));

    int []arr2 = {7, 1, 4, 9, 7, 4, 1} ; 
    assertEquals(2, s.mirrorlongest(arr2));

    int []arr3 = {1, 2, 1, 4}; 
    assertEquals(3, s.mirrorlongest(arr3));

    int []arr4 = {1, 4, 5, 3, 5, 4, 1} ; 
    assertEquals(7, s.mirrorlongest(arr4));

    int []arr6 = {} ; 
         assertThrows(AssertionError.class, ()->{
            if(arr6.length==0){
                 throw new AssertionError("Array cannot be empty ") ; 
            }
         });

    }

    @Test
    public void idTest3() {
        ArrOperation s = new ArrOperation() ; 
         int []arr1 = {5, 4, 9, 4, 9, 5} ; 
             int []o1 = {9, 4, 5, 4, 5, 9}; 
             assertArrayEquals(o1, s.fixXY(arr1 , 4, 5));

             int []arr2 = {1, 4, 1, 5} ; 
             int []o2 = {1,4,5,1} ; 
             assertArrayEquals(o2, s.fixXY(arr2 , 4, 5));

             int []arr3 = {1, 4, 1, 5, 5, 4, 1} ; 
             int []o3 = {1, 4, 5, 1, 1, 4, 5} ; 
             assertArrayEquals(o3, s.fixXY(arr3, 4, 5));

             int []arr4 = {1,4,1,4,5,5,4}; 
             int n = arr4.length; 
             assertThrows(AssertionError.class, ()->{
                 if(arr4[n-1]==4){
                     throw new AssertionError("X is not occur at last position") ;
                 }
             });
    }
}
