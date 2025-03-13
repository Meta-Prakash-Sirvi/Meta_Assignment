package Assignment5;

import java.util.Scanner;

final class IntSet1 {
   private int  []set ;
   private int SET_SIZE = 1000 ; 
    public IntSet1(int []arr){    // constructor 
        set = new int[SET_SIZE+1] ; 
        for(int i:arr){
            if(i>=1 && i<=SET_SIZE){
             set[i]++;  
            }
        }
        
    }

    public boolean isMemeber(int x){
         if(set[x]>0){
             return true ; 
         }
         return false ; 
    }

    public int size(){
         int countSize = 0 ; 
         for(int i=0 ; i<=SET_SIZE ; i++){
             if(set[i]>0){
                 countSize +=set[i]; 
             }
         }
         return countSize ; 
    }

   public  boolean isSubSet(IntSet1 s){
        
         for(int i=1; i<=SET_SIZE; i++){
             if((s.set[i]>0 && set[i]==0)){
                 return false; 
             }
         }
         return true ; 
     }

   
     public  IntSet1 getCompliment(IntSet1 s){
        IntSet1 compliment = new IntSet1(set) ; 
        for(int i=0 ; i<s.set.length ; i++){
             if(s.set[i]>0 && set[i]==0){
                 compliment.set[i]++ ;
             }
        }
         return compliment ;  
     }

    public IntSet1 getUion(IntSet1 s){
        IntSet1 union= new IntSet1(set) ; 
        for(int i=0 ; i<s.set.length ; i++){
             if(s.set[i]>0  || set[i]>0){
                 union.set[i]++ ;
             }
        }
         return union ;  
     }
    

     public int[] get(){
         return set ; 
     }
   


}

public class IntSet{
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in) ; 
        System.out.println("enter the size of input1 array :");
        int size1 = sc.nextInt() ; 
        System.out.println("Insert the elements ");
        int []input1 = new int[size1]; 
        for(int i=0 ; i<size1 ; i++){
             input1[i] = sc.nextInt() ; 
        }
        
        
        System.out.println("enter the size of input2 array :");
        int size2 = sc.nextInt() ; 
        System.out.println("Insert the elements");
        int []input2 = new int[size2] ;
        for(int i=0 ; i<size2 ;i++){
             input2[i] = sc.nextInt() ; 
        }

        IntSet1 set1 = new IntSet1(input1) ; 
        IntSet1 set2 = new IntSet1(input2) ; 

        
        System.out.println("choose one :"); 
        System.out.println("1 : is memeber : ");
        System.out.println("2 : is subset :  ");
        System.out.println("3 : compliment : ");
        System.err.println("4 : union : ");

        int choose = sc.nextInt() ; 


            switch(choose){

                case 1: boolean result = (set1.isSubSet(set2));

                if(result){
                     System.out.println("yes set2 is a subset of set1");
                }else{
                     System.out.println(" NO, set2 is not a subset of set1 ");
                }
                 
                break ;
        

                case 2: System.out.println(set1.isMemeber(2)) ; 
                        System.out.println("size of set1  : "+ set1.size());
                        System.out.println("Size of set2 : " + set2.size());
                        break ;

                case 3: IntSet1 set3 = set1.getCompliment(set2) ; 
                System.out.println("Compliment : ");
                    for(int i=1 ;i<1001 ; i++){
                         if(set3.get()[i]>0){
                             System.out.print(i +" ");
                         }
                    }
                    System.out.println();
                    break ;
                
                case 4:  IntSet1 set4 = set1.getUion(set2) ;
                System.out.println("Union : "); 
                for(int i=1 ;i<1001; i++){
                     if(set4.get()[i]>0){
                         System.out.print(i +" ");
                     }
                }

                break ;
                
                 
            }
        
        
        
        

       

        
         
        


        

        
    }
}
