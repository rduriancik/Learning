import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.collections.ObservableList;
/**
 * Created by robkio on 10.9.2015.
 */
public class TestSelfAvoidingRandomWalk extends Application{
    private final int stepSize = 20;
    private Polyline line = new Polyline(160, 160);
    private ObservableList<Double> points = line.getPoints();
    @Override
    public void start(Stage stage){
        Pane pane = new Pane();
        Line[] xLines = new Line[17];
        for(int i = 0; i < xLines.length; i++){
            xLines[i] = new Line(0, stepSize*i, 20*16, stepSize*i);
            xLines[i].setOpacity(0.3);
            pane.getChildren().add(xLines[i]);
        }
        Line[] yLines = new Line[17];
        for(int i = 0; i < yLines.length; i++){
            yLines[i] = new Line(stepSize*i, 0, stepSize*i, 20*16);
            yLines[i].setOpacity(0.3);
            pane.getChildren().add(yLines[i]);
        }

        ObservableList<Double> points = line.getPoints();
        pane.getChildren().add(line);

        Button btStart = new Button("Start");

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(btStart);
        BorderPane.setAlignment(btStart, Pos.CENTER);
        BorderPane.setMargin(btStart, new Insets(10, 0, 0, 0));

        EventHandler<ActionEvent> handler = e -> {
            if(points.get(points.size() - 1) > 0 && points.get(points.size() - 1) < 20*16 &&
                    points.get(points.size() - 2) > 0 && points.get(points.size() - 2) < 20*16){
                moveLine();
            }
        };

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(40), handler));
        animation.setCycleCount(Timeline.INDEFINITE);

        btStart.setOnAction(e -> animation.play());

        Scene scene = new Scene(borderPane);
        stage.setTitle("SelfAvoidingRandomWalk");
        stage.setScene(scene);
        stage.show();
    }

    public void moveLine(){
            double[] newPoints = new double[2];
            int random = (int) (Math.random() * 4);

            switch (random) {
                case 0:
                    newPoints[0] = points.get(points.size() - 2);
                    newPoints[1] = points.get(points.size() - 1) - stepSize;
                    break;
                case 1:
                    newPoints[0] = points.get(points.size() - 2) + stepSize;
                    newPoints[1] = points.get(points.size() - 1);
                    break;
                case 2:
                    newPoints[0] = points.get(points.size() - 2);
                    newPoints[1] = points.get(points.size() - 1) + stepSize;
                    break;
                case 3:
                    newPoints[0] = points.get(points.size() - 2) - stepSize;
                    newPoints[1] = points.get(points.size() - 1);
                    break;
            }

            int indexX = 0;
            int indexY = 1;
            for (int i = 0; i < (points.size() - 2) / 2; i++) {
                if (newPoints[1] == points.get(indexY) && newPoints[0] == points.get(indexX)){
                    return;
                }
                else {
                    indexX += 2;
                    indexY += 2;
                }
            }

            if (points.size() >= 4 && newPoints[0] == points.get(points.size() - 4) && newPoints[1] == points.get(points.size() - 3))
                moveLine();
            else
                points.addAll(newPoints[0], newPoints[1]);

    }

    public static void main(String[] args){
        launch(args);
    }
}
