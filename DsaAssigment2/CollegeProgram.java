package DsaAssigment2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.lang.reflect.Array;
import java.util.*;



class Pair{
     String first ; 
     String second ; 
     Pair(String first , String second){
         this.first = first ; 
         this.second = second ; 
     }

     String getFirst(){
         return first ; 
     }
     String getSecond(){
         return second ; 
     }
}


class Student{
   private int rank ; 
    private String name ; 
     String []prefrences ; 

     Student(int rank , String name , String []gprefrences){
        this.rank = rank ; 
         this.name = name ; 
         prefrences = new String[5] ; 
         for(int index = 0 ; index<gprefrences.length ; index++){
             prefrences[index] = gprefrences[index]; 
         }
     }

     int getRank(){
         return rank ;
     }

    String getName(){
         return name ; 
     }

    
}



interface Queue {
    ArrayList<Pair> delete() ; 
    void add(Student student) ; 

}


class SimpleQueue implements Queue {
     private Student []input ; 
        private int front; 
        private int rear ;
        private int size ; 

  public static HashMap<String , Integer> program = new HashMap<>() ;
  static{
        program.put("CS" , 1) ; 
        program.put("ECE", 1) ; 
        program.put("EC" , 1 ) ; 
        program.put("IT" , 2) ;
        program.put("ME", 3) ; 
        program.put("BCA" ,0);;
        program.put("MCA" , 1) ;  

        
  }

    SimpleQueue(int size){
        this.size = size ; 
        this.input = new Student[size] ;
        this.rear = -1 ; 
        this.front = -1 ; 
    }

    public void add(Student student){
        if(rear==-1){
            rear = front = 0 ;
            input[rear] = student ; 
        }else{
            rear++ ; 
            input[rear] = student ; 
        }
    }


    public ArrayList<Pair> delete(){
        ArrayList<Pair>  allocationDetails = new ArrayList<Pair>();
        while (front<=rear) {
        Student s = input[front]; 
        String name  = s.getName(); 
        int index=0 ; 
        boolean allocation = false ; 
        while(index<s.prefrences.length){
             String allocateSubject = s.prefrences[index] ; 
             if(program.containsKey(allocateSubject) && program.get(allocateSubject)>0){
                allocationDetails.add(new Pair(name , allocateSubject)); 
                 program.put(allocateSubject , program.get(allocateSubject)-1);
                 allocation  = true ; 
                 break ;
             }else{
                index++;
            
             }
        }
        if(!allocation){
             allocationDetails.add(new Pair(name,"Not allocated" ));
        }
        front++ ; 
    }
        return allocationDetails;

    }


    void display(){
            System.out.println("Student Name ");
                for(int index = front ; index<=rear ; index++){
                     System.out.println(input[index].getName() + "  " + input[index].getRank());
                }
                System.out.println();

    }
}



public class CollegeProgram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in) ; 
        ArrayList<Student> studentDetails = new ArrayList<>() ; 

        System.out.println("How many  Student Participate  for counseling process : ");
        int numbersOfStudent = sc.nextInt() ; 

         while(numbersOfStudent>0){
            System.out.println("Enter the name of Student : ");
             String name  = sc.next() ; 
            System.out.println("Enter the Rank of the Student : ");
            int rank = sc.nextInt() ; 
              System.out.println("Enter the subject choice of student : (student can fill only 5 choice)");
              String []subject = new String[5] ; 
              for(int index=0 ; index<5 ; index++){
                  subject[index] = sc.next() ; 
              }
            
            Student student = new Student(rank,name , subject); 

            studentDetails.add(student) ;

            numbersOfStudent--; 


         }


        SimpleQueue queue = new SimpleQueue(studentDetails.size()) ; 
        Collections.sort(studentDetails, Comparator.comparingInt(c -> c.getRank())); 
        for(Student s: studentDetails) {
              queue.add(s) ; 
        }

    
       queue.display() ;
       

       ArrayList<Pair>  allocation = new ArrayList<>();

       allocation = queue.delete() ; 
         for(int i=0 ; i<allocation.size() ; i++){
             System.out.println(allocation.get(i).getFirst() + "->" + allocation.get(i).getSecond());
         }

         

    }
}
