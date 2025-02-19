import java.util.*;

public class Postfix {
    public static void main(String[] args) {
    }


    //-------------infixToPostfix---------

    public static String infixToPostfix(String s) {
        Stack<String> stack = new Stack<>();
        List<String> elementss = Arrays.asList(s.split("\\s+")); // Splitting by spaces
        List<String> postfix = new ArrayList<>();

        for (String elements : elementss) {
            if (isNumeric(elements)) {  // check for number
                postfix.add(elements); 
            } else if (isOperator(elements)) {  // check for operator
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(elements)) {
                    postfix.add(stack.pop()); //check precedence operators
                }
                stack.push(elements);
            } else if (elements.equals("(")) { 
                stack.push(elements);
            } else if (elements.equals(")")) { 
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.add(stack.pop());
                }
                stack.pop(); // Remove "("
            }
        }

        while (!stack.isEmpty()) {
            postfix.add(stack.pop()); // Pop remaining operators
        }

        return String.join(" ", postfix);
    }


    // ------------ calculation of postfix---------

    public static Double evaluatePostfix(String s) {
        Stack<Double> stack = new Stack<>();
        List<String> elements = Arrays.asList(s.split("\\s+"));
        Double op1, op2;

        for (String element : elements) {
            if (isNumeric(element)) {
                stack.push(Double.parseDouble(element));
            } 
            else {
                op2 = stack.pop();
                op1 = stack.pop();
                stack.push(evaluate(op1, op2,  element));
            }
        }
        return (Double) stack.pop();
    }





//-----------helper methods-----
    public static boolean isNumeric(String s) {
        return s.matches("-?[0-9]+(\\.[0-9]+)?");
    }

    public static boolean isOperator(String s) {
        return s.matches("[+\\-*/]");
    }

    public static int precedence(String s) {
        switch (s) {
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                return 0;
        }
    }

    public static Double evaluate(Double a, Double b, String s){
        switch (s){
            case "+":
                return  a +  b;
            case "-":
                return ( a - b);
            case "*":
                return ( a *  b);
            case "/":
                return (a /  b);
            default:
                throw new IllegalArgumentException("Invalid operator: " + s);
        }
    }

}