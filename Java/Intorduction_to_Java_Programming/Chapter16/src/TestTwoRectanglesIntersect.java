import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

/**
 * Created by robkio on 13.10.2015.
 */
public class TestTwoRectanglesIntersect extends Application {
    @Override
    public void start(Stage primaryStage){
        Pane paneForRectangles = new Pane();
        paneForRectangles.setPadding(new Insets(10));
        Text textIntersect = new Text(60, 12, "Two rectangles intersect? Yes");
        Rectangle rect1 = new Rectangle(79, 20, 40, 50);
        rect1.setFill(Color.WHITE);
        rect1.setStroke(Color.BLACK);
        rect1.setOpacity(0.6);
        Rectangle rect2 = new Rectangle(110, 33, 50, 20);
        rect2.setFill(Color.WHITE);
        rect2.setStroke(Color.BLACK);
        rect2.setOpacity(0.6);
        paneForRectangles.getChildren().addAll(textIntersect, rect1, rect2);

        BorderPane paneForRect1Info = new BorderPane();
        paneForRect1Info.setStyle("-fx-border-color: black");
        TextField tfRect1X = new TextField(rect1.getX() + "");
        tfRect1X.setPrefColumnCount(5);
        tfRect1X.setAlignment(Pos.CENTER_RIGHT);
        TextField tfRect1Y = new TextField(rect1.getY() + "");
        tfRect1Y.setPrefColumnCount(5);
        tfRect1Y.setAlignment(Pos.CENTER_RIGHT);
        TextField tfRect1Width = new TextField(rect1.getWidth() + "");
        tfRect1Width.setPrefColumnCount(5);
        tfRect1Width.setAlignment(Pos.CENTER_RIGHT);
        TextField tfRect1Height = new TextField(rect1.getHeight() + "");
        tfRect1Height.setPrefColumnCount(5);
        tfRect1Height.setAlignment(Pos.CENTER_RIGHT);
        GridPane gridPaneRect1 = new GridPane();
        gridPaneRect1.add(new Label("X:"), 0, 0);
        gridPaneRect1.add(tfRect1X, 1, 0);
        gridPaneRect1.add(new Label("Y:"), 0, 1);
        gridPaneRect1.add(tfRect1Y, 1, 1);
        gridPaneRect1.add(new Label("Width:"), 0, 2);
        gridPaneRect1.add(tfRect1Width, 1, 2);
        gridPaneRect1.add(new Label("Height:"), 0, 3);
        gridPaneRect1.add(tfRect1Height, 1, 3);
        paneForRect1Info.setTop(new Label("Enter Rectangle 1 info:"));
        paneForRect1Info.setCenter(gridPaneRect1);

        BorderPane paneForRect2Info = new BorderPane();
        paneForRect2Info.setStyle("-fx-border-color: black");
        TextField tfRect2X = new TextField(rect2.getX() + "");
        tfRect2X.setPrefColumnCount(5);
        tfRect2X.setAlignment(Pos.CENTER_RIGHT);
        TextField tfRect2Y = new TextField(rect2.getY() + "");
        tfRect2Y.setPrefColumnCount(5);
        tfRect2Y.setAlignment(Pos.CENTER_RIGHT);
        TextField tfRect2Width = new TextField(rect2.getWidth() + "");
        tfRect2Width.setPrefColumnCount(5);
        tfRect2Width.setAlignment(Pos.CENTER_RIGHT);
        TextField tfRect2Height = new TextField(rect2.getHeight() + "");
        tfRect2Height.setPrefColumnCount(5);
        tfRect2Height.setAlignment(Pos.CENTER_RIGHT);
        GridPane gridPaneRect2 = new GridPane();
        gridPaneRect2.add(new Label("X:"), 0, 0);
        gridPaneRect2.add(tfRect2X, 1, 0);
        gridPaneRect2.add(new Label("Y:"), 0, 1);
        gridPaneRect2.add(tfRect2Y, 1, 1);
        gridPaneRect2.add(new Label("Width:"), 0, 2);
        gridPaneRect2.add(tfRect2Width, 1, 2);
        gridPaneRect2.add(new Label("Height:"), 0, 3);
        gridPaneRect2.add(tfRect2Height, 1, 3);
        paneForRect2Info.setTop(new Label("Enter Rectangle 2 info:"));
        paneForRect2Info.setCenter(gridPaneRect2);

        HBox hBoxForInfos = new HBox(5);
        hBoxForInfos.setPadding(new Insets(0, 5, 0, 5));
        hBoxForInfos.getChildren().addAll(paneForRect1Info, paneForRect2Info);

        Button btRedrawCircles = new Button("Redraw Circles");
        btRedrawCircles.setOnAction(e -> {
            rect1.setX(Double.valueOf(tfRect1X.getText()));
            rect1.setY(Double.valueOf(tfRect1Y.getText()));
            rect1.setWidth(Double.valueOf(tfRect1Width.getText()));
            rect1.setHeight(Double.valueOf(tfRect1Height.getText()));

            rect2.setX(Double.valueOf(tfRect2X.getText()));
            rect2.setY(Double.valueOf(tfRect2Y.getText()));
            rect2.setWidth(Double.valueOf(tfRect2Width.getText()));
            rect2.setHeight(Double.valueOf(tfRect2Height.getText()));

            if(intersect(rect1, rect2)){
                textIntersect.setText("Two rectangles intersect? Yes");
            } else {
                textIntersect.setText("Two rectangles intersect? No");
            }
        });

        BorderPane pane = new BorderPane();
        pane.setTop(paneForRectangles);
        pane.setCenter(hBoxForInfos);
        pane.setBottom(btRedrawCircles);
        BorderPane.setAlignment(btRedrawCircles, Pos.CENTER);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Two Rectangles Intersect");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public boolean intersect(Rectangle r1, Rectangle r2) {
        if (r1.getX() < r2.getX()) {
            if (r1.getY() < r2.getY()) {
                if (r1.getX() + r1.getWidth() > r2.getX()
                        && r1.getY() + r1.getHeight() > r2.getY()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (r1.getX() + r1.getWidth() > r2.getX()
                        && r2.getY() + r2.getHeight() > r1.getY()) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            if (r1.getY() < r2.getY()) {
                if (r2.getX() + r2.getWidth() > r1.getX()
                        && r1.getY() + r1.getHeight() > r2.getY()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (r2.getX() + r2.getWidth() > r1.getX()
                        && r2.getY() + r2.getHeight() > r1.getY()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public static void main(String[] args){launch(args);}
}
