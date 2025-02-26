package com.spring.main.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class Spring3Service {

    //------------- Infix to Postfix Conversion -------------
    public String infixToPostfix(String s) {
        Stack<String> stack = new Stack<>();
        
        StringBuilder numBuffer = new StringBuilder();
        List<String> elements = new ArrayList<>();
        System.out.println(s);
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                numBuffer.append(c);  // Build multi-digit numbers
            } else {
                if (numBuffer.length() > 0) {
                    elements.add(numBuffer.toString());  // Add full number
                    numBuffer.setLength(0); // Clear buffer
                }
                if (c != ' ') { // Ignore spaces
                    elements.add(String.valueOf(c));  // Add operator or parentheses
                }
            }
        }
        if (numBuffer.length() > 0) {
            elements.add(numBuffer.toString()); // Add last number
}

    

    System.out.println("Tokens: " + elements); // Debugging output

        List<String> postfix = new ArrayList<>();

        // Debugging: Print the correctly split tokens
        System.out.println("Tokens: " + elements);

        for (String element : elements) {
            if (isNumeric(element)) {  
                postfix.add(element); 
            } else if (isOperator(element)) {  
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(element) && !stack.peek().equals("(")) {
                    postfix.add(stack.pop());
                }
                stack.push(element);
            } else if (element.equals("(")) { 
                stack.push(element);
            } else if (element.equals(")")) { 
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.add(stack.pop());
                }
                if (!stack.isEmpty()) stack.pop(); // Remove "("
            }
        }

        while (!stack.isEmpty()) { 
            postfix.add(stack.pop()); 
        }

        return String.join(" ", postfix);
    }

    // ------------- Evaluation of Postfix Expression -------------
    public Double evaluatePostfix(String s) {
        Stack<Double> stack = new Stack<>();
        
        List<String> elements = Arrays.asList(s.split("\\s+"));
        Double op1, op2;

        for (String element : elements) {
            if (isNumeric(element)) {
                stack.push(Double.parseDouble(element));
            } else {
                op2 = stack.pop();
                op1 = stack.pop();
                stack.push(evaluate(op1, op2, element));
            }
        }
        return stack.pop();
    }

    //----------- Helper Methods -----------
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

    public static Double evaluate(Double a, Double b, String s) {
        switch (s) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
            default: throw new IllegalArgumentException("Invalid operator: " + s);
        }
    }
}
