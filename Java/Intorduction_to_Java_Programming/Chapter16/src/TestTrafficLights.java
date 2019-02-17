import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.control.RadioButton;
import javafx.geometry.Pos;

/**
 * Created by robkio on 1.10.2015.
 */
public class TestTrafficLights extends Application {
    @Override
    public void start(Stage primaryStage){
        // Create a pane to hold shapes
        Pane paneForShape = new Pane();
        Rectangle body = new Rectangle(95, 10, 60, 200);
        body.setFill(Color.WHITE);
        body.setStroke(Color.BLACK);
        Circle redLight = new Circle(125, 40, 25);
        redLight.setFill(Color.WHITE);
        redLight.setStroke(Color.BLACK);
        Circle orangeLight = new Circle(125, 110, 25);
        orangeLight.setFill(Color.WHITE);
        orangeLight.setStroke(Color.BLACK);
        Circle greenLight = new Circle(125, 180, 25);
        greenLight.setFill(Color.WHITE);
        greenLight.setStroke(Color.BLACK);
        paneForShape.getChildren().addAll(body, redLight, orangeLight, greenLight);

        // Create the radio buttons and place them in a toggle group
        RadioButton rbRed = new RadioButton("Red");
        RadioButton rbOrange = new RadioButton("Orange");
        RadioButton rbGreen = new RadioButton("Green");
        ToggleGroup group = new ToggleGroup();
        rbGreen.setToggleGroup(group);
        rbOrange.setToggleGroup(group);
        rbRed.setToggleGroup(group);

        // Create HBox to hold the radio buttons
        HBox hBoxForRadioButtons = new HBox(5);
        hBoxForRadioButtons.getChildren().addAll(rbRed, rbOrange, rbGreen);
        hBoxForRadioButtons.setAlignment(Pos.CENTER);
        hBoxForRadioButtons.setPadding(new Insets(5));

        // Create a BorderPane
        BorderPane pane = new BorderPane();
        pane.setCenter(paneForShape);
        pane.setBottom(hBoxForRadioButtons);

        // Create the action events for the objects
        rbRed.setOnAction(e -> {
            if(rbRed.isSelected()){
                redLight.setFill(Color.RED);
                orangeLight.setFill(Color.WHITE);
                greenLight.setFill(Color.WHITE);
            }
        });

        rbOrange.setOnAction(e -> {
            if(rbOrange.isSelected()){
                orangeLight.setFill(Color.ORANGE);
                redLight.setFill(Color.WHITE);
                greenLight.setFill(Color.WHITE);
            }
        });

        rbGreen.setOnAction(e -> {
            if(rbGreen.isSelected()){
                greenLight.setFill(Color.GREEN);
                redLight.setFill(Color.WHITE);
                orangeLight.setFill(Color.WHITE);
            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle("TestTrafficLights");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}
}
