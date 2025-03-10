import java.util.Scanner; 

public class Area{
    
      public double triangleArea(double h , double w){
        if(h<=0 || w<=0){
             throw  new ArithmeticException("Height and Width must be greater then 0") ; 
         }
             return (h*w)/2 ; 
      }
      public double rectangleArea(double h, double w){
        if(h<=0 || w<=0){
             throw  new ArithmeticException("Height and Width must be greater then 0") ; 
         }
           return  (h*w) ;
      }

      public double squreArea(double w){
        if(w<=0){
             throw  new ArithmeticException("Width must be greater then 0") ; 
         }
         return  w*w ;
      }

      public double circleArea(double r){
        if(r<=0){
             throw  new ArithmeticException("Radius must be greater then 0") ; 
         }
         return 3.14*r*r;
      }

     public static void main(String[] args){

        Area a = new Area() ; 

        Scanner sc = new Scanner(System.in) ;   
        System.out.println("Enter the width ")  ; 
            int width = sc.nextInt() ; 
        System.out.println("Enter the Heigth ") ;
           int height = sc.nextInt() ; 

        System.out.println("Enter the Radius ") ; 
            int radius = sc.nextInt() ; 
        
        try{ 
             
        System.out.println("Triangle  area : "+ a.triangleArea(height ,width)) ; 
        System.out.println("Rectangle area : " +a.rectangleArea(height, width)) ;
        System.out.println("Square area : " + a.squreArea(width)) ; 
         System.out.println("Circle are : " + a.circleArea(radius)) ;
        }
        catch(ArithmeticException e){
             System.out.println("Error - " +e.getMessage()) ;
        }

        
         






     }
}