package DsaAssignemt1;

import java.util.ArrayList;
import java.util.Scanner;


public class Polynomial{


     public static int findDegree(ArrayList<ArrayList<Integer>> input){
           int maxDegree = 0 ; 
           for(int index=0 ; index<input.size() ; index++){
                ArrayList<Integer> row = input.get(index) ;
                int temp = 0 ;  
                for(int indexJ = 1 ; indexJ<row.size() ; indexJ++){
                     temp += row.get(indexJ) ; 
                }
                    if(maxDegree<temp){
                          maxDegree = temp ; 
                    }

           }
           return maxDegree ; 
     }
     public static void main(String[] args) {
          Scanner sc = new Scanner(System.in) ; 
     
          ArrayList<ArrayList<Integer>> nestedArrayList = new ArrayList<ArrayList<Integer>>();
         
      System.out.println("Enter the number of varibles : ");
      int exponent = sc.nextInt() ; 
          while(true){
               ArrayList<Integer> row = new ArrayList<Integer>();  
               System.out.println("Enter the coffecent :");
               int coffecent = sc.nextInt() ; 
                    row.add(coffecent);
                  for(int j=1; j<=exponent ; j++){
                    System.out.println("enter the power of "+ j +" exponent : " );
                    int power = sc.nextInt() ;
                    row.add(power); 
                  }   

               nestedArrayList.add(row); 
               System.out.println("press -1 to exit :");
               int press = sc.nextInt() ; 
               if(press==-1){
                     break; 
               }
          }  

           
           System.out.println( "Degree of this  multivariate polynomial function is : " + findDegree(nestedArrayList));

     }
}