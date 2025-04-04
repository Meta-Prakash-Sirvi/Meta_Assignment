package DsaAssigment3;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node{
     String name; 
     Node left ;
     Node right ; 

     Node(String name){
         this.name = name ;
         this.left = null ; 
         this.right =  null;  
     }
}


class CommandPrompt{

    public Node mkdir(Node root , String s1){
        Node newnode = new Node(s1); 
         if(root.left==null){
             root.left = newnode ; 
         }else{
             root.right = newnode ; 
         }

         return root ; 
    }

    Node find(Node root , String s1){
         if(root==null){
             return null ; 
         }
         if(root.name.equals(s1)){
             return root ; 
         }

        Node  left = find(root.left, s1) ;
        if(left!=null){
             return left ; 
        }
        return find(root.right, s1) ;

        
    }


    void ls(Node root){
        if(root==null || root.left==null && root.right==null){
             System.out.println("No directories found");
             
        } else{
             System.out.println("Folders insides  : " + root.name );
             if(root.left!=null){
                 System.out.println(root.left.name);
             }
             if(root.right!=null){
                 System.out.println(root.right.name);
             }
        }
    }


    


     Node cd(Node root, String s1){
          Node temp = find(root, s1); 
          if(temp==null){
             System.out.println("Error :  Directory is not found");
             return root ;
          }
        return temp; 
     }

     void tree(Node root , String pF , boolean flag){
         if(root!=null){
             System.out.println(pF + (flag ? "|--" : "|__")+ root.name);
            tree(root.left, pF + (flag ? "|  ":"  "), true);
            tree(root.right, pF + (flag ? "|  ":"  "), false);
             
         }
     }
   
   
     
}

public class VirtualCommandPrompt {   

    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in) ; 
       
        VirtualCommandPrompt vc = new VirtualCommandPrompt() ;
        CommandPrompt c = new CommandPrompt() ;  
        Node rootNode  = new Node("MetaCude") ; 
        Node root = rootNode ; 
          while(true){
            System.out.println("enter the commands : ");
             String choose = sc.next() ; 
             switch (choose) {
                case "mkdir": System.out.println("Enter the folder name");
                        String name =  sc.next() ; 
                        root  = c.mkdir(root, name); 
                        break ;
                        
                case "tree" : c.tree(rootNode , "" , true);
                    break;

                case "cd": System.out.println("enter the folder name to move ");
                    String folder = sc.next() ; 
                    root = c.cd(root, folder) ; 
                    break ; 

                case "find": System.out.println("enter the folder name to find ");
                    String folderName  = sc.next() ; 
                    Node temp =  c.find(rootNode, folderName) ; 
                    if(temp!=null){
                        System.out.println("Directory found" + temp.name);
                    }else{
                         System.out.println("Directory Not found");
                    }
                    break ;

                case "ls":
                        c.ls(root);
                        break ; 

                default : System.out.println("Invalid command : ");
                break ; 
 
             }

             if(choose.equals("exit")){
                 break ; 
             }
          }


    }
}
