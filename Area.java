import java.util.Scanner; 

public class Area{
    
      public double triangle_area(double h , double w){
        if(h<=0 || w<=0){
             throw  new ArithmeticException("Height and Width must be greater then 0") ; 
         }
             return (h*w)/2 ; 
      }
      public double rectangle_area(double h, double w){
        if(h<=0 || w<=0){
             throw  new ArithmeticException("Height and Width must be greater then 0") ; 
         }
           return  (h*w) ;
      }

      public double squre_area(double w){
        if(w<=0){
             throw  new ArithmeticException("Width must be greater then 0") ; 
         }
         return  w*w ;
      }

      public double circle_area(double r){
        if(r<=0){
             throw  new ArithmeticException("Radius must be greater then 0") ; 
         }
         return 2*3.14*r ;
      }

     public static void main(String[] args){

        Area a = new Area() ; 

        Scanner sc = new Scanner(System.in) ;   
        System.out.println("Enter the width ")  ; 
            int width = sc.nextInt() ; 
        System.out.println("Enter the Height ") ;
           int height = sc.nextInt() ; 

        System.out.println("Enter the Radius ") ; 
            int radius = sc.nextInt() ; 
        
        try{ 
             
        System.out.println("Triangle  area : "+ a.triangle_area(height ,width)) ; 
        System.out.println("Rectangle area : " +a.rectangle_area(height, width)) ;
        System.out.println("Square area : " + a.squre_area(width)) ; 
        System.out.println("Circle are : " + a.circle_area(radius)) ;
        }
        catch(ArithmeticException e){
             System.out.println("Error - " +e.getMessage()) ;
        }

         






     }
}