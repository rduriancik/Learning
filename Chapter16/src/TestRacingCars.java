import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by robert on 14.11.15.
 */
public class TestRacingCars extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a main VBox pane
        VBox pane = new VBox(5);

        // Create a HBox with speed control
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        hBox.getChildren().add(new Label("Car 1:"));
        TextField tfCar1 = new TextField();
        tfCar1.setPrefColumnCount(2);
        hBox.getChildren().add(tfCar1);

        hBox.getChildren().add(new Label("Car 2:"));
        TextField tfCar2 = new TextField();
        tfCar2.setPrefColumnCount(2);
        hBox.getChildren().add(tfCar2);

        hBox.getChildren().add(new Label("Car 3:"));
        TextField tfCar3 = new TextField();
        tfCar3.setPrefColumnCount(2);
        hBox.getChildren().add(tfCar3);

        hBox.getChildren().add(new Label("Car 5:"));
        TextField tfCar4 = new TextField();
        tfCar4.setPrefColumnCount(2);
        hBox.getChildren().add(tfCar4);

        pane.getChildren().add(hBox);

        // Create four RacingCarPane
        RacingCarPane rcpCar1 = new RacingCarPane();
        RacingCarPane rcpCar2 = new RacingCarPane();
        RacingCarPane rcpCar3 = new RacingCarPane();
        RacingCarPane rcpCar4 = new RacingCarPane();

        pane.getChildren().addAll(rcpCar1, rcpCar2, rcpCar3, rcpCar4);

        // Create action for text fields
        tfCar1.setOnAction(e -> {
            double speed = Double.parseDouble(tfCar1.getText());
            if (speed > 0 && speed < 100) {
                rcpCar1.setSpeed(speed);
            }
            rcpCar1.startCar();
        });
        tfCar2.setOnAction(e -> {
            double speed = Double.parseDouble(tfCar2.getText());
            if (speed > 0 && speed < 100) {
                rcpCar2.setSpeed(speed);
            }
            rcpCar2.startCar();
        });
        tfCar3.setOnAction(e -> {
            double speed = Double.parseDouble(tfCar3.getText());
            if (speed > 0 && speed < 100) {
                rcpCar3.setSpeed(speed);
            }
            rcpCar3.startCar();
        });
        tfCar4.setOnAction(e -> {
            double speed = Double.parseDouble(tfCar4.getText());
            if (speed > 0 && speed < 100) {
                rcpCar4.setSpeed(speed);
            }
            rcpCar4.startCar();
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 410, 220);
        primaryStage.setTitle("TestRacingCars");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

class RacingCarPane extends Pane {
    private Polygon carBody;
    private Circle wheel1;
    private Circle wheel2;
    private double speed;
    private Timeline animation;
    double x;
    double y;

    public RacingCarPane() {
        // Set parameters of the pane and speed
        setPrefWidth(400);
        setPrefHeight(42);
        setStyle("-fx-border-width: 2px; -fx-border-color: black");
        speed = 1;

        // Create a car
        createCar();

        // Initialize carActionEvent
        EventHandler<ActionEvent> carActionHandler = e -> {
            if (x > getWidth()) {
                x = -55;
            }
            x += speed / 10.0;
            refreshPoints();
        };

        // Initialize animation
        animation = new Timeline(new KeyFrame(Duration.millis(10), carActionHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
    }

    public void createCar() {
        // Create a body of the car
        x = 5;
        y = 35;
        carBody = new Polygon();
        carBody.getPoints().addAll(x, y);
        carBody.getPoints().addAll(x, y - 10);
        carBody.getPoints().addAll(x + 10, y - 10);
        carBody.getPoints().addAll(x + 20, y - 20);
        carBody.getPoints().addAll(x + 30, y - 20);
        carBody.getPoints().addAll(x + 40, y - 10);
        carBody.getPoints().addAll(x + 50, y - 10);
        carBody.getPoints().addAll(x + 50, y);
        carBody.setStyle("-fx-fill: red");
        getChildren().add(carBody);

        // Create wheels of the car
        wheel1 = new Circle(x + 15, y + 5, 5);
        wheel1.setStyle("-fx-fill: black");
        getChildren().add(wheel1);
        wheel2 = new Circle(x + 35, y + 5, 5);
        wheel2.setStyle("-fx-fill: black");
        getChildren().add(wheel2);
    }

    private void refreshPoints() {
        carBody.getPoints().set(0, x);
        carBody.getPoints().set(2, x);
        carBody.getPoints().set(4, x + 10);
        carBody.getPoints().set(6, x + 20);
        carBody.getPoints().set(8, x + 30);
        carBody.getPoints().set(10, x + 40);
        carBody.getPoints().set(12, x + 50);
        carBody.getPoints().set(14, x + 50);

        wheel1.setCenterX(x + 15);
        wheel2.setCenterX(x + 35);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void startCar() {
        animation.play();
    }

    public void pauseCar() {
        animation.pause();
    }

    public void stopCar() {
        animation.stop();
    }
}
