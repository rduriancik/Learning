/**
 * Created by robkio on 2.9.2015.
 */
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Arc;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TestDisplayRunnigFan extends Application{
    @Override
    public void start(Stage stage){
        Pane pane = new Pane();

        Circle circle = new Circle(105, 105, 100);
        circle.setStyle("-fx-fill: null; -fx-stroke: BLACK");
        pane.getChildren().add(circle);

        Arc arc = new Arc(105, 105, 90, 90, 25, 40);
        arc.setType(ArcType.ROUND);
        arc.setStyle("-fx-fill: RED");
        Arc arc1 = new Arc(105, 105, 90, 90, 115, 40);
        arc1.setType(ArcType.ROUND);
        arc1.setStyle("-fx-fill: RED");
        Arc arc2 = new Arc(105, 105, 90, 90, 205, 40);
        arc2.setType(ArcType.ROUND);
        arc2.setStyle("-fx-fill: RED");
        Arc arc3 = new Arc(105, 105, 90, 90, 295, 40);
        arc3.setType(ArcType.ROUND);
        arc3.setStyle("-fx-fill: RED");
        pane.getChildren().addAll(arc, arc1, arc2, arc3);

        EventHandler handler = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                arc.setStartAngle(arc.getStartAngle() + 1);
                arc1.setStartAngle(arc1.getStartAngle() + 1);
                arc2.setStartAngle(arc2.getStartAngle() + 1);
                arc3.setStartAngle(arc3.getStartAngle() + 1);
            }
        };

        EventHandler<ActionEvent> handler1 = e ->{
            arc.setStartAngle(arc.getStartAngle() - 1);
            arc1.setStartAngle(arc1.getStartAngle() - 1);
            arc2.setStartAngle(arc2.getStartAngle() - 1);
            arc3.setStartAngle(arc3.getStartAngle() - 1);
        };

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(10), handler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        Timeline animation2 = new Timeline(new KeyFrame(Duration.millis(10), handler1));
        animation2.setCycleCount(Timeline.INDEFINITE);

        HBox hBox = new HBox(10);
        Button btReverse = new Button("Reverse");
        Button btResume = new Button("Resume");
        Button btPause = new Button("Pause");
        hBox.getChildren().addAll(btPause, btResume, btReverse);

        btPause.setOnAction(e -> {
            if(animation.getStatus() == Animation.Status.RUNNING)
                animation.pause();
            else if(animation2.getStatus() == Animation.Status.RUNNING)
                    animation2.pause();
        });
        btResume.setOnAction(e -> {
            if(animation.getStatus() == Animation.Status.PAUSED)
                animation.play();
            else if(animation2.getStatus() == Animation.Status.PAUSED)
                animation2.play();
        });
        btReverse.setOnAction(e -> {
            if(animation.getStatus() == Animation.Status.RUNNING) {
                animation.stop();
                animation2.play();
            }
            else{
                animation2.stop();
                animation.play();
            }
        });


        BorderPane borderPane = new BorderPane();
        BorderPane.setMargin(hBox, new Insets(10));
        BorderPane.setMargin(pane, new Insets(10, 0, 0, 15));
        borderPane.setCenter(pane);
        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane);
        stage.setTitle("Running fan");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){ launch(args);}
}
