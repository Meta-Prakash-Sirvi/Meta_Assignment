import java.util.*; 

class Shopping {
     private HashMap<Item ,  Integer> listofitem = new HashMap<>() ; 

     void addtocart(Item item , int q){
           listofitem.put(item , q); 
           System.out.println("Item is added successfully") ; 
     }

     int display(Item item){
        if(listofitem.containsKey(item)){
         return listofitem.get(item) ; 
        }
        return 0 ;
     
     }
     void update(Item item , int q){ 
        if(listofitem.containsKey(item)){
           listofitem.replace(item,q) ;
           System.out.println(" Update Successfully ") ; 
        } 
        else{
             System.out.println("Item not found in  cart Firstly add the item into a cart") ; 
        }
     }

     void delete(Item item){
        if(listofitem.containsKey(item)){
           listofitem.remove(item) ; 
           System.out.println("Sucessfully delete the item") ; 
        }
        else{
             System.out.println("Item is not  present in the cart ");
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


public class ShoppingCart{

   static Item find (List<Item> store , int id){
    for(Item i:store){
         if(i.getitemid()==id){
             return i ; 
         }
    }
   
         return null ; 
    
}
    
    
     public static void main(String[] args){
        

          ArrayList<Item> store = new ArrayList<>() ; 

          store.add(new Item(1,"milk" , "liquid" , 26)) ; 
          store.add(new Item(2 ,"banana" , "fruit" , 34)) ; 
          store.add(new Item(3,"Tomato" , "vegetable" ,12)) ; 
          store.add(new Item(4,"coco-cola" ,"cold-drink", 20)) ; 

          for(Item i: store){
             System.out.println(i.getitemid() + " " + i.getname()+" "+ i.getdes() +" " + i.getprice()) ;
          }


          Shopping s = new Shopping() ;
          Scanner sc = new Scanner(System.in) ;

        
           int i ; 

          while(true) {
            
        System.out.print("Enter item ID : ") ; 
         int id = sc.nextInt() ;     
         Item check = find(store,id) ; // find the item present or not 
         if(check==null){
             System.out.println("Item is not available"); 
         }else{
               System.out.println("Enter the number :") ; 
               System.out.println("1. add to cart") ; 
               System.out.println("2. Display  quantity for added item") ; 
               System.out.println("3. Update the Item Quantity") ; 
               System.out.println("4. Delete item from to cart") ; 
               System.out.println("5. Total Bill Amount") ;

         int press = sc.nextInt() ; 

         switch(press){
            case 1: //add to cart
            System.out.println("Enter the quantity") ;
              int q = sc.nextInt() ; 
             s.addtocart(check , q) ; 
                break ;

            case 2 : // dispaly  qunantity
               System.out.println(check.getname() + " " +  s.display(check)) ; 
               break ; 

            case 3: // update 
               System.out.println("Enter the quantity ") ; 
               int qu  = sc.nextInt() ; 
               s.update(check , qu) ; 
               break ; 

            case 4: // delete
               s.delete(check) ; 
               break ; 

            case 5: // total bill
               System.out.println("Total bill:" +s.display_Bill()) ;
               break ; 

            default : 
               System.out.println("Choose vaild number : ") ; 

         }
         }

         System.out.println("if you continue press any number : ");
         System.out.println("for exit press -1:") ; 
            
            i = sc.nextInt() ; 
            if(i==-1){
                 break ; 
            }
         }
         
         System.out.println("Total bill : " + s.display_Bill()) ; 
    

     } 
     
}

