import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Ellipse;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * Created by robkio on 1.10.2015.
 */
public class TestGeometricFigures extends Application {
    @Override
    public void start(Stage primaryStage){
        // Create a StackPane to hold shapes
        StackPane paneForShapes = new StackPane();
        Circle circle = new Circle(30);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Rectangle rectangle = new Rectangle(60, 30);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        Ellipse ellipse = new Ellipse(30, 20);
        ellipse.setFill(Color.WHITE);
        ellipse.setStroke(Color.BLACK);
        paneForShapes.getChildren().add(circle);
        paneForShapes.setStyle("-fx-border-color: black");
        paneForShapes.setPrefHeight(80);
        
        // Create the radio  and a toggle group
        RadioButton rbCircle = new RadioButton("Circle");
        RadioButton rbRectangle = new RadioButton("Rectangle");
        RadioButton rbEllipse = new RadioButton("Ellipse");
        ToggleGroup group = new ToggleGroup();
        rbCircle.setToggleGroup(group);
        rbRectangle.setToggleGroup(group);
        rbEllipse.setToggleGroup(group);
        rbCircle.setSelected(true);

        // Create check box
        CheckBox chkFill = new CheckBox("Fill");

        // Create HBox to hold radio buttons and check box
        HBox hBox = new HBox();
        hBox.getChildren().addAll(rbCircle, rbRectangle, rbEllipse, chkFill);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5));

        // Create BorderPane
        BorderPane pane = new BorderPane();
        pane.setCenter(paneForShapes);
        pane.setBottom(hBox);

        chkFill.setOnAction(e -> {
            if(chkFill.isSelected()){
                circle.setFill(Color.BLACK);
                rectangle.setFill(Color.BLACK);
                ellipse.setFill(Color.BLACK);
            }
            else{
                circle.setFill(Color.WHITE);
                rectangle.setFill(Color.WHITE);
                ellipse.setFill(Color.WHITE);
            }
        });

        rbCircle.setOnAction(e -> {
            if(rbCircle.isSelected()){
                paneForShapes.getChildren().clear();
                paneForShapes.getChildren().add(circle);
            }
        });

        rbRectangle.setOnAction(e -> {
            if(rbRectangle.isSelected()){
                paneForShapes.getChildren().clear();
                paneForShapes.getChildren().add(rectangle);
            }
        });

        rbEllipse.setOnAction(e -> {
            if(rbEllipse.isSelected()){
                paneForShapes.getChildren().clear();
                paneForShapes.getChildren().add(ellipse);
            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestGeometricFigures");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}
}
