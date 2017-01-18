package afterChapterApps;

import java.util.Stack;

/**
 * Created by robert on 26.5.2016.
 */
public class PostfixNotation {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java PostfixNotation \"expression\"");
            System.exit(1);
        }

        Integer result = parseExpression(args);
        if (result == null) {
            System.err.println("Invalid expression");
        } else {
            System.out.println("Result is " + result);
        }
    }

    private static Integer parseExpression(String[] expression) {
        if (expression == null || expression.length == 0) {
            return null;
        }

        Stack<Integer> operator = new Stack<>();

        for (String s : expression) {
            if (isDigit(s)) {
                operator.push(Integer.valueOf(s));
            } else if (isOperator(s)) {
                if (operator.size() >= 2) {
                    Integer result = makeOperation(s, operator.pop(), operator.pop());
                    if (result != null) {
                        operator.push(result);
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }

        if (operator.size() != 1) {
            return null;
        }

        return operator.pop();
    }

    private static boolean isDigit(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }
        for (Character c : string.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isOperator(String string) {
        if (string == null || string.isEmpty() || string.length() > 1) {
            return false;
        }

        if (string.equals("+") ||
                string.equals("-") ||
                string.equals("*") ||
                string.equals("/")) {
            return true;
        }

        return false;
    }

    private static Integer makeOperation(String operator, int b, int a) {
        if (operator == null || operator.isEmpty()) {
            return null;
        }

        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                return null;
        }
    }
}
