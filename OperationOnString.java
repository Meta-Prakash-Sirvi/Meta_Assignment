import java.util.Scanner ; 
public class OperationOnString{
        int compareStrings(String s1 ,String s2) {
             int len1 = s1.length() ; 
             int len2 = s2.length() ;

                if(len1!=len2) {
                     return 0; 
                }else{
                    for(int i=0 ; i<len1 ; i++) {
                        if(s1.charAt(i)!=s2.charAt(i)){
                             return 0 ; 
                         
                        }   
                }
            return 1 ;              
                }

        }

        String convert(String s1) {    // lower case to upper case 
            int size = s1.length() ; 
            String ans ="" ;
            for(int i=0 ; i<size; i++) {
                char ch = s1.charAt(i) ; 
                if(ch>='A' && ch<='Z') {
                     ans+=(char)(ch+32) ; 
                }
                else if(ch>='a' && ch<='z') {  // if character already in uppercase 
                     ans+=(char)(ch-32) ; 
                }
            }
            return ans ;
        }

        String reverseString(String s2) {
            String r = "" ; 
            int size = s2.length() ; 
            for(int i=size-1 ; i>=0 ; i--) {
                 r+=s2.charAt(i) ; 
            }
            return r; 
        }

        String largestWord(String s1){
             String ans ="" ; 
             String temp="" ; 
             int size = s1.length() ; 
             for(int i=0 ; i<size ; i++){
                 char ch = s1.charAt(i) ; 
                 if(ch==' '){
                    if(ans.length()<=temp.length()){
                     ans = temp ; 
                    }
                     temp="" ;    //reset the string 
                  }    
                 else if(i==size-1){   // check last word 
                            temp+=ch ; // last word addtion
                       if(ans.length()<=temp.length()){  // if last word greater
                            ans = temp ; 
                       }
                 }
                 else{
                     temp+=ch ; 
                 }
             }
             return ans ;
        }

    public static void main(String[] args){
         OperationOnString o1 = new OperationOnString() ; 
            Scanner sc = new Scanner(System.in) ; 
             String s1 = sc.next() ; 
             String s2 =sc.next() ; 

             int  check = o1.compareStrings(s1 ,s2) ; 
             if(check==1){
                 System.out.println("Both Strings are Equal : " +s1+"="+s2) ; 
             }
             else{
                 System.out.println(s1 +" , " + s2 + " Both Strings are Not Equal  ") ; 
             }

            
            System.out.println("Upper latter : " + o1.convert(s1)) ; 

            System.out.println("Reverse String : "+ o1.reverseString(s2)) ; 

            System.out.println("Largest Word in String : "+ o1.largestWord("I am Trainee Software Engineer at MetaCube")) ; 
            

      }
}