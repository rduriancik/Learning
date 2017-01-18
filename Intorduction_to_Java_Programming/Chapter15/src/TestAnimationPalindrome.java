/**
 * Created by robkio on 31.8.2015.
 */
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.animation.PathTransition;
import javafx.util.Duration;

public class TestAnimationPalindrome extends Application{
    @Override
    public void start(Stage stage){
        Pane pane = new Pane();

        Arc arc = new Arc(120, 20, 100, 50, 200, 140);
        arc.setType(ArcType.OPEN);
        arc.setStyle("-fx-fill: null; -fx-stroke: BLACK");
        pane.getChildren().add(arc);

        Circle point = new Circle(20, Color.RED);
        pane.getChildren().add(point);

        PathTransition pt = new PathTransition(Duration.millis(4000), arc, point);
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(true);
        pt.play();

        pane.setOnMousePressed(e -> pt.pause());
        pane.setOnMouseReleased(e -> pt.play());

        Scene scene = new Scene(pane, 240, 100);
        stage.setTitle("Animation Palindrome");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
