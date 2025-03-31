package DsaAssignemt1;

import java.util.Scanner;

class Node{
     int data ; 
     Node next ; 

     Node (int data){
         this.data  = data ; 
         this.next = null ; 
     }
}


public class LinkedListRotate {
        public static Node rotateSubLinkedNode(Node head , int L , int R , int N){
             if(head==null || L>=R){
                 return head ; 
             } 

             Node currNode = head ; 
             Node leftNode =null ; 
             Node rightNode = null ; 
             Node leftbeforeNode = head; 

             for(int index=1 ; index<L ; index++){
                 currNode = currNode.next ; 
             }

             leftNode = currNode ; 
             while(leftbeforeNode.next!=leftNode && L!=1){
                leftbeforeNode = leftbeforeNode.next ; 
             }


             for(int index=L ; index<R ; index++){
                 currNode = currNode.next ; 
             }



             rightNode = currNode ;

            Node  rightNextNode =  rightNode.next ; 
           

            currNode = leftNode ; 

            int len = 1 ;   // checking if N is larger then lenght ;  
             Node temp = leftNode ;
            while (temp!=rightNode) {
                temp =temp.next ;
                len++ ;
            }
           
         
            N = N%len ; 
           
            if(N==0){
                return head ; 
            }else {
              int index =0 ; 
              while (index<len-N-1) {
                currNode = currNode.next ; 
                index++; 
              }
            }
        
          
     
            if(L==1){
                rightNode.next = leftNode; 
                head  = currNode.next ; 
                currNode.next = rightNextNode;

            }else{
            rightNode.next = leftNode;
            leftbeforeNode.next = currNode.next ; 
            currNode.next = rightNextNode ; 
            }
            
            
             return head ; 

    }

    public static  void display(Node head){
         Node tempNode = head ; 
         while(tempNode!=null){
             System.out.print(tempNode.data +" -> ");
             tempNode = tempNode.next ; 
         }
         System.out.println("null");

    }


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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in) ; 
        LinkedListRotate  ll = new LinkedListRotate() ; 
                
                Node head = null ; 
                System.out.println("Enter the number to add  in linked list : (type  -1 for stop )");
                while (true) {
                    int element = sc.nextInt() ; 
                    if(element==-1){
                         break;  
                    }
                        head =  ll.insert(head, element) ; 
                }

        System.out.println("Enter the left position : ");
        int L  = sc.nextInt() ; 
        System.out.println("Enter the right position :");
        int R = sc.nextInt() ; 
        System.out.println("Enter the N :");
        int N = sc.nextInt() ;  

         head = rotateSubLinkedNode(head , L , R , N); 

         display(head) ; 

    }
}
