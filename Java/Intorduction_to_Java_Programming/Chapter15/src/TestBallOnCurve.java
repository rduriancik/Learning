/**
 * Created by robkio on 1.9.2015.
 */
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.PathTransition;
import javafx.util.Duration;
import javafx.collections.ObservableList;

public class TestBallOnCurve extends Application{
    @Override
    public void start(Stage stage){
        Pane pane = new Pane();

        Line xAxis = new Line(10, 410, 690, 410);
        Line yAxis = new Line(350, 10, 350, 690);
        Polyline xArrow = new Polyline(670, 400, 690, 410, 670, 420);
        Polyline yArrow = new Polyline(340, 30, 350, 10, 360, 30);
        Text txtX = new Text(685, 425, "x");
        Text txtY = new Text(360, 15, "y");
        pane.getChildren().addAll(xAxis, yAxis, xArrow, yArrow, txtX, txtY);

        Polyline sinus = new Polyline();
        ObservableList<Double> list = sinus.getPoints();
        double scaleFactor = 100;
        for(int i = -340; i <= 340; i++){
            list.add(350.0 + i);
            list.add(410 - scaleFactor * Math.sin((i / 200.0) * 2 * Math.PI));
        }
        pane.getChildren().add(sinus);

        Text minusTwoPI = new Text(350 - 200, 425, "-2\u03c0");
        Text minusPi = new Text(350 - 92, 425, "\u03c0");
        Text zero = new Text(356, 425, "0");
        Text Pi = new Text(350 + 108, 425, "\u03c0");
        Text twoPi = new Text(350 + 205, 425, "2\u03c0");
        pane.getChildren().addAll(minusTwoPI, minusPi, zero, Pi, twoPi);

        Circle point = new Circle(10, Color.YELLOW);
        pane.getChildren().add(point);

        PathTransition pt = new PathTransition(Duration.millis(5000), sinus, point);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.play();

        pane.setOnMousePressed(e -> {
            if(e.getButton() == MouseButton.PRIMARY)
                pt.play();
            else if(e.getButton() == MouseButton.SECONDARY)
                pt.pause();
        });

        Scene scene = new Scene(pane, 700, 700);
        stage.setTitle("Ball on curve");;
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
