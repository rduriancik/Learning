import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by robert on 20.11.15.
 */
public class TestConsecutiveFourEqualsNumbers extends Application {

    private TextField[][] fields;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a main BorderPane
        BorderPane pane = new BorderPane();

        // Create a top label
        Label lbText = new Label("Enter numbers");
        pane.setTop(lbText);
        BorderPane.setAlignment(lbText, Pos.CENTER);

        // Create a center GridPane with TextFields
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5, 10, 5, 10));
        fields = new TextField[6][7];
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j] = new TextField();
                fields[i][j].setPrefColumnCount(1);
                fields[i][j].setAlignment(Pos.CENTER);
                gridPane.add(fields[i][j], j, i);
            }
        }
        pane.setCenter(gridPane);

        // Create a bottom Button
        Button btSolve = new Button("Solve");
        pane.setBottom(btSolve);
        BorderPane.setAlignment(btSolve, Pos.CENTER);

        // Create an action event for the button
        btSolve.setOnAction(e -> {
            if (!isValidInput(fields)) {
                lbText.setText("Input isn't valid");
                return;
            }

            setFieldsStyle();
            if (isConsecutiveInRow() || isConsecutiveInColumn() || isConsecutiveInDiagonal()) {
                lbText.setText("A consecutive four found");
            } else {
                lbText.setText("A consecutive four wasn't found");
            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestConsecutiveFourEqualsNumbers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Returns true if the array of TextFields has valid input, false if not
     *
     * @param fields an array of TextFields
     * @return true if the input is valid, false if not
     */
    private boolean isValidInput(TextField[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                String input = fields[i][j].getText();
                if (input.isEmpty() || input.length() > 1 || Character.isLetter(input.charAt(0))) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns true if the array of TextFields has four same numbers in a row and marks
     * four consecutive numbers in the array, false if not
     *
     * @return true if is consecutive four in a row, false if not
     */
    private boolean isConsecutiveInRow() {
        boolean isConsecutiveFour = false;
        int count;
        int input1;
        int input2;
        int[] indexOfConsecutive = new int[2];

        for (int i = 0; i < fields.length; i++) {
            count = 1;
            for (int j = 0; j < fields[i].length - 1; j++) {
                input1 = Integer.parseInt(fields[i][j].getText());
                input2 = Integer.parseInt(fields[i][j + 1].getText());

                if (input1 == input2) {
                    count++;
                } else {
                    count = 1;
                }

                if (count == 4) {
                    isConsecutiveFour = true;
                    indexOfConsecutive[0] = i;
                    indexOfConsecutive[1] = j - 2;
                    break;
                }
            }
        }

        if (isConsecutiveFour) {
            // Mark the consecutive four
            for (int i = indexOfConsecutive[1]; i < indexOfConsecutive[1] + 4; i++) {
                fields[indexOfConsecutive[0]][i].setStyle("-fx-border-color: red");
            }
        }

        return isConsecutiveFour;
    }

    /**
     * Returns true if the array of TextFields has four same numbers in a column and marks
     * four consecutive numbers in the array, false if not
     *
     * @return true if is consecutive four in a column, false if not
     */
    private boolean isConsecutiveInColumn() {
        boolean isConsecutiveFour = false;
        int count;
        int input1;
        int input2;
        int[] indexOfConsecutive = new int[2];

        for (int j = 0; j < fields[0].length; j++) {
            count = 1;
            for (int i = 0; i < fields.length - 1; i++) {
                input1 = Integer.parseInt(fields[i][j].getText());
                input2 = Integer.parseInt(fields[i + 1][j].getText());

                if (input1 == input2) {
                    count++;
                } else {
                    count = 1;
                }

                if (count == 4) {
                    isConsecutiveFour = true;
                    indexOfConsecutive[0] = i - 2;
                    indexOfConsecutive[1] = j;
                    break;
                }
            }
        }

        if (isConsecutiveFour) {
            // Mark the consecutive four
            for (int i = indexOfConsecutive[0]; i < indexOfConsecutive[0] + 4; i++) {
                fields[i][indexOfConsecutive[1]].setStyle("-fx-border-color: red");
            }
        }

        return isConsecutiveFour;
    }

    /**
     * Returns true if the array of TextFields has four same numbers in a diagonal and marks
     * four consecutive numbers in the array, false if not
     *
     * @return @return true if is consecutive four in a diagonal, false if not
     */
    private boolean isConsecutiveInDiagonal() {
        boolean isConsecutiveFour = false;
        int count;
        int input1;
        int input2;
        int[] indexOfConsecutive = new int[2];

        // Check the diagonal from the last element of the first column to the first element of the last column
        for (int k = 3; k < fields.length + fields[0].length - 4; k++) {
            count = 1;
            for (int j = 0; j <= k; j++) {
                int i = (fields.length - 1) - (k - j);
                if (i >= 0 && i < fields.length - 1 && j >= 0 && j < fields[0].length - 1) {
                    input1 = Integer.parseInt(fields[i][j].getText());
                    input2 = Integer.parseInt(fields[i + 1][j + 1].getText());

                    if (input1 == input2) {
                        count++;
                    } else {
                        count = 1;
                    }

                    if (count == 4) {
                        isConsecutiveFour = true;
                        indexOfConsecutive[0] = i - 2;
                        indexOfConsecutive[1] = j - 2;
                        break;
                    }
                }
            }
        }

        if (isConsecutiveFour) {
            for (int i = 0; i < 4; i++) {
                fields[indexOfConsecutive[0] + i][indexOfConsecutive[1] + i].setStyle("-fx-border-color: red");
            }
        } else {
            // Check the diagonal from the first element of the first column to the last element of the last column
            for (int k = 3; k < fields.length + fields[0].length - 4; k++) {
                count = 1;
                for (int j = 0; j <= k; j++) {
                    int i = k - j;
                    if (i > 0 && i < fields.length && j >= 0 && j < fields[0].length - 1) {
                        input1 = Integer.parseInt(fields[i][j].getText());
                        input2 = Integer.parseInt(fields[i - 1][j + 1].getText());

                        if (input1 == input2) {
                            count++;
                        } else {
                            count = 1;
                        }

                        if (count == 4) {
                            isConsecutiveFour = true;
                            indexOfConsecutive[0] = i + 2;
                            indexOfConsecutive[1] = j - 2;
                            break;
                        }
                    }
                }
            }
            if (isConsecutiveFour) {
                for (int i = 0; i < 4; i++) {
                    fields[indexOfConsecutive[0] - i][indexOfConsecutive[1] + i].setStyle("-fx-border-color: red");
                }
            }

        }

        return isConsecutiveFour;
    }

    /**
     * This method sets a border color of each TextField to null
     */
    private void setFieldsStyle() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j].setStyle("-fx-border-color: null");
            }
        }
    }
}
