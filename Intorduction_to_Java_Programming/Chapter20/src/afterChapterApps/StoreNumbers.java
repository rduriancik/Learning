package afterChapterApps;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by robert on 22.4.2016.
 */
public class StoreNumbers extends Application {
    private List<Integer> numbers = new LinkedList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();

        HBox hbTop = new HBox(5);
        hbTop.setAlignment(Pos.CENTER);
        hbTop.getChildren().add(new Label("Enter a number:"));
        TextField tfInput = new TextField();
        tfInput.setPrefColumnCount(5);
        tfInput.setAlignment(Pos.CENTER_LEFT);
        hbTop.getChildren().add(tfInput);
        borderPane.setTop(hbTop);
        BorderPane.setMargin(hbTop, new Insets(5, 0, 0, 0));

        TextArea taNumbers = new TextArea();
        taNumbers.setEditable(false);
        borderPane.setCenter(taNumbers);
        BorderPane.setMargin(taNumbers, new Insets(5));

        HBox hbBottom = new HBox(5);
        hbBottom.setAlignment(Pos.CENTER);
        Button btSort = new Button("Sort");
        Button btShuffle = new Button("Shuffle");
        Button btReverse = new Button("Reverse");
        hbBottom.getChildren().addAll(btSort, btShuffle, btReverse);
        borderPane.setBottom(hbBottom);
        BorderPane.setMargin(hbBottom, new Insets(0, 0, 5, 0));

        tfInput.setOnAction(e -> {
            numbers.add(Integer.valueOf(tfInput.getText()));

            tfInput.clear();
            taNumbers.clear();

            for (Integer i : numbers) {
                taNumbers.setText(taNumbers.getText() + " " + i );
            }
        });

        btSort.setOnAction(e -> {
            Collections.sort(numbers);

            taNumbers.clear();

            for (Integer i : numbers) {
                taNumbers.setText(taNumbers.getText() + " " + i );
            }
        });

        btShuffle.setOnAction(e -> {
            Collections.shuffle(numbers);

            taNumbers.clear();

            for (Integer i : numbers) {
                taNumbers.setText(taNumbers.getText() + " " + i );
            }
        });

        btReverse.setOnAction(e -> {
            Collections.reverse(numbers);

            taNumbers.clear();

            for (Integer i : numbers) {
                taNumbers.setText(taNumbers.getText() + " " + i );
            }
        });
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Store numbers");
        primaryStage.show();
    }
}
