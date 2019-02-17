package afterChapterApps.hangmanGUI;

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
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by robert on 4.5.2016.
 */
public class Game extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HangmanPane hangmanPane = new HangmanPane();
        HangmanBackend backend = new HangmanBackend();
        backend.addWord("auto");
        backend.addWord("pes");
        backend.addWord("skuska");

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(hangmanPane);

        Label lbInfo = new Label("Hangman game");
        lbInfo.setFont(Font.font(20));
        borderPane.setTop(lbInfo);
        BorderPane.setAlignment(lbInfo, Pos.CENTER);
        BorderPane.setMargin(lbInfo, new Insets(5));

        VBox vBox = new VBox(5);
        Label lbWord = new Label("");
        TextField tfGuess = new TextField();
        tfGuess.setPrefColumnCount(1);
        tfGuess.setEditable(false);
        Button btGuess = new Button("Guess");
        btGuess.setDisable(true);
        btGuess.setOnAction((e) -> {
            String text = tfGuess.getText();
            if (!text.isEmpty() && text.length() == 1) {
                if (backend.guessWord(text.charAt(0))) {
                    lbWord.setText(backend.getWord());
                } else {
                    hangmanPane.paint();
                }
                tfGuess.setText("");
            }

            if (backend.isWholeWord()) {
                tfGuess.setEditable(false);
                btGuess.setDisable(true);
                lbInfo.setText("You win!");
            }

            if (hangmanPane.isWholeHangman()) {
                tfGuess.setEditable(false);
                btGuess.setDisable(true);
                lbInfo.setText("You lose!");
            }
        });

        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(5));
        vBox.getChildren().add(lbWord);
        vBox.getChildren().addAll(new HBox(5, tfGuess, btGuess));
        borderPane.setLeft(vBox);

        Button btNewWord = new Button("New word");
        btNewWord.setOnAction(e -> {
            backend.initialize();
            lbWord.setText(backend.getWord());
            btGuess.setDisable(false);
            tfGuess.setEditable(true);
            hangmanPane.restart();
            lbInfo.setText("Hangman Game");
        });

        borderPane.setBottom(btNewWord);
        BorderPane.setAlignment(btNewWord, Pos.CENTER);
        BorderPane.setMargin(btNewWord, new Insets(5));
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hangman");
        primaryStage.show();
    }
}
