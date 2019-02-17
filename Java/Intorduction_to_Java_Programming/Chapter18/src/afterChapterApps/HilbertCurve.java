package afterChapterApps;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

/**
 * Created by rduriancik on 2/18/16.
 */
public class HilbertCurve extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a main BorderPane
        BorderPane borderPane = new BorderPane();

        // Create a HilbertPane and place it in the center of the borderPane
        HilbertPane hilbertPane = new HilbertPane();
        borderPane.setCenter(hilbertPane);

        // Create a HBox with a text field for an order input
        TextField tfOrder = new TextField();
        tfOrder.setPrefColumnCount(2);
        tfOrder.setAlignment(Pos.CENTER_LEFT);
        HBox hBox = new HBox(5, new Label("Enter an order: "), tfOrder);
        hBox.setAlignment(Pos.CENTER);
        // Place it in the bottom of the borderPane
        borderPane.setBottom(hBox);
        BorderPane.setMargin(hBox, new Insets(5));

        // Create an action event for the tfOrder
        tfOrder.setOnAction(
                e -> hilbertPane.setOrder(Integer.parseInt(tfOrder.getText())));


        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("HilbertCurve");
        primaryStage.show();
    }

    /**
     * This class represents a pane with the Hilbert curve
     */
    static class HilbertPane extends Pane {

        private Polyline line;
        private double stepSize;

        public HilbertPane() {
            line = new Polyline();
            this.getChildren().add(line);
            this.setPrefWidth(200);
            this.setPrefHeight(200);
        }

        /**
         * Sets an order of the Hilbert curve, initializes a start point and paints the Hilbert Curve
         */
        public void setOrder(int order) {
            line.getPoints().clear();
            line.getPoints().add(getWidth() / (4 * order));
            line.getPoints().add(getHeight() / (4 * order));

            stepSize = (this.getWidth() / 2.0) / Math.pow(2, order - 1);
            paint(1, 1, order);
        }

        /**
         * Recursive method which paints the Hilbert curve
         */
        private void paint(int direction, int rotation, int order) {
            if (order != 0) {
                direction += rotation;

                paint(direction, -rotation, order - 1);
                step(direction);

                direction -= rotation;

                paint(direction, rotation, order - 1);
                step(direction);
                paint(direction, rotation, order - 1);

                direction -= rotation;

                step(direction);
                paint(direction, -rotation, order - 1);
            }
        }

        /**
         * Makes one step in the pane based on direction
         */
        private void step(int direction) {
            Point2D lastPoint = new Point2D(line.getPoints().get(line.getPoints().size() - 2),
                    line.getPoints().get(line.getPoints().size() - 1));

            if (direction < 0) {
                do {
                    direction = 4 + direction;
                } while (direction < 0);
            }

            if (direction > 3) {
                direction %= 4;
            }

            switch (direction) {
                case 0:
                    line.getPoints().add(lastPoint.getX());
                    line.getPoints().add(lastPoint.getY() - stepSize);
                    break;
                case 1:
                    line.getPoints().add(lastPoint.getX() + stepSize);
                    line.getPoints().add(lastPoint.getY());
                    break;
                case 2:
                    line.getPoints().add(lastPoint.getX());
                    line.getPoints().add(lastPoint.getY() + stepSize);
                    break;
                case 3:
                    line.getPoints().add(lastPoint.getX() - stepSize);
                    line.getPoints().add(lastPoint.getY());
            }
        }

    }
}
