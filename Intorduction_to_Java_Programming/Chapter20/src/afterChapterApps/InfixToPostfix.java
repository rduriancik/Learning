package afterChapterApps;

import java.io.BufferedInputStream;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by robert on 4.7.2016.
 */
public class InfixToPostfix {
    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedInputStream(System.in));

        System.out.println("Enter an expression in infix notation: ");
        String expression = input.nextLine();

        try {
            System.out.println(infixToPostfix(expression));
        } catch (Exception ex) {
            System.out.println("Invalid expression.");
        }

    }

    /**
     * Inserts blanks between operators and operands
     */
    private static String insertBlanks(String expression) {
        String result = "";

        for (Character ch : expression.toCharArray()) {
            if (ch == '+' || ch == '-'
                    || ch == '*' || ch == '/'
                    || ch == '(' || ch == ')') {
                result += " " + ch + " ";
            } else {
                result += ch;
            }
        }

        return result;
    }

    /**
     * Converts the infix expression to postfix
     */
    private static String infixToPostfix(String expression) {
        if (expression == null || expression.length() == 0) {
            throw new IllegalArgumentException("Wrong expression");
        }

        String postfix = "";

        expression = insertBlanks(expression);
        String[] temp = expression.split(" ");

        if (!verifyExpression(temp)) {
            throw new IllegalArgumentException("Wrong argument");
        }

        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < temp.length; i++) {
            if (temp[i].length() == 0) {
                continue;
            } else if (temp[i].charAt(0) == '+'
                    || temp[i].charAt(0) == '-') {

                while (!operators.isEmpty()
                        && isOperator(operators.peek().toString())) {
                    postfix += operators.pop() + " ";
                }

                operators.push(temp[i].charAt(0));

            } else if (temp[i].charAt(0) == '*'
                    || temp[i].charAt(0) == '/') {

                while (!operators.isEmpty()
                        && (operators.peek() == '*'
                        || operators.peek() == '/')) {
                    postfix += operators.pop() + " ";
                }

                operators.push(temp[i].charAt(0));

            } else if (temp[i].charAt(0) == '(') {
                operators.push(temp[i].charAt(0));
            } else if (temp[i].charAt(0) == ')') {
                while (operators.peek() != '(') {
                    postfix += operators.pop() + " ";
                }

                operators.pop();
            } else {
                postfix += temp[i] + " ";
            }
        }

        while (!operators.isEmpty()) {
            postfix += operators.pop() + " ";
        }

        return postfix;
    }

    /**
     * Checks if the string is a number
     */
    private static boolean isDigit(String numb) {
        if (numb == null || numb.length() == 0) {
            return false;
        }

        for (Character c : numb.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verifies the expression
     */
    private static boolean verifyExpression(String[] expression) {
        if (expression == null || expression.length == 0) {
            return false;
        }

        boolean operator = false;
        boolean operand = true;
        short parentheses = 0;

        for (String s : expression) {
            if (s.length() == 0) {
                continue;
            } else if (s.charAt(0) == '(') {
                parentheses++;
            } else if (s.charAt(0) == ')') {
                parentheses--;
            } else if (isDigit(s) && operand) {
                operator = true;
                operand = false;
            } else if (isOperator(s) && operator) {
                operand = true;
                operator = false;
            } else {
                return false;
            }
        }

        return parentheses == 0 && operator;
    }

    /**
     * Checks if the string is an operator
     */
    private static boolean isOperator(String op) {
        if (op == null || op.length() != 1) {
            return false;
        }

        return op.charAt(0) == '+'
                || op.charAt(0) == '-'
                || op.charAt(0) == '*'
                || op.charAt(0) == '/';
    }
}
