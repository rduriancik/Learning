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
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * Created by robert on 26.2.2016.
 */
public class RecursiveTree extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a main BorderPane
        BorderPane borderPane = new BorderPane();

        // Create a HilbertPane and place it in the center of the borderPane
        RecursiveTreePane recursiveTreePane = new RecursiveTreePane();
        borderPane.setCenter(recursiveTreePane);

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
                e -> recursiveTreePane.setOrder(Integer.parseInt(tfOrder.getText())));

        // Create a mouse event for the recursiveTreePane
        recursiveTreePane.setOnMouseDragged(e -> recursiveTreePane.moveTree(e.getX(), e.getY()));

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("HilbertCurve");
        primaryStage.show();
    }

    /**
     * RecursiveTreePane class extends Pane class and represents
     * a pane with the possibility paint the recursive tree based on the order
     */
    static class RecursiveTreePane extends Pane {

        public RecursiveTreePane() {
            this.setPrefHeight(400);
            this.setPrefWidth(400);
        }

        /**
         * Sets an order of the recursive tree
         */
        public void setOrder(int order) {
            this.getChildren().clear();

            Line line = new Line();
            line.setStartX(getWidth() / 2);
            line.setStartY(this.getHeight() / 10);
            line.setEndX(getWidth() / 2);
            line.setEndY(this.getHeight() - 5);

            paint(line, 90, order);
        }

        /**
         * Recursive method which paint the recursive tree based on the order
         */
        private void paint(Line line, double angle, int order) {
            Point2D lineEnd = new Point2D(line.getEndX(), line.getEndY());
            Point2D lineStart = new Point2D(line.getStartX(), line.getStartY());

            if (order == 0) {
                drawLine(lineEnd, angle, lineStart.distance(lineEnd) / 2.0);
            } else {
                Line l = drawLine(lineEnd, angle, lineStart.distance(lineEnd) / 2.0);

                paint(l, angle + 45, order - 1);
                paint(l, angle - 45, order - 1);
            }
        }

        /**
         * Draws a line
         */
        private Line drawLine(Point2D point, double angle, double length) {
            double endX = point.getX() + length * Math.cos(((2 * Math.PI) / 360) * angle);
            double endY = point.getY() - length * Math.sin(((2 * Math.PI) / 360) * angle);

            Line line = new Line(point.getX(), point.getY(), endX, endY);

            this.getChildren().add(line);

            return line;
        }

        /**
         * Moves the recursive tree
         */
        public void moveTree(double x, double y) {
            for (int i = 0; i < this.getChildren().size(); i++) {
                Line line = (Line) this.getChildren().get(i);
                line.setLayoutX(x - this.getWidth() / 2.0);
                line.setLayoutY(y - this.getHeight() / 2.0);
            }
        }


    }
}
