package DsaAssigment2;

import java.util.Scanner;

interface Queue {
        boolean isEmpty() ; 
        boolean isFull() ; 
        void delete() ; 
        void add(int data) ; 
    
}


class CirclurQueue implements Queue {
    private int []input ; 
    private int front; 
    private int rear ;
    private int size ; 

    CirclurQueue(int size){
        this.size = size ; 
         this.input = new int[size] ;
         this.rear = -1 ; 
         this.front = -1 ; 
    }

    public void add(int data){
         if(isFull()){
            System.out.println("Data cannot be insert becasue of queue is full : ");
            return ; 
         }

         
          if(rear==-1){
             rear = front = 0 ;
             input[rear] = data ; 
          }else{
            rear = (rear+1)%size ; 
            input[rear] = data ; 
          }
    }


   public void delete(){
        if(isEmpty()){
             System.out.println("Element cannot be delete because the queue is empty ");
                return ; 
        }

        if(rear == front){
             front = rear = -1 ; 
        }
        else{
             front = (front+1)%size ; 
        }

    }

   public boolean isEmpty(){
         if(front ==-1){
             return true ; // empty hai
         }else{
             return false ; // not empty 
         }
    }

   public boolean isFull(){
         if((front ==(rear+1)%size)){
             return true ;  // full hai queue
         }else {
             return false ; // not full queue
         }
    }


    void display(){
        if(isEmpty()){
             System.out.println("Queue is empty : ");
        }else{
             int index = front ; 
             while(true){
                 System.out.print(input[index] + " ");
                 if(index==rear){
                     break ;
                 }
                 index = (index+1)%size; 
             }
        }
         
         System.out.println();

    }
    
}

public class QueueImplemetation {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in) ; 
        System.out.println("enter the size of Queue :");
        int size = sc.nextInt() ; 
        CirclurQueue simplequeue = new CirclurQueue(size) ; 


        while (true) {
        
        System.out.println("1. add element into Queue");
        System.out.println("2. delete element into Queue");
        System.out.println("3. IS empty ");
        System.out.println("4. Is full");
        int choose = sc.nextInt() ; 
        switch (choose) {
            case 1:  System.out.println("Enter the data : "); 
                    int data = sc.nextInt() ;
                    simplequeue.add(data) ;
                break;

            case 2: simplequeue.delete(); 
                break ;

            case 3: System.out.println("Is Queue Empty : " + simplequeue.isEmpty());
                break ; 
            
            case 4: System.out.println("Is Queue Full  : " + simplequeue.isFull()); 
                break;
        }
            System.out.println("press -1 for exit");
            int press = sc.nextInt() ; 
            if(press==-1){
                 break ; 
             }

    }
        simplequeue.display();
         
        

    }
}
