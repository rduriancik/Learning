package afterChapterApps;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 30.01.16.
 */
public class KochSnowflake extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a main BorderPane
        BorderPane pane = new BorderPane();

        KochSnowflakePane kochSnowflake = new KochSnowflakePane();

        pane.setCenter(kochSnowflake); // Add kochSnowflake in the main borderPane

        // Pane to hold label, and a textField
        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        TextField tfOrder = new TextField();
        tfOrder.setPrefColumnCount(2);

        hBox.getChildren().addAll(new Label("Enter an order: "), tfOrder);

        // Add hBox in the main borderPane
        pane.setBottom(hBox);

        tfOrder.setOnAction(e -> {
            kochSnowflake.setOrder(Integer.parseInt(tfOrder.getText()));
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 200, 230);
        primaryStage.setScene(scene);
        primaryStage.setTitle("KochSnowflake");
        primaryStage.show();
    }

    static class KochSnowflakePane extends Pane {
        private int order;

        public KochSnowflakePane() {
        }

        public void setOrder(int order) {
            this.order = order;
            paint();
        }

        protected void paint() {
            // Select three points in proportion to the pane size
            Point2D p1 = new Point2D(getWidth() / 2, 10);
            Point2D p2 = new Point2D(getWidth() - 10, getHeight() - (getHeight() / 3));
            Point2D p3 = new Point2D(10, getHeight() - (getHeight() / 3));

            this.getChildren().clear(); // Clear the pane before redisplay
            System.out.println();
            displaySnowflake(order, p1, p2, p3);
        }

        private void displaySnowflake(int order, Point2D... p) {
            if (order == 0) {
                Polygon flake = new Polygon();
                for (Point2D point : p) {
                    flake.getPoints().addAll(point.getX(), point.getY());
                }
                flake.setStroke(Color.BLACK);
                flake.setFill(Color.WHITE);

                getChildren().add(flake);
            } else {
                List<Point2D> points = new ArrayList<>();

                for (int i = 0; i < p.length; i++) {
                    Point2D p1 = new Point2D(p[i % p.length].getX() + ((p[(i + 1) % p.length].getX() - p[i % p.length].getX()) / 3.0),
                            p[i % p.length].getY() + ((p[(i + 1) % p.length].getY() - p[i % p.length].getY()) / 3.0));

                    Point2D p3 = new Point2D(p[i % p.length].getX() + (((p[(i + 1) % p.length].getX() - p[i % p.length].getX()) * 2) / 3.0),
                            p[i % p.length].getY() + (((p[(i + 1) % p.length].getY() - p[i % p.length].getY()) * 2) / 3.0));

                    Point2D p2 = getPointP2(p1.midpoint(p3), p1);

                    if (!points.contains(p[i % p.length])) {
                        points.add(p[i % p.length]);
                    }
                    points.add(p1);
                    points.add(p2);
                    points.add(p3);
                    if (!points.contains(p[(i + 1) % p.length])) {
                        points.add(p[(i + 1) % p.length]);
                    }

                }

                displaySnowflake(order - 1, points.toArray(new Point2D[0]));
            }
        }

        private Point2D getPointP2(Point2D centerPoint, Point2D pointForRotation) {
            double x = 2 * (centerPoint.getY() - pointForRotation.getY()) + centerPoint.getX();
            double y = 2 * (pointForRotation.getX() - centerPoint.getX()) + centerPoint.getY();
            return new Point2D(x, y);
        }
    }

}
