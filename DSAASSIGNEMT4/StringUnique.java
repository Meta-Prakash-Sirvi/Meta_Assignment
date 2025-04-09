package DSAASSIGNEMT4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class StringUnique {
   private Map<String, Integer> cache = new HashMap<>();

    int findNumberOfUnique(String input) {

        if (cache.containsKey(input)) {
            int value = cache.get(input);
            System.out.println("Retrieve from the Stored Result");
            return value;
        }
        
        String[]unique = input.split("") ;  
        Set<String> set = new HashSet<>(Arrays.asList(unique)) ;
        
        cache.put(input, set.size());
        return set.size() ; 
          

    

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringUnique stringUnique = new StringUnique();

        while (true) {
            System.out.print("Enter the String : ");
            String userString = sc.next();
            System.out.println("Unique Character in the String : " + stringUnique.findNumberOfUnique(userString));

            System.out.print("WANT TO CONTINUE (Y/N): ");
            String string = sc.next();

            if (string.equals("N")) {
                return;
            }

        }

    }
}
