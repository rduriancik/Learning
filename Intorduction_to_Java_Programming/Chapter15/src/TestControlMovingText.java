/**
 * Created by robkio on 1.9.2015.
 */
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.PathTransition;
import javafx.util.Duration;

public class TestControlMovingText extends Application{
    @Override
    public void start(Stage stage){
        Pane pane = new Pane();
        Line line = new Line(-65, 50, 365, 50);
        Text text = new Text("Programming is fun");
        pane.getChildren().add(text);

        PathTransition pt = new PathTransition(Duration.millis(4000), line, text);
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.play();

        pane.setOnMousePressed(e -> pt.pause());
        pane.setOnMouseReleased(e -> pt.play());

        Scene scene = new Scene(pane, 300, 100);
        stage.setTitle("Moving text");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
