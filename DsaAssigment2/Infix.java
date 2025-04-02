package DsaAssigment2;

import java.util.*;

class Operation {
    private Stack<String> operator = new Stack<>();
    private Stack<Integer> operands = new Stack<>();

    int check(String s1) {
        switch (s1) {
            case "*": return 2; 
            case "/": return 2;
            case "+": return 1; 
            case "-": return 1;
        }
        
        return 0;
    }

    int solve(String s1) {
        String[] s2 = s1.split("\\s+");

        for (String token : s2) {
            if (token.matches("-?\\d+")) {
                operands.push(Integer.parseInt(token));
            } else { // If token is an operator
                while (!operator.isEmpty() && check(operator.peek()) >= check(token)) {
                    evaluate();
                }
                operator.push(token);
            }
        }

        while (!operator.isEmpty()) {
            evaluate();
        }

        return operands.pop();
    }

    private void evaluate() {
        if (operands.size() == 1) {
            return;
        }

        int secondValue = operands.pop();
        int firstValue = operands.pop();
        String op = operator.pop();

        switch (op) {
            case "+":
                operands.push(firstValue + secondValue);
                break;
            case "-":
                operands.push(firstValue - secondValue);
                break;
            case "*":
                operands.push(firstValue * secondValue);
                break;
            case "/":
                operands.push(firstValue / secondValue);
                break;
        }
    }
}

public class Infix {
    public static void main(String[] args) {
        Operation op = new Operation();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string ");
        String string = sc.nextLine();
        System.out.println("Result is: " + op.solve(string));
    }
}