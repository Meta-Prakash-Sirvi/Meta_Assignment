import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Search{
    int index = 0 ; 
     
    public int linearSearch(ArrayList<Integer> input ,  int element){
         if(index>=input.size()){
             return -1; 
         }

         if(input.get(index)==element) {
             return index ; 
         }
           index++ ; 
         return linearSearch(input, element) ; 
    }

    int counter =0; 
    int start =0 ; 
    int end = 0 ; 
    public int binarySearch(ArrayList<Integer> input, int element){
        if(counter==0){
             end  = input.size()-1 ; 
             counter =1 ; 
        }
         if(start>end){
             return -1 ; 
         }

         int mid = (start+end)/2; 
         if(input.get(mid)==element){
             return mid ; 
         }

         if(input.get(mid)>element){
               end  = mid-1; 
             return binarySearch(input, element); 
         }
         else{
            start = mid+1 ; 
             return binarySearch(input, element);
         }

    }

}
public class Assignment4Q2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        
        Search s = new Search() ;
        //Scanner sc = new Scanner(System.in) ; 
        System.out.println("1 - Linear Searching ");
        System.out.println("2 - Binary Searching ");
        System.out.print("Choose the searching : ") ; 
        int choose = Integer.parseInt(br.readLine()) ; 
 
        //  ArrayList<Integer> input = new ArrayList<>() ; 
         System.out.println("Enter the elements : (type -1 to stop)");
        // String in = br.readLine();
       //  ArrayList<Integer> input1 =  new ArrayList<Integer>(Arrays.asList(in.split(" "))); 

//stream  reading 

        String inputStr = br.readLine();
        ArrayList<Integer> input= new ArrayList<>(Arrays.stream(inputStr.split("\s+")).map((str)->Integer.parseInt(str)).collect(Collectors.toList()));

        switch(choose){
            case 1 : System.out.println("Enter the element you want to search ");
                     int element = Integer.parseInt(br.readLine()) ; 
                     int position = s.linearSearch(input, element) ;
                     if(position!=-1){
                         System.out.println("Element found at index : " + position);
                     }else{
                         System.out.println("Element not found ");
                     }

            break ; 
            
            case 2: Collections.sort(input) ; // sort the input array
                    System.out.println("Enter the element you want to search ");
                    int element1 = Integer.parseInt(br.readLine()) ;
                    int position1 = s.binarySearch(input, element1);
                    if(position1!=-1){
                        System.out.println("Element found at index : " + position1);
                    }else{
                        System.out.println("Element not found ");
                    }
            break ; 
        }
        

        
    }

    public Object linearSearch(ArrayList<Integer> arr1, int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'linearSearch'");
    }
}
