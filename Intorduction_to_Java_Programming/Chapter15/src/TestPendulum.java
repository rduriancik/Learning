import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.Duration;
/**
 * Created by robkio on 3.9.2015.
 */
public class TestPendulum extends Application{
    private double angle = -230;
    private double speed = -0.5;
    @Override
    public void start(Stage stage){
        Pane pane = new Pane();
        Circle circle = new Circle(200, 30, 10);
        Line line = new Line();
        line.startXProperty().bind(circle.centerXProperty());
        line.startYProperty().bind(circle.centerYProperty());
        Circle pendulum = new Circle(20);
        line.endXProperty().bind(pendulum.centerXProperty());
        line.endYProperty().bind(pendulum.centerYProperty());
        pane.getChildren().addAll(circle, line, pendulum);

        EventHandler<ActionEvent> handler = e -> {
            if(angle < -310) speed *= -1;
            if(angle > -230) speed *= -1;
            pendulum.setCenterX(200 + 200 * Math.cos(Math.toRadians(angle)));
            pendulum.setCenterY(30 + 200 * Math.sin(Math.toRadians(angle)));
            angle += speed;
        };

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(20), handler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        pane.setOnKeyPressed(e -> {
            switch(e.getCode()){
                case UP: speed -= 0.1; break;
                case DOWN: speed += 0.1; break;
                case R: animation.play(); break;
                case S: animation.pause(); break;
            }
        });

        Scene scene = new Scene(pane, 400, 300);
        stage.setTitle("Pendulum");
        stage.setScene(scene);
        stage.show();

        pane.requestFocus();
    }

    public static void main(String[] args){launch(args);}
}
