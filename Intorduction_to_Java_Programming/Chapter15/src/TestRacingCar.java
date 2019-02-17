import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;

/**
 * Created by robkio on 2.9.2015.
 */
public class TestRacingCar extends Application {
    private double x = 0;
    private double y = 50;
    private double speed = 0.5;
    private Pane pane = new Pane();
    private Rectangle body;
    private Circle wheel1;
    private Circle wheel2;
    private Polygon roof;

    @Override
    public void start(Stage primaryStage) throws Exception {
        moveCar();
        pane.getChildren().addAll(body, wheel1, wheel2, roof);

        EventHandler<ActionEvent> handler = e -> {
            if(speed < 0) speed = 0;
            if(x >= pane.getWidth())
                x = 0;
            else
                x += speed;
            pane.getChildren().removeAll(body, wheel1, wheel2, roof);
            moveCar();
            pane.getChildren().addAll(body, wheel1, wheel2, roof);
        };

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(10), handler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        pane.setOnMousePressed(e -> animation.pause());
        pane.setOnMouseReleased(e -> animation.play());
        pane.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.UP)
                speed++;
            else if(e.getCode() == KeyCode.DOWN)
                speed--;
        });

        Scene scene = new Scene(pane, 300, 50);
        primaryStage.setTitle("Racing car");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.requestFocus();
    }

    public static void main(String[] args){launch(args);}

    public void moveCar(){
        body = new Rectangle(x, y - 20, 50, 10);
        body.setFill(Color.AQUA);
        wheel1 = new Circle(x + 15, y - 5, 5);
        wheel2 = new Circle(x + 35, y - 5, 5);
        roof = new Polygon(x + 10, y - 20, x + 20, y - 30, x + 30, y - 30, x + 40, y - 20);
        roof.setFill(Color.BLUE);
    }
}
