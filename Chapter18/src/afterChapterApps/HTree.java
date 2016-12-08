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
 * Created by rduriancik on 2/18/16.
 */
public class HTree extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a main BorderPane
        BorderPane borderPane = new BorderPane();

        // Create a HTreePane and place it in the center of the borderPane
        HTreePane hTreePane = new HTreePane();
        borderPane.setCenter(hTreePane);

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
                e -> hTreePane.setOrder(Integer.parseInt(tfOrder.getText())));

        // Create a scene and place it in the Stage
        Scene scene = new Scene(borderPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("H-Tree");
        primaryStage.show();
    }

    static class HTreePane extends Pane {

        public HTreePane() {
        }

        /**
         * Sets the order of a H-Tree and paint it
         */
        public void setOrder(int order) {
            getChildren().clear();
            paint(new Point2D(this.getWidth() / 3.0, this.getHeight() / 2.0),
                    new Point2D(this.getWidth() * (2 / 3.0), this.getHeight() / 2.0), order);
        }

        /**
         * Recursive method which paints H-Trees based on the order
         */
        public void paint(Point2D p1, Point2D p2, int order) {
            if (order == 0) {
                drawH(p1, p2);
            } else {
                drawH(p1, p2);

                paint(new Point2D(p1.getX() - (p1.distance(p2) / 4), p1.getY() - (p1.distance(p2) / 2)),
                        new Point2D(p1.getX() + (p1.distance(p2) / 4), p1.getY() - (p1.distance(p2) / 2)), order - 1);

                paint(new Point2D(p1.getX() - (p1.distance(p2) / 4), p1.getY() + (p1.distance(p2) / 2)),
                        new Point2D(p1.getX() + (p1.distance(p2) / 4), p1.getY() + (p1.distance(p2) / 2)), order - 1);

                paint(new Point2D(p2.getX() - (p1.distance(p2) / 4), p2.getY() - (p1.distance(p2) / 2)),
                        new Point2D(p2.getX() + (p1.distance(p2) / 4), p2.getY() - (p1.distance(p2) / 2)), order - 1);

                paint(new Point2D(p2.getX() - (p1.distance(p2) / 4), p2.getY() + (p1.distance(p2) / 2)),
                        new Point2D(p2.getX() + (p1.distance(p2) / 4), p2.getY() + (p1.distance(p2) / 2)), order - 1);
            }
        }

        /**
         * Draws H in the pane
         */
        private void drawH(Point2D p1, Point2D p2) {
            Line line1 = new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY());

            Line line2 = new Line(p1.getX(), p1.getY() - (p1.distance(p2) / 2.0),
                    p1.getX(), p1.getY() + (p1.distance(p2) / 2.0));

            Line line3 = new Line(p2.getX(), p2.getY() - (p1.distance(p2) / 2.0),
                    p2.getX(), p2.getY() + (p1.distance(p2) / 2.0));

            getChildren().addAll(line1, line2, line3);
        }
    }

}

