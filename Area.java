import java.util.Scanner; 

public class Area{
    
      public double triangleArea(double h , double w){
        if(h<=0 || w<=0){
             throw  new ArithmeticException("Height and Width for must be greater then 0") ; 
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
          System.out.println("Enter the number you want to calculate the area : ");
          System.out.println("1 - Circle Area");
          System.out.println("2 - Rectangle Area");
          System.out.println("3 - squre Area");
          System.out.println("4 - Triangle Area");
            System.out.println("Enter the choice");
         int choose = sc.nextInt() ; 
         try{
         switch(choose){
             case 1: System.out.println("Enter the radius : ");
                    int radius = sc.nextInt(); 
                    System.out.println("Circle Area = "+a.circleArea(radius));
                     break ; 

            case 2: System.out.println("Enter the heigth and width : ");
                    int widthRec = sc.nextInt(); 
                    int heightRec = sc.nextInt();
                    System.out.println("Rectangle Area = " + a.rectangleArea(heightRec, widthRec));
                break ; 
            
            case 3: System.out.println("Enter the width : ");
                    int widthsq = sc.nextInt(); 
                    System.out.println("Square Area =" + a.squreArea(widthsq));
                    break ; 

            case 4:  System.out.println("Enter the heigth and width : ");
                     int widtthri = sc.nextInt(); 
                     int heigthri = sc.nextInt(); 
                     System.out.println("Triangle area  = " +a.triangleArea(heigthri,widtthri));
                     break ;

         }
        }catch(ArithmeticException e){
             System.out.println("Erorr :"+e.getMessage());
        }


     }
}