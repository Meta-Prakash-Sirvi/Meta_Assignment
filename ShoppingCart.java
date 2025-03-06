import java.util.*; 
import java.util.Scanner ; 

class Shopping {
     private HashMap<Item ,  Integer> listofitem = new HashMap<>() ; 

     void addtocart(Item it , int q){
           listofitem.put(it , q); 
           System.out.println("Item is added successfully") ; 
     }

     void display(Item i){
        if(listofitem.containsKey(i)){
          System.out.println("item - "+i.getname() + " quanity - "+ listofitem.get(i)) ; 
        }
        else{
             System.out.println("This item is not available in the cart") ; 
        }
     
     }
     void update(Item i , int q){ 
        if(listofitem.containsKey(i)){
           listofitem.replace(i,q) ;
           System.out.println(" Upadate Successfully ") ; 
        } 
        else{
             System.out.println("Item not found in  cart Firstly add the item into a cart") ; 
        }
     }

     void delete(Item i){
        if(listofitem.containsKey(i)){
           listofitem.remove(i) ; 
           System.out.println("Sucessfully delete the item") ; 
        }
        else{
             System.out.println("Item is not  preaent in the cart ");
        }
     }

     Double display_Bill(){
        double total = 0 ; 
           for(Map.Entry<Item , Integer> set: listofitem.entrySet()){
                total +=(set.getKey().getprice() * set.getValue()) ; 
           }

           return total;
     }
}

class Item{
      
     private int itemid  ; 
     private String name ; 
      private String descrption  ; 
      private float price; 

      public Item(int itemid ,String name , String descrption , float price){
           this.itemid =  itemid;   
            this.name  = name ; 
            this.descrption = descrption ; 
            this.price = price ; 
      }

      int  getitemid(){
         return  itemid ; 
      }
     String getname(){
         return name ; 
     }

     String getdes(){
         return descrption ; 
     }

     float getprice(){
         return price ; 
     }

}


public class shoppingCart{

   static Item find (List<Item> a , int id){
    for(Item i:a){
         if(i.getitemid()==id){
             return i ; 
         }
    }
   
         return null ; 
    
}
    
    
     public static void main(String[] args){
        

          ArrayList<Item> a = new ArrayList<>() ; 

          a.add(new Item(1,"milk" , "liquid" , 26.0f)) ; 
          a.add(new Item(2 ,"banana" , "fruit" , 34)) ; 
          a.add(new Item(3,"Tomato" , "vegetable" ,12)) ; 
          a.add(new Item(4,"coco-cola" ,"cold-drink", 20)) ; 


          Shopping s = new Shopping() ;
          Scanner sc = new Scanner(System.in) ;

        
           int i ; 

          while(true) {
            
        System.out.print("Enter item ID : ") ; 
         int id = sc.nextInt() ;     
         Item check = find(a,id) ; // find the item present or not 
        
        System.out.print("Enter the number :") ; 
        System.out.println("1. add to cart") ; 
        System.out.println("2. Display Quantity") ; 
        System.out.println("3. Update the Item Quantity") ; 
        System.out.println("4. Total bill amount") ;
         int press = sc.nextInt() ; 
         switch(press){
            case 1: //add to cart
         if(check==null){
             System.out.println("Item is not available") ; 
         }
         else{
             s.addtocart(check , 23) ; 
         }
         break ;

         case 2 : // dispaly  qunantity
         if(check==null){
            System.out.println("Item is not available") ; 
         }
         else{
             s.display(check) ; 
         }
         break ; 

         case 3: // update 
         if(check==null){
             System.out.println("Item is not available") ;  ; 
         }
         else{
             s.update(check , 3) ; 
         }
         break ; 

         case 4: // total bill
            System.out.println("Total bill:" +s.display_Bill()) ;
            break ; 

        default : System.out.println("Choose vaild number : ") ; 

         }
         System.out.println("Press the any number : for exit press -1  ") ; 
            
            i = sc.nextInt() ; 
            if(i==-1){
                 break ; 
            }
         }
         
         System.out.println("Total bill : " + s.display_Bill()) ; 

        
        
         

     } 
     
}

