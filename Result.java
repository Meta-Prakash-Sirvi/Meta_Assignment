import java.util.*; 
public class Result{
   private ArrayList<Integer> marks  = new ArrayList<>(); 
        
        void addmarks(int m){
             if(m<0 || m>100){
                 throw new ArithmeticException("Marks Must be between 0 and 100 ") ; 
             }else{
              marks.add(m) ; 
             }
        }

        double avgMarks(){   //avrage marks 
                 double sum = 0.0f; 
                 int n = marks.size(); 
                for(int i:marks){
                     sum+=i ; 
                }
                 return sum/n ; 
        }

        int maxMarks(){
                 int maximum = -1 ;   // minimum marks is always 0 
                 for(int i:marks){
                     if(maximum<i){
                         maximum = i ;
                     }
                 }
                 return maximum ; 
             }

        int minMarks(){
                 int minimum = 101 ;   //maximum marks are 100
                for(int i:marks){
                     if(minimum>i){
                         minimum = i ;
                     }
                }
                return minimum ; 
        }

        double percentage(){
                 int totalPass = 0 ; 
                 int n = marks.size() ;
                 for(int i:marks){
                     if(i>=40){
                         totalPass++ ; 
                     }
                 }
                 return (totalPass*100)/n ; 
        }

     public static void main(String[] args) {
    
         Result r = new Result() ;
         
         Scanner sc = new Scanner(System.in) ; 
         System.out.println("Enter the  number of students  : "); 
         int subject = sc.nextInt() ; 

         try{
         for(int i=0 ; i<subject ; i++){
                System.out.print("Enter the grades of each student  :") ; 
                int marks = sc.nextInt() ;
                r.addmarks(marks);
         }
        
         System.out.println("1 - calculate the Average marks : ");
         System.out.println("2 - calculate the Maximum marks : ");
         System.out.println("3 - calculate the Minimum marks : ");
         System.out.println("4 - calculate the total passed student percentage : ");
         System.out.println("Enter the choicd : ");
         int choose = sc.nextInt() ; 

         switch (choose) {
            case 1:  System.out.println("Avgrage marks of all Student : " + String.format("%.2f", r.avgMarks())); 
                 break;

            case 2:  System.out.println("Maximum marks is : "+r.maxMarks()) ; 
                break ;

            case 3:  System.out.println("Minimum marks is : " + r.minMarks()) ; 
                 break ;

            case 4:   System.out.println("Total passed student percentage is : "+String.format("%.2f" , r.percentage())) ; 
                 break ;
         }
         }
         catch(ArithmeticException e){
             System.out.println("Exception : "+e.getMessage()); 
         }

     }
}