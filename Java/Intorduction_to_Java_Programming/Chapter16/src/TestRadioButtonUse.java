import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.control.ToggleGroup;

/**
 * Created by robkio on 30.9.2015.
 */
public class TestRadioButtonUse extends Application {
    @Override
    public void start(Stage primaryStage){
        Pane paneForText = new Pane();
        Text text = new Text(10, 80, "Programming is fun");
        text.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        paneForText.getChildren().add(text);
        paneForText.setStyle("-fx-border-color: black");

        RadioButton rbRed = new RadioButton("Red");
        RadioButton rbYellow = new RadioButton("Yellow");
        RadioButton rbBlack = new RadioButton("Black");
        RadioButton rbOrange = new RadioButton("Orange");
        RadioButton rbGreen = new RadioButton("Green");
        ToggleGroup toggleGroup = new ToggleGroup();
        rbRed.setToggleGroup(toggleGroup);
        rbYellow.setToggleGroup(toggleGroup);
        rbBlack.setToggleGroup(toggleGroup);
        rbOrange.setToggleGroup(toggleGroup);
        rbGreen.setToggleGroup(toggleGroup);
        HBox hBoxForRb = new HBox(5);
        hBoxForRb.getChildren().addAll(rbRed, rbYellow, rbBlack, rbOrange, rbGreen);
        hBoxForRb.setAlignment(Pos.CENTER);

        Button btLeft = new Button("<=");
        Button btRight = new Button("=>");
        HBox hBoxForBt = new HBox(5);
        hBoxForBt.getChildren().addAll(btLeft, btRight);
        hBoxForBt.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBoxForRb);
        borderPane.setCenter(paneForText);
        borderPane.setBottom(hBoxForBt);

        rbRed.setOnAction(e -> {
            if (rbRed.isSelected()) {
                text.setFill(Color.RED);
            }
        });

        rbYellow.setOnAction(e -> {
            if (rbYellow.isSelected()) {
                text.setFill(Color.YELLOW);
            }
        });

        rbBlack.setOnAction(e -> {
            if (rbBlack.isSelected()) {
                text.setFill(Color.BLACK);
            }
        });

        rbOrange.setOnAction(e -> {
            if (rbOrange.isSelected()) {
                text.setFill(Color.ORANGE);
            }
        });

        rbGreen.setOnAction(e -> {
            if(rbGreen.isSelected()){
                text.setFill(Color.GREEN);
            }
        });

        btLeft.setOnAction(e -> text.setX(text.getX() - 10));
        btRight.setOnAction(e -> text.setX(text.getX() + 10));

        Scene scene = new Scene(borderPane, 400, 170);
        primaryStage.setTitle("TestRadioButton");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}
}
