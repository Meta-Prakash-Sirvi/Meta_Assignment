package DSAASSIGNEMT4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StringUnique {
   private Map<String, Integer> cache = new HashMap<>();

    int findNumberOfUnique(String input) {

        if (cache.containsKey(input)) {
            int value = cache.get(input);
            System.out.println("Retrieve from the Stored Result");
            return value;
        }

        Map<Character, Integer> unique = new HashMap<>();
        int uniqueCount = 0;
        int size = input.length();
        for (int index = 0; index < size; index++) {
            char ch = input.charAt(index);
            unique.put(ch, unique.getOrDefault(ch, 0) + 1);

        }
        for (int count : unique.values()) {
            if (count == 1) {
                uniqueCount++;
            }
        }
        cache.put(input, uniqueCount);
        return uniqueCount;

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringUnique stringUnique = new StringUnique();

        while (true) {
            System.out.print("Enter the String : ");
            String userString = sc.next();
            System.out.println("Unique Character in the String : " + stringUnique.findNumberOfUnique(userString));

            System.out.print("WANT TO CONTINUE (Y/N): ");
            String str = sc.next();

            if (str.equals("N")) {
                return;
            }

        }

    }
}
