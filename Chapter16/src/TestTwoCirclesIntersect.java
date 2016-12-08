import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.geometry.Insets;

/**
 * Created by robkio on 4.10.2015.
 */
public class TestTwoCirclesIntersect extends Application {
    @Override
    public void start(Stage primaryStage){
        Pane paneForCircles = new Pane();
        Text textIntersect = new Text(60, 12, "Two circles intersects? No");
        Circle circle1 = new Circle(52, 60, 30);
        circle1.setFill(Color.WHITE);
        circle1.setOpacity(0.5);
        circle1.setStroke(Color.BLACK);
        Circle circle2 = new Circle(180, 56, 40);
        circle2.setFill(Color.WHITE);
        circle2.setOpacity(0.5);
        circle2.setStroke(Color.BLACK);
        paneForCircles.getChildren().addAll(textIntersect, circle1, circle2);

        BorderPane borderPaneForCircle1 = new BorderPane();
        borderPaneForCircle1.setStyle("-fx-border-color: BLACK");
        borderPaneForCircle1.setTop(new Label("Enter circle 1 info:"));

        GridPane paneForCircleInfo1 = new GridPane();

        TextField tfCircle1CenterX = new TextField();
        tfCircle1CenterX.setPrefColumnCount(5);
        tfCircle1CenterX.setAlignment(Pos.CENTER_RIGHT);
        tfCircle1CenterX.setText(circle1.getCenterX() + "");
        paneForCircleInfo1.add(new Label("Center x: "), 0, 0);
        paneForCircleInfo1.add(tfCircle1CenterX, 1, 0);

        TextField tfCircle1CenterY = new TextField();
        tfCircle1CenterY.setPrefColumnCount(5);
        tfCircle1CenterY.setAlignment(Pos.CENTER_RIGHT);
        tfCircle1CenterY.setText(circle1.getCenterY() + "");
        paneForCircleInfo1.add(new Label("Center y: "), 0, 1);
        paneForCircleInfo1.add(tfCircle1CenterY, 1, 1);

        TextField tfCircle1Radius = new TextField();
        tfCircle1Radius.setPrefColumnCount(5);
        tfCircle1Radius.setAlignment(Pos.CENTER_RIGHT);
        tfCircle1Radius.setText(circle1.getRadius() + "");
        paneForCircleInfo1.add(new Label("Radius: "), 0, 2);
        paneForCircleInfo1.add(tfCircle1Radius, 1, 2);

        borderPaneForCircle1.setCenter(paneForCircleInfo1);

        BorderPane borderPaneForCircle2 = new BorderPane();
        borderPaneForCircle2.setStyle("-fx-border-color: BLACK");
        borderPaneForCircle2.setTop(new Label("Enter circle 2 info:"));

        GridPane paneForCircleInfo2 = new GridPane();

        TextField tfCircle2CenterX = new TextField();
        tfCircle2CenterX.setPrefColumnCount(5);
        tfCircle2CenterX.setAlignment(Pos.CENTER_RIGHT);
        tfCircle2CenterX.setText(circle2.getCenterX() + "");
        paneForCircleInfo2.add(new Label("Center x: "), 0, 0);
        paneForCircleInfo2.add(tfCircle2CenterX, 1, 0);

        TextField tfCircle2CenterY = new TextField();
        tfCircle2CenterY.setPrefColumnCount(5);
        tfCircle2CenterY.setAlignment(Pos.CENTER_RIGHT);
        tfCircle2CenterY.setText(circle2.getCenterY() + "");
        paneForCircleInfo2.add(new Label("Center y: "), 0, 1);
        paneForCircleInfo2.add(tfCircle2CenterY, 1, 1);

        TextField tfCircle2Radius = new TextField();
        tfCircle2Radius.setPrefColumnCount(5);
        tfCircle2Radius.setAlignment(Pos.CENTER_RIGHT);
        tfCircle2Radius.setText(circle2.getRadius() + "");
        paneForCircleInfo2.add(new Label("Radius: "), 0, 2);
        paneForCircleInfo2.add(tfCircle2Radius, 1, 2);

        borderPaneForCircle2.setCenter(paneForCircleInfo2);

        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(5));
        hBox.getChildren().addAll(borderPaneForCircle1, borderPaneForCircle2);

        Button btRedrawCircles = new Button("Redraw Circles");

        BorderPane pane = new BorderPane();
        pane.setTop(paneForCircles);
        pane.setCenter(hBox);
        pane.setBottom(btRedrawCircles);
        BorderPane.setAlignment(btRedrawCircles, Pos.CENTER);

        btRedrawCircles.setOnAction(e -> {
                circle1.setCenterX(Double.valueOf(tfCircle1CenterX.getText()));
                circle1.setCenterY(Double.valueOf(tfCircle1CenterY.getText()));
                circle1.setRadius(Double.valueOf(tfCircle1Radius.getText()));

                circle2.setCenterX(Double.valueOf(tfCircle2CenterX.getText()));
                circle2.setCenterY(Double.valueOf(tfCircle2CenterY.getText()));
                circle2.setRadius(Double.valueOf(tfCircle2Radius.getText()));

                if (intersect(circle1, circle2)) {
                    textIntersect.setText("Two circle intersect? Yes");
                } else {
                    textIntersect.setText("Two circle intersect? No");
                }
        });

        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestTwoCirclesIntersect");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}

    private static boolean intersect(Circle c1, Circle c2){
        for(double i = 0; i <= 2; i += 0.1){
            double x = c1.getCenterX() + c1.getRadius() * Math.cos(Math.PI * i);
            double y = c1.getCenterY() + c1.getRadius() * Math.sin(Math.PI * i);
            if(c2.contains(x, y)){
                return true;
            }
        }

        return false;
    }
}
