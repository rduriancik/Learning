package afterChapterApps.cardGame;

import afterChapterApps.ExpressionFinder;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


/**
 * Created by robert on 25.5.2016.
 */
public class CardGame extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CardPane cardPane = new CardPane();

        Label lbInfo = new Label();
        lbInfo.setPadding(new Insets(0, 0, 5, 5));
        Button btFindSolution = new Button("Find solution");
        TextField tfSolution = new TextField();
        tfSolution.setPrefColumnCount(40);

        Button btShuffle = new Button("Shuffle");
        HBox topHBox = new HBox(5);
        topHBox.setAlignment(Pos.BASELINE_CENTER);
        topHBox.setPadding(new Insets(5));
        topHBox.getChildren().addAll(btFindSolution, tfSolution, btShuffle);

        TextField tfExpression = new TextField();
        tfExpression.setPrefColumnCount(40);
        Button btVerify = new Button("Verify");
        HBox bottomHBox = new HBox(5);
        bottomHBox.setAlignment(Pos.BASELINE_CENTER);
        bottomHBox.setPadding(new Insets(5));
        bottomHBox.getChildren().addAll(new Label("Enter an expression:"), tfExpression, btVerify);

        btVerify.setOnAction(e -> {
            switch (verify(tfExpression.getText(), cardPane.getValues())) {
                case 'e':
                    lbInfo.setText("Invalid expression");
                    break;
                case 'c':
                    lbInfo.setText("Correct");
                    break;
                case 'i':
                    lbInfo.setText("Incorrect");
                    break;
                case 'm':
                    lbInfo.setText("The numbers in the expression don't match the numbers in the set");
                    break;
                case 'n':
                    lbInfo.setText("Null card values");
                    break;
                default:
                    lbInfo.setText("Error");
            }
        });

        btShuffle.setOnAction(e -> {
            cardPane.showCards();
            lbInfo.setText("");
            tfExpression.setText("");
            tfSolution.setText("");
        });

        btFindSolution.setOnAction(e -> {
            ExpressionFinder exp = new ExpressionFinder(cardPane.getValues(), 24);
            tfSolution.setText(exp.getExpression());
        });

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(topHBox);
        mainPane.setCenter(cardPane);
        mainPane.setBottom(new VBox(bottomHBox, lbInfo));

        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("24-Point Card Game");
        primaryStage.show();
    }

    /**
     * Verifies the expression
     */
    private char verify(String expression, Integer[] cardValues) {
        if (expression.isEmpty()) {
            return 'e';
        }

        if (cardValues == null) {
            return 'n';
        }

        int result = 0;
        try {
            result = evaluateExpression(expression);
        } catch (Exception ex) {
            return 'e';
        }

        if (!verifyNumbers(cardValues, expression)) {
            return 'm';
        }

        return result == 24 ? 'c' : 'i';
    }

    /**
     * Verifies if numbers in expression are equals to cards values
     */
    private boolean verifyNumbers(Integer[] values, String expression) {
        if (values == null || expression.isEmpty()) {
            return false;
        }

        String[] temp = splitExpression(expression);
        List<Integer> list = new ArrayList<>(Arrays.asList(values));
        if (temp.length != 4) return false;
        for (String s : temp) {
            if (!contains(list, Integer.parseInt(s))) {
                return false;
            }
            list.remove(Integer.valueOf(s));
        }

        return true;
    }

    /**
     * Checks if an Integer list contains value only once
     */
    private boolean contains(List<Integer> list, int value) {
        if (list == null) {
            return false;
        }
        for (Integer i : list) {
            if (i == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * Removes each string without numbers
     */
    private String[] splitExpression(String expression) {
        List<String> temp = new ArrayList<String>(Arrays.asList(expression.split("\\D")));
        temp.removeIf(s -> s.matches("\\D*"));
        return temp.toArray(new String[0]);
    }

    private int evaluateExpression(String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("Illegal expression");
        }

        Stack<Character> operatorStack = new Stack<>();
        Stack<Integer> operandStack = new Stack<>();

        expression = insertBlanks(expression);
        String[] tokens = expression.split(" ");

        for (String s : tokens) {
            if (s.length() == 0) {
                continue;
            } else if (s.charAt(0) == '+' || s.charAt(0) == '-') {
                if (!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '+'
                                || operatorStack.peek() == '-'
                                || operatorStack.peek() == '*'
                                || operandStack.peek() == '/')) {
                    processOperator(operatorStack, operandStack);
                }

                operatorStack.push(s.charAt(0));
            } else if (s.charAt(0) == '*' || s.charAt(0) == '/') {
                while (!operatorStack.isEmpty() &&
                        (operatorStack.peek() == '/' ||
                                operatorStack.peek() == '*')) {
                    processOperator(operatorStack, operandStack);
                }

                operatorStack.push(s.charAt(0));
            } else if (s.trim().charAt(0) == '(') {
                operatorStack.push('(');
            } else if (s.trim().charAt(0) == ')') {
                while (operatorStack.peek() != '(') {
                    processOperator(operatorStack, operandStack);
                }
                operatorStack.pop();
            } else {
                operandStack.push(new Integer(s));
            }
        }

        while (!operatorStack.isEmpty()) {
            processOperator(operatorStack, operandStack);
        }

        return operandStack.pop();
    }

    private void processOperator(Stack<Character> operatorStack, Stack<Integer> operandStack) {
        char operand = operatorStack.pop();
        int op2 = operandStack.pop();
        int op1 = operandStack.pop();

        switch (operand) {
            case '+':
                operandStack.push(op1 + op2);
                break;
            case '-':
                operandStack.push(op1 - op2);
                break;
            case '*':
                operandStack.push(op1 * op2);
                break;
            case '/':
                operandStack.push(op1 / op2);
                break;
        }
    }

    private String insertBlanks(String expression) {
        String result = "";
        for (Character c : expression.toCharArray()) {
            if (c == '+' || c == '-' ||
                    c == '*' || c == '/' ||
                    c == '(' || c == ')') {
                result += " " + c + " ";
            } else {
                result += c;
            }
        }

        return result;
    }
}
