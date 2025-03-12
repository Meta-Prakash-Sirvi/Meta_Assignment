import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public  class Assignment4Q2Test{
     @Test
     public void idTest(){
        Search s = new Search();  
         ArrayList<Integer>  arr1 = new ArrayList<>();
         arr1.add(1); 
         arr1.add(2); 
         arr1.add(5); 
         arr1.add(6); 
         arr1.add(8) ; 
          arr1.add(9) ;
         assertEquals(1, s.linearSearch(arr1, 2)); 
         assertEquals(-1, s.linearSearch(arr1, 10)); 
     }

     @Test
     public void idTest1() {
         ArrayList<Integer> arr2 = new ArrayList<>(); 
         Search s = new Search();  
         arr2.add(3);
         arr2.add(5);
         arr2.add(7) ;
          assertEquals(0, s.binarySearch(arr2, 3));
           
     }

}