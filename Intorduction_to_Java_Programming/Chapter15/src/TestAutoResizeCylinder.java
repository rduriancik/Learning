/**
 * Created by robkio on 30.8.2015.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

public class TestAutoResizeCylinder extends Application {
    @Override
    public void start(Stage stage){
        Pane pane = new Pane();

        Ellipse ellipse = new Ellipse(150, 70, 140, 60);
        ellipse.setStyle("-fx-fill: null; -fx-stroke: BLACK");
        pane.getChildren().add(ellipse);
        ellipse.centerXProperty().bind(pane.widthProperty().divide(2));
        ellipse.centerYProperty().bind(pane.heightProperty().divide(400/70.0));
        ellipse.radiusXProperty().bind(pane.widthProperty().divide(2).subtract(10));
        ellipse.radiusYProperty().bind(pane.heightProperty().divide(400/70.0).subtract(10));

        Arc frontArc = new Arc(150, 330, 140, 60, 180, 180);
        frontArc.setType(ArcType.OPEN);
        frontArc.setStyle("-fx-fill: null; -fx-stroke: BLACK");
        pane.getChildren().add(frontArc);
        frontArc.centerXProperty().bind(ellipse.centerXProperty());
        frontArc.centerYProperty().bind(pane.heightProperty().divide(400/330.0).subtract(10));
        frontArc.radiusXProperty().bind(ellipse.radiusXProperty());
        frontArc.radiusYProperty().bind(ellipse.radiusYProperty());

        Arc backArc = new Arc(150, 330, 140, 60, 0, 180);
        backArc.setType(ArcType.OPEN);
        backArc.setStyle("-fx-fill: null; -fx-stroke: BLACK");
        backArc.getStrokeDashArray().addAll(10.0, 15.0);
        pane.getChildren().add(backArc);
        backArc.centerXProperty().bind(frontArc.centerXProperty());
        backArc.centerYProperty().bind(frontArc.centerYProperty());
        backArc.radiusXProperty().bind(frontArc.radiusXProperty());
        backArc.radiusYProperty().bind(frontArc.radiusYProperty());

        Line side1 = new Line();
        side1.startXProperty().bind(ellipse.centerXProperty().add(ellipse.radiusXProperty()));
        side1.startYProperty().bind(ellipse.centerYProperty());
        side1.endXProperty().bind(frontArc.centerXProperty().add(frontArc.radiusXProperty()));
        side1.endYProperty().bind(frontArc.centerYProperty());
        pane.getChildren().add(side1);

        Line side2 = new Line();
        side2.startXProperty().bind(ellipse.centerXProperty().subtract(ellipse.radiusXProperty()));
        side2.startYProperty().bind(ellipse.centerYProperty());
        side2.endXProperty().bind(frontArc.centerXProperty().subtract(frontArc.radiusXProperty()));
        side2.endYProperty().bind(frontArc.centerYProperty());
        pane.getChildren().add(side2);


        Scene scene = new Scene(pane, 300, 400);
        stage.setTitle("Auto Resize Cylinder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
