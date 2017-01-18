package afterChapterApps;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by robert on 8.8.2016.
 */
public class Game24 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();

        // Top of main pane
        HBox topHbox = new HBox(5);
        topHbox.setAlignment(Pos.CENTER);
        topHbox.setPadding(new Insets(5));

        TextField tfSolution = new TextField();
        tfSolution.setAlignment(Pos.BASELINE_LEFT);
        tfSolution.setEditable(false);

        Button btSolve = new Button("Solve");

        topHbox.getChildren().addAll(tfSolution, btSolve);
        mainPane.setTop(topHbox);

        // Center of main pane
        HBox centerHbox = new HBox(5);
        centerHbox.setAlignment(Pos.CENTER);
        centerHbox.setPadding(new Insets(0, 5, 5, 5));

        TextField[] tfNumbers = new TextField[4];
        for (int i = 0; i < tfNumbers.length; i++) {
            tfNumbers[i] = new TextField();
            tfNumbers[i].setStyle(
                    "-fx-font-size: 25;" +
                            "-fx-alignment: center;" +
                            "-fx-pref-column-count: 2;"
            );
        }

        tfNumbers[0].textProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                int n = Integer.parseInt(newValue);
                if (n > 13) {
                    tfNumbers[0].setText("13");
                }
                if (n < 1) {
                    tfNumbers[0].setText("1");
                }
            } catch (NumberFormatException ex) {
                tfNumbers[0].setText("");
            }
        }));

        tfNumbers[1].textProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                int n = Integer.parseInt(newValue);
                if (n > 13) {
                    tfNumbers[1].setText("13");
                }
                if (n < 1) {
                    tfNumbers[1].setText("1");
                }
            } catch (NumberFormatException ex) {
                tfNumbers[1].setText("");
            }
        }));

        tfNumbers[2].textProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                int n = Integer.parseInt(newValue);
                if (n > 13) {
                    tfNumbers[2].setText("13");
                }
                if (n < 1) {
                    tfNumbers[2].setText("1");
                }
            } catch (NumberFormatException ex) {
                tfNumbers[2].setText("");
            }
        }));

        tfNumbers[3].textProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                int n = Integer.parseInt(newValue);
                if (n > 13) {
                    tfNumbers[3].setText("13");
                }
                if (n < 1) {
                    tfNumbers[3].setText("1");
                }
            } catch (NumberFormatException ex) {
                tfNumbers[3].setText("");
            }
        }));

        btSolve.setOnAction(event -> {
            Integer[] values = new Integer[4];
            try {
                for (int i = 0; i < values.length; i++) {
                    values[i] = Integer.valueOf(tfNumbers[i].getText());
                }
            } catch (NumberFormatException ex) {
                tfSolution.setText("Empty text field");
            }

            ExpressionFinder exp = new ExpressionFinder(values, 24);
            tfSolution.setText(exp.getExpression());
        });


        centerHbox.getChildren().addAll(tfNumbers);
        mainPane.setCenter(centerHbox);

        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("24-Game");
        primaryStage.show();
    }
}
