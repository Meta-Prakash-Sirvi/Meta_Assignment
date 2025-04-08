package DSAASSIGNEMT4;

import java.util.Scanner;
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

public class MolecularMass {
    static Stack<Integer> massWeight = new Stack<>();
    static Map<Character, Integer> molecularMasss = new HashMap<>();
    static {
        molecularMasss.put('C', 12);
        molecularMasss.put('H', 1);
        molecularMasss.put('O', 16);
    }

    public static int findMolecularMass(String input) {
        int size = input.length();
        int result = 0;
        int index = 0;
        while (index < size) {
            char ch = input.charAt(index);
            char nextch = (index + 1 < input.length()) ? input.charAt(index + 1) : 0;
            if (ch >= 65 && ch <= 90) {
                massWeight.push(molecularMasss.get(ch));
            } else if ((ch >= '1' && ch <= '9') && (nextch >= '0' && nextch <= '9')) {

                int element = massWeight.pop();
                massWeight.push(element * (10 * ((int) (ch - '0')) + ((int) (nextch - '0'))));
                index++;
            } else if (ch >= '1' && ch <= '9') {
                int element = massWeight.pop();
                element = element * ((int) ch - '0');
                massWeight.push(element);

            } else if (ch == '(') {
                massWeight.push(-1);

            } else {
                int temp = 0;
                while (!massWeight.isEmpty() && massWeight.peek() != -1) {
                    temp += massWeight.pop();
                }
                massWeight.pop();
                massWeight.push(temp);
            }
            index++;
        }

        while (!massWeight.isEmpty()) {

            result += massWeight.pop();

        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the  organic chemistry formula");
        String input = sc.next();

        System.out.println("Molecular Mass : " + findMolecularMass(input));
    }
}
