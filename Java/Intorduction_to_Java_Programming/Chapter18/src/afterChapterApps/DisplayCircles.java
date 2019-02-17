package afterChapterApps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Created by robert on 22.01.16.
 */
public class DisplayCircles extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane pane = new StackPane();

        pane.widthProperty().addListener(ov -> {
            pane.getChildren().clear();
            drawCircles(pane, 5);
        });
        pane.heightProperty().addListener(ov -> {
            pane.getChildren().clear();
            drawCircles(pane, 5);
        });

        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("DisplayCircles");
        primaryStage.show();
    }

    private void drawCircles(Pane pane, double radius) {
        Circle circle = new Circle(radius);
        circle.setStroke(Color.BLACK);
        circle.setFill(null);

        if (pane.getWidth() / 2 + radius <= pane.getWidth() - 10
                && pane.getHeight() / 2 + radius <= pane.getHeight() - 10) {
            pane.getChildren().add(circle);
            drawCircles(pane, radius + 10);
        } else {
            return;
        }
    }
}
