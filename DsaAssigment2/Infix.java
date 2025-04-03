
package DsaAssigment2;
import java.util.*;

class Operation {
    private Stack<String> operator = new Stack<>();
    private Stack<Integer> operands = new Stack<>();

    int checkPrecedence (String s1) {
        switch (s1) {
            case "*": return 12; 
            case "/": return 12;
            case "+": return 11; 
            case "-": return 11;
            case "==": return 8;
            case "!=": return 8; 
            case "<": return 8;
            case ">": return 8;
            case ">=": return 8;
            case "<=": return 8;
            case "&&": return 4; 
            case "||": return 3 ; 

        }
        
        return 0;
    }

    int operation(String s1) {
        String[] s2 = s1.split("\\s+");

        for (String token : s2) {
            if (token.matches("-?\\d+")) {
                operands.push(Integer.parseInt(token));
            }
            else if(token.equals("(")){
                 operator.push(token) ; 
            }else if(token.equals(")")){
                while(!operator.isEmpty() && !operator.peek().equals("(")){
                     evaluate() ; 
                }
                operator.pop() ; 
                 
            }else { 
                while (!operator.isEmpty() && checkPrecedence(operator.peek()) >= checkPrecedence(token)) {
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
            case "==": operands.push(firstValue == secondValue ? 1 : 0);
                break;

            case "<":
                operands.push(firstValue < secondValue ? 1 : 0);
                break;

            case ">":
                operands.push(firstValue > secondValue ? 1:0);
                break;
            
            case "<=":
                operands.push(firstValue <= secondValue ? 1 : 0);
                break;

            case ">=":
                operands.push(firstValue >= secondValue ? 1 : 0);
                break;

            case "!=":
                operands.push(firstValue != secondValue ? 1 : 0);
                break;

            case "&&":
                operands.push((firstValue!=0 && secondValue!=0) ? 1 : 0);
                break;

            case "||":
                operands.push((firstValue!=0 || secondValue!=0) ? 1 : 0);
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
        System.out.println("Result is: " + op.operation(string));
    }
}