/**
 * Created by robkio on 30.8.2015.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.shape.PathElement;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;

public class TestDragPoints extends Application{
    private Circle circle;
    private final double circleRadius = 150;
    private final double circleX = 180;
    private final double circleY = 180;
    private Circle point1;
    private Circle point2;
    private Circle point3;
    private final double pointRadius = 10;
    private Text txtPoint1;
    private Text txtPoint2;
    private Text txtPoint3;

    @Override
    public void start(Stage stage){
        Pane pane = new Pane();

        circle = new Circle(circleX, circleY, circleRadius);
        circle.setStyle("-fx-fill: null; -fx-stroke: BLACK");
        pane.getChildren().add(circle);

        point1 = new Circle(circleX + circleRadius * Math.cos(Math.PI), circleY + circleRadius * Math.sin(Math.PI),
                pointRadius);
        point1.setFill(Color.BLUE);
        txtPoint1 = new Text(point1.getCenterX(), point1.getCenterY() - pointRadius, "");
        point2 = new Circle(circleX + circleRadius * Math.cos(Math.PI / 2.0), circleY + circleRadius *
                Math.sin(Math.PI / 2.0), pointRadius);
        point2.setFill(Color.RED);
        txtPoint2 = new Text(point2.getCenterX(), point2.getCenterY() - pointRadius, "");
        point3 = new Circle(circleX + circleRadius * Math.cos(2 * Math.PI), circleY + circleRadius *
                Math.sin(2 * Math.PI), pointRadius);
        point3.setFill(Color.YELLOW);
        txtPoint3 = new Text(point3.getCenterX(), point3.getCenterY() - pointRadius, "");
        computeAngles();

        Line line1 = new Line();
        line1.startXProperty().bind(point1.centerXProperty());
        line1.startYProperty().bind(point1.centerYProperty());
        line1.endXProperty().bind(point2.centerXProperty());
        line1.endYProperty().bind(point2.centerYProperty());
        Line line2 = new Line();
        line2.startXProperty().bind(point2.centerXProperty());
        line2.startYProperty().bind(point2.centerYProperty());
        line2.endXProperty().bind(point3.centerXProperty());
        line2.endYProperty().bind(point3.centerYProperty());
        Line line3 = new Line();
        line3.startXProperty().bind(point3.centerXProperty());
        line3.startYProperty().bind(point3.centerYProperty());
        line3.endXProperty().bind(point1.centerXProperty());
        line3.endYProperty().bind(point1.centerYProperty());
        pane.getChildren().addAll(line1, line2, line3, point1, point2, point3, txtPoint1, txtPoint2, txtPoint3);

        point1.setOnMouseDragged(e -> {
            movePoint(e, point1);
            txtPoint1.setX(point1.getCenterX());
            txtPoint1.setY(point1.getCenterY() - pointRadius);
            computeAngles();
        });



        Scene scene = new Scene(pane, 360, 360);
        stage.setTitle("Drag points");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

    public void computeAngles(){
        double a = Math.sqrt((point2.getCenterX() - point3.getCenterX()) * (point2.getCenterX() - point3.getCenterX())
        + (point2.getCenterY() - point3.getCenterY()) * (point2.getCenterY() - point3.getCenterY()));
        double b = Math.sqrt((point1.getCenterX() - point3.getCenterX()) * (point1.getCenterX() - point3.getCenterX())
                + (point1.getCenterY() - point3.getCenterY()) * (point1.getCenterY() - point3.getCenterY()));
        double c = Math.sqrt((point1.getCenterX() - point2.getCenterX()) * (point1.getCenterX() - point2.getCenterX())
                + (point1.getCenterY() - point2.getCenterY()) * (point1.getCenterY() - point2.getCenterY()));

        double A = Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
        double B = Math.toDegrees(Math.acos((b * b - a * a - c * c) / (-2 * a * c)));
        double C = Math.toDegrees(Math.acos((c * c - b * b - a * a) / (-2 * a * b)));

        txtPoint1.setText(Double.toString((int)(A * 100) / 100.0));
        txtPoint2.setText(Double.toString((int)(B * 100) / 100.0));
        txtPoint3.setText(Double.toString((int) (C * 100) / 100.0));
    }

    public void movePoint(javafx.scene.input.MouseEvent e, Circle point){
        double[] u = {(circleX - circleRadius) - circleX, 0};
        double[] v = {e.getX() - circleX, e.getY() - circleY};
        double cosAngle = (u[0] * v[0] + u[1] * v[1]) / (Math.sqrt(u[0] * u[0] + u[1] * u[1]) * Math.sqrt(v[0] * v[0] +
        v[1] * v[1]));

        point.setCenterX(circleX + circleRadius * Math.cos(Math.PI * cosAngle));
        point.setCenterY(circleY + circleRadius * Math.sin(Math.PI * cosAngle));
    }
}
