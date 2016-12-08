import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * Created by robkio on 4.9.2015.
 */
public class TestBeanMachineAnimation extends Application {
    private ArrayList<Circle> circle = new ArrayList<>();
    ArrayList<Circle> ball = new ArrayList<>();
    private double y = -5;
    private double x = 100;
    private double point = 35;
    @Override
    public void start(Stage stage){
        Pane pane = new Pane();

        //Points
        final double radius = 5;
        int count = 0;
        for(int i = 100; i >= 30; i-=10){
            for(int j = 0; j <= count; j++){
                circle.add(new Circle(i + (20 * j), 40 + (20 * count), radius, Color.ORANGE));
            }
            count++;
        }
        for(Circle c : circle)
            pane.getChildren().add(c);

        //Body
        Polyline body = new Polyline(85, 5, 85, 25, 10, 185, 10, 235, 190, 235, 190, 185, 115, 25, 115, 5);
        pane.getChildren().add(body);
        Line[] line = new Line[8];
        for(int i = 0; i < line.length; i++)
            line[i] = new Line(30 + (20 * i), 185, 30 + (20 * i), 235);
        pane.getChildren().addAll(java.util.Arrays.asList(line));

        //ANIMATION
        ball.add(new Circle(x, y, radius, Color.GREEN));
        pane.getChildren().add(ball.get(0));

        EventHandler<ActionEvent> handler = e -> {
            if(ball.size() <= 10) {
                ball.get(ball.size() - 1).setCenterY(ball.get(ball.size() - 1).getCenterY() + 1);
                if (ball.get(ball.size() - 1).getCenterY() <= 180 &&
                        ball.get(ball.size() - 1).getCenterY() + radius == point) {
                    point += 20;
                    int random = (int) (Math.random() * 2);
                    if (random == 0)
                        ball.get(ball.size() - 1).setCenterX(ball.get(ball.size() - 1).getCenterX() - 10);
                    else
                        ball.get(ball.size() - 1).setCenterX(ball.get(ball.size() - 1).getCenterX() + 10);
                }

                if (isDown()) {
                    point = 35;
                    ball.add(new Circle(x, y, radius, Color.GREEN));
                    pane.getChildren().add(ball.get(ball.size() - 1));
                }
            }
        };

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(20), handler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        Scene scene = new Scene(pane, 200, 245);
        stage.setTitle("Bean Machine");
        stage.setScene(scene);
        stage.show();
    }

    public boolean isDown(){
        return ball.get(ball.size() - 1).getCenterY() == countEndY();
    }

    public double countEndY(){
        int count = 0;
        for(int i = 0; i < ball.size() - 1; i++){
            if(ball.get(i).getCenterX() == ball.get(ball.size() - 1).getCenterX())
                count++;
        }
        return 230 - (10 * count);
    }

    public static void main(String[] args){launch(args);}
}
