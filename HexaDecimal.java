import java.util.* ; 

class HexCalc{
      
      public int  hexatodec(String hexa) {
         int decimal =0 ; 
         int j = 0 ; 
         int len = hexa.length() ; 

         for(int i = len-1; i>=0 ; i--) {
              char ch = hexa.charAt(i) ; 
               if(ch>='0' && ch<='9'){
                     decimal += (ch-'0')* Math.pow(16 ,j) ; 

                     j++ ; 
               }
               else if(ch>='A' && ch<='F'){
                     decimal += (ch-'A'+10)* Math.pow(16 , j) ;
                     j++ ; 
               }
              
         }

         return decimal ; 
      }

       public String dectohex(int  dec) {
            if(dec==0){
                return "0" ; 
            }
            StringBuilder  s = new StringBuilder() ; 

            while(dec>0){
                int rem  = dec%16 ; 
                if(rem<=9){
                     s.append((char)('0'+ rem)) ; 
                }
                else {
                      s.append((char)('A'+ (rem-10))) ; 
                }

                dec/=16 ; 
            }
       return s.reverse().toString() ; 
       }

       public String add(String s1 , String s2) {    //addintion 
              
               int number1 = hexatodec(s1) ; 
               int number2 = hexatodec(s2) ; 

                 int ans = number1 + number2 ; 

                 return dectohex(ans) ; 
              
       }

       public String sub(String s1 ,String s2) {  //substraction
              int number1 = hexatodec(s1) ; 
              int number2 = hexatodec(s2) ; 

              if(number1<number2){
                  return "-1" ;
              }
              int ans = number1-number2 ; 

              return dectohex(ans) ; 
       }

       public String multi(String s1 , String s2) {   //multiplication 
           int number1 = hexatodec(s1) ; 
           int number2 = hexatodec(s2) ; 

           if(number1==0 || number2==0){
                return "0" ;
           }

           int ans = number1* number2 ; 

           return dectohex(ans) ; 
       }

       public String div(String s1 ,String s2) {   // division 
           int number1 = hexatodec(s1) ; 
           int number2 = hexatodec(s2) ; 
            if(number2==0){
                return "-1" ; 
            }

            int ans = number1/number2 ; 

            return dectohex(ans) ; 
       }
       boolean compare(String s1 , String s2){
             int len1 = s1.length() ; 
             int len2 = s2.length() ;

             if(len1>len2){
                return true ; 
             }
             else if(len1<len2){
                return false ; 
             }
             else{
                return true ; 
             }
           
       }  

}

public class HexaDecimal{
      public static void main(String[] args) {
          HexCalc h = new HexCalc() ; 
          Scanner sc = new Scanner(System.in) ; 
           String s1 = "A5F" ;
           String s2 = "A3F" ;

          System.out.println("addtion  = " +  h.add(s1 , s2)) ;
          System.out.println("Substraction = " + h.sub(s1 ,s2)) ; 
          System.out.println("Multiplycation = " + h.multi(s1 , s2)) ; 
          System.out.println("Division = "+ h.div(s1 ,s2)) ; 

          System.out.println("first heaxdecimal is greater than Second hexadecimal =" + h.compare(s1 , s2)) ; 
          System.out.println("hexadecimal to decimal  first number = "+h.hexatodec(s1)) ; 
          System.out.println("hexadecimal to decimal second number = " +h.hexatodec(s2)) ; 
           


      }
}