package afterChapterApps;

import java.util.*;

/**
 * Created by robert on 1.8.2016.
 */
public class ExpressionFinder {

    private List<String> values;
    private int result;
    private final String[] operators = new String[]{"+", "-", "*", "/"};

    public ExpressionFinder(Integer[] values, int result) {
        this.values = new ArrayList<>();
        for (Integer i : values) {
            this.values.add(Integer.toString(i));
        }

        this.result = result;
    }

    private boolean acceptsOperator(List<String> expression) {
        if (expression == null) {
            return false;
        }

        int n = 0;
        for (String s : expression) {
            if (isDigit(s)) {
                n++;
            } else if (isOperator(s)) {
                n--;
            } else {
                throw new IllegalArgumentException("Invalid Expression.");
            }

            if (n <= 0) {
                return false;
            }
        }

        return n == 1;
    }

    private Integer computePostfix(LinkedList<String> expression) {
        if (expression == null || expression.size() == 0) {
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

    private boolean isDigit(String string) {
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

    private boolean isOperator(String string) {
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

    private Integer makeOperation(String operator, int b, int a) {
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

    private Set<LinkedList<String>> getPermutations(List<String> elements, int range, boolean repeated) {
        if (elements == null || range < 0) {
            return Collections.emptySet();
        }

        Set<LinkedList<String>> permutations = new LinkedHashSet<>();

        if (range == 1) {
            for (String s : elements) {
                permutations.add(new LinkedList<>(Arrays.asList(s)));
            }
            return permutations;
        }

        if (range > elements.size()) {
            range = elements.size();
        }

        for (int i = 0; i < elements.size(); i++) {
            List<String> recursiveList = new ArrayList<>(elements);
            if (!repeated) {
                recursiveList.remove(i);
            }

            Set<LinkedList<String>> temp = getPermutations(recursiveList, range - 1, repeated);

            for (LinkedList<String> s : temp) {
                s.addFirst(elements.get(i));
                permutations.add(s);
            }

        }

        return permutations;
    }

    private LinkedList<String> findExpression() {

        Set<LinkedList<String>> numbersPerm = getPermutations(this.values, values.size(), false);
        Set<LinkedList<String>> operatorsPerm = getPermutations(Arrays.asList(operators), values.size() - 1, true);

        for (LinkedList<String> s : numbersPerm) {
            for (LinkedList<String> n : operatorsPerm) {
                List<String> temp = new ArrayList<>(s.size() + operators.length);
                temp.addAll(s);
                temp.addAll(n);
                Set<LinkedList<String>> possibilities = getPermutations(temp, s.size() + operators.length, false);

                for (LinkedList<String> p : possibilities) {
                    if (!acceptsOperator(p)) {
                        continue;
                    } else if (computePostfix(p) == result) {
                        return p;
                    }
                }
            }
        }

        return null;
    }

    private String postFixToInfix(LinkedList<String> postfix) {
        if (postfix == null || postfix.size() == 0) {
            return null;
        }

        Stack<String> expression = new Stack<>();

        for (String s : postfix) {
            if (s.length() == 0) {
                continue;
            } else if (isDigit(s)) {
                expression.push(s);
            } else if (isOperator(s)) {
                if (expression.size() >= 2) {
                    String temp = expression.pop();
                    expression.push("(" + expression.pop() + " " + s + " " + temp + ")");
                } else {
                    return null;
                }
            }
        }

        return expression.size() == 1 ? expression.pop() : null;
    }

    public String getExpression() {
        String expression = postFixToInfix(findExpression());

        return expression == null ? "Expression does not exist." : expression.substring(1, expression.length() - 1);
    }
}
