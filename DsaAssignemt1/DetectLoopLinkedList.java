package DsaAssignemt1;

import java.util.Scanner;

class Node{
     int data ; 
     Node next ; 
     Node(int data){
         this.data = data ; 
         this.next = null; 
     }
}

public class DetectLoopLinkedList {

    Node insert(Node head , int data){
         Node newNode = new Node(data) ; 
         if(head == null){
             return newNode;  
         }
         Node tempNode = head ; 
         while (tempNode.next != null) {
             tempNode = tempNode.next; 
         }

         tempNode.next = newNode;
          
    
         return head ; 

    }

    void createLoop(Node head , int position){
         if(head==null || position == -1) {
             return ; 
         }

         Node tempNode = head ; 
         Node loopNode = null;
         int pos = 1 ; 

         if(head.next==null && position==1){
             head.next = head ;
         }else{
        while(tempNode.next!=null){
            if(pos==position){
                 loopNode = tempNode ; 
            }
             tempNode = tempNode.next ;
             pos++; 
        }

        if(loopNode!=null){
             tempNode.next = loopNode ; 
        }
    }

    }
      
        public boolean detectLoop(Node head){
             if(head==null || head.next == null){
                 return false;  
             }

             Node fast = head; 
             Node slow = head  ; 
             while (fast!=null && fast.next!=null) {
                   slow = slow.next ; 
                   fast = fast.next.next ; 

                   if(slow==fast){
                     return true ; 
                   }
             }

            return false ; 
        }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in) ; 
        DetectLoopLinkedList ll = new DetectLoopLinkedList() ;  
                
                Node head = null ; 
                System.out.println("Enter the number to add  in linked list : (type  -1 for stop )");
                while (true) {
                    int element = sc.nextInt() ; 
                    if(element==-1){
                         break;  
                    }
                        head =  ll.insert(head, element) ; 
                }


                System.out.println("enter the position to create loop :");
                int position = sc.nextInt(); 
                ll.createLoop(head , position); 


                if(ll.detectLoop(head)) {
                     System.out.println("loop is deteced in linked list " + ll.detectLoop(head));
                }else{
                    System.out.println("loop is  not deteced in linked list ");
                }
    }
}
