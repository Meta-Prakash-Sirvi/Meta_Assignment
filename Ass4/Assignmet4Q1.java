import java.util.Scanner;

class MathematicalOperation{
     
     int counter=0 ;
     int value1; 
     int value2 ; 
     public int findLCM(int firstvalue , int secondvalue) {
        if(firstvalue==0 || secondvalue==0){
             return 0 ; 
        }
             if(counter==0){
                 value1 = firstvalue ; 
                 value2 = secondvalue ; 
             }
             counter =1; 

             if(firstvalue==secondvalue){
                 return secondvalue ; 
             }
             
             if(firstvalue<secondvalue){
                 return findLCM(firstvalue+value1, secondvalue) ; 
             }
             else{
                 return findLCM(firstvalue, secondvalue+value2) ; 
             }

    }

    public int findHcf(int firstvalue , int secondvalue){
         if(secondvalue!=0){
             return findHcf(secondvalue, firstvalue%secondvalue); 
         }else{
            
             return firstvalue; 
         }

    }
}
public class Assignmet4Q1 {
    
    public static void main(String[] args) {
         
        MathematicalOperation m = new MathematicalOperation() ; 

        Scanner sc = new Scanner(System.in) ; 
        System.out.println("enter the choice");
        System.out.println("1 - Find HCF");
        System.out.println("2 - Find LCM");
        int choose =sc.nextInt() ; 
        System.out.println("Enter the firstvalue and secondvalue");
        int num1= sc.nextInt() ; 
        int num2 = sc.nextInt() ; 
       
        switch (choose) {
            case 1: System.out.println(m.findHcf(num1, num2)) ;
                    break;
        
            case 2: System.out.println(m.findLCM(num1, num2)) ;
                    break ; 
        }
        


    }
}
