package Assignment5;

import static org.junit.jupiter.api.DynamicTest.stream;

import java.net.Socket;
import java.util.Scanner;

final class Poly{
     private int[] polynomialinput  ; 

     public Poly (int []input){
         int n = input.length; 
         polynomialinput = new int[n] ; 
         for(int i=0 ; i<n; i++){
             polynomialinput[i] = input[i] ; 
         }

     }

     public float evaluate(float x){
         int size = polynomialinput.length ; 
         float ans =  0 ; 
         for(int i=0 ; i<size ; i++){
             ans +=polynomialinput[i]*Math.pow(x,(size-i-1)) ; 

         }
         return ans; 

     }

     public int  degreeofpolynomial(){
          
        int size = polynomialinput.length-1 ; 
        int degree = size ; 
        return degree; 
     }

     public int[] sumofpolynomial(Poly p1 ,Poly p2){
         int size1 = p1.polynomialinput.length ; 
         int size2 = p2.polynomialinput.length ; 
         int d1 = size1-1 ; 
         int d2 = size2-1 ; 

          int maxilength = Math.max(size1, size2);
          int []result = new int[maxilength] ;
            int index1= 0; 
            int index2 =0 ; 
          for(int i=0 ; i<maxilength ; i++){
             if(d1>d2){
                 result[i] = p1.polynomialinput[index1++] ;
                 d1--; 
             }else if(d1<d2){
                 result[i] = p2.polynomialinput[index2++] ; 
                 d2--;
             }else{
                 result[i]= p1.polynomialinput[index1++]+p2.polynomialinput[index2++] ; 
                 d1--; 
                 d2-- ;
             }
          }
         
            return result ; 
         
     }

     public int [] multiplicationply(Poly p1, Poly p2){
         int newd = p1.degreeofpolynomial()+ p2.degreeofpolynomial() ; 
         int []result = new int[newd+1] ;
         int length1 =p1.polynomialinput.length; 
         int length2 = p2.polynomialinput.length ;  

         for(int i=0 ; i<length1 ; i++){
             for(int j =0 ; j<length2 ;j++){
                 result[i+j]+=p1.polynomialinput[i]*p2.polynomialinput[j] ; 
             }
         }
         return result ; 
     }
}


public class Polynomial {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in) ; 
        System.out.println("Enter the size of input array1 :");
            int size = sc.nextInt() ;

        int []arr1 =  new int[size];
        System.out.println("enter the element of input array 1");
        for(int i=0 ;i<size ; i++){
             arr1[i] =sc.nextInt() ; 
        }

        System.out.println("Enter the size of input array 2 :");
        int size1 = sc.nextInt() ;
        System.out.println("enter the elemet of input array 2");

        int []arr2 = new int[size1];
        for(int i=0 ;i<size1 ; i++){
            arr2[i] =sc.nextInt() ; 
       }


        Poly p = new Poly(arr1) ; 
        Poly p1 =new Poly(arr1); 
        Poly p2 = new Poly(arr2) ; 

        System.out.println("enter the option :");
        System.out.println("1- Evaluate : ");
        System.out.println("2- Degree : ");
        System.out.println("3 - addition :");
        System.out.println("4 - multiplication : ");
        int choose = sc.nextInt() ; 
        
        
            switch (choose) {
                            
                case 1: System.out.println("Enter the element for the Evaluate : ");
                        float num = sc.nextFloat() ; 
                        System.out.println("Evaluate of polynomial :" +p.evaluate(num));
                            break;
                
                case 2:  System.out.println("degree of the polynomial :"+p.degreeofpolynomial()) ;
                         break ;

                case 3: System.out.println("addtion of polynomial : ");
                         int []r = p.sumofpolynomial(p1, p2);
                            for(int i=0 ; i<r.length; i++){
                                 System.out.print(r[i]+" ");
                             }
                            System.out.println(); //print line 
                             break ;
                    
                case 4:   System.out.println("multiplication  of polynomial : ");
                         int []r2 = p.multiplicationply(p1,p2) ; 
                            for(int i=0 ; i<r2.length ;i++){
                            System.out.print(r2[i]+" ");
                          }
    
            }
           

    }
}
