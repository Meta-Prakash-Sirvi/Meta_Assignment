
import java.util.Scanner;

 class ArrOperation{
        
    public int clumps(int []input) {
      int size = input.length ; 
      int count =0 ; 
      int counter = 0 ; 
      for(int i=1 ; i<size ; i++) {
         if(input[i-1]==input[i]) {
              counter++ ; 
              if(counter==1){
                   count++ ; 
              }
         }else{
              counter = 0 ; 
         }
      }
    return count ;
   }


   public int splitArray(int []input) {
        int size = input.length ;  
        int total = 0 ; 
       
       for(int i=0 ; i<size; i++) {  // calculate total sum 
          total+=input[i] ; 
       }
        int currentSum = 0 ; 

      for(int i=0 ; i<size; i++) {   
        currentSum+=input[i] ; 
        if(currentSum==total-currentSum) {  // find equal index 
            return i+1 ;      //  for - 1 based indexing 
        }
      }
    return -1 ;        
    }

    public void checkExecption(int [] input , int x ,int y) {
        int size = input.length ; 
        boolean flag  = true ; 
        int countX = 0 ; 
        int countY = 0 ;
        for(int i=0 ; i<size; i++) {
            if(input[i]==x) { 
             countX ++ ; 
            }
            if(input[i]==y) {
             countY ++ ;
            }
        }  

        for(int i=1 ;i<size ; i++) {
         if(input[i-1]==input[i] && input[i]==x) {
             flag = false ; 
             break ;
         }
        }

        if(countX!=countY) {
         throw new AssertionError("X and Y are unequal in array ") ; 
        }
        if(input[size-1]==x) {
         throw new AssertionError("X is not occur at last position ") ; 
        }
        if(!flag) {
         throw new AssertionError("x is not be Adjacent : ") ; 
        }
    }

    public int[] fixXY(int[] input, int x, int y) {  // fixXY  

        checkExecption(input, x, y);

        int size = input.length;
    
        int j = 0;
        int []temparray  = new int[size]; 
    
        for(int i=0 ; i<size; i++) { 
         if(input[i]==x && input[i+1]!=y) {
             temparray[j] = input[i+1] ; 
             input[i+1] = y ; 
             j++;
         }
        }

        int t = 0 ;
        int k =0 ;
        while(k<size){
         if(input[k]==x && input[k+1]==y) {
             k+=2 ; 
         }else if(input[k]==y) {
             input[k] = temparray[t] ; 
             t++ ; 
             k++ ; 
         }
         else{
             k++ ; 
         }
        }
  
    return input;
    }

    public int mirrorlongest(int []input) {
         int size = input.length ; 
        int maxlen = -1 ; 
        for(int i=0 ; i<size; i++) {
            for(int j = size-1 ; j>=0 ; j--) {
                int len = 0 ; 
                int start = i  ; 
                int end = j ;

                while(start<size && end>=0 && input[start]==input[end]) {
                        len++ ; 
                        start++ ; 
                        end-- ; 
                }

                if(maxlen<len) { 
                 maxlen = len ;
                } 
            }
        }
      return maxlen ; 
   
    }
}


public class Assignment3 {
      
    
    public static void main(String[] args) {
        ArrOperation a = new ArrOperation() ; 
        Scanner sc = new Scanner(System.in) ; 
        System.out.println("Enter the Choice : ");
        System.out.println("1. split array operation : ");
        System.out.println("2. Mirror array operation : ");
        System.out.println("3. clumps array operation : ");
        System.out.println("4. FixXy array operation : ");
        int choice = sc.nextInt() ; 
        
        
                          
        try{ 
            System.out.println("enter the size of array : ");  
            int size = sc.nextInt() ; 
            
            if(size==0){
                throw new AssertionError("Size cannot be Zero ") ; 
            }
            
            System.out.println("Enter the input elements : ");
            int []input = new int[size] ; 
            for(int i=0 ; i<size; i++){
                    input[i] = sc.nextInt() ;
            }

            switch(choice){
                case 1: 
                    System.out.println(a.splitArray(input)) ; 
                    break ;
                case 2: 
                    System.out.println(a.mirrorlongest(input)) ; 
                    break ;

                case 3: 
                    System.out.println(a.clumps(input)) ; 
                    break ;    
                            
                case 4: 
                    int []result = a.fixXY(input, 4 ,5);
                     for(int i=0 ; i<size ;i++){
                         System.out.print(result[i]+" ");
                     } 
                    break ;  
        }
    }catch(AssertionError e){
          System.out.println("Exception --- " + e.getMessage());
    }    
        
    }
}
