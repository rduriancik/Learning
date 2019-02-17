import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Slider;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Arc;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * Created by robkio on 30.10.2015.
 */
public class FanPane extends BorderPane {

    private Timeline animation;
    private double speed;
    private Arc arc1;
    private Arc arc2;
    private Arc arc3;
    private Arc arc4;

    public FanPane(){
        // Top HBox for buttons
        HBox hBoxForButtons = new HBox(5);
        hBoxForButtons.setAlignment(Pos.CENTER);
        Button btPause = new Button("Pause");
        Button btResume = new Button("Resume");
        Button btReverse = new Button("Reverse");
        hBoxForButtons.getChildren().addAll(btPause, btResume, btReverse);

        setTop(hBoxForButtons);

        // Bottom slider
        Slider slSpeed = new Slider(0, 3, 0.1);

        setBottom(slSpeed);

        // Center pane for the fan
        Pane pane = new Pane();
        Circle circle = new Circle(75, 75, 75);
        circle.setStyle("-fx-fill: null; -fx-stroke: black;");
        pane.getChildren().add(circle);

        arc1 = new Arc(75, 75, 65, 65, 20, 50);
        arc1.setFill(Color.RED);
        arc1.setType(ArcType.ROUND);
        arc2 = new Arc(75, 75, 65, 65, 110, 50);
        arc2.setFill(Color.RED);
        arc2.setType(ArcType.ROUND);
        arc3 = new Arc(75, 75, 65, 65, 200, 50);
        arc3.setFill(Color.RED);
        arc3.setType(ArcType.ROUND);
        arc4 = new Arc(75, 75, 65, 65, 290, 50);
        arc4.setFill(Color.RED);
        arc4.setType(ArcType.ROUND);
        pane.getChildren().addAll(arc1, arc2, arc3, arc4);

        setCenter(pane);
        BorderPane.setMargin(pane, new Insets(15, 0, 10, 30));

        // Set animation properties
        speed = slSpeed.getValue();
        animation = new Timeline(new KeyFrame(Duration.millis(10), handler));
        runFan();

        // Set button on action
        btPause.setOnAction(e -> animation.pause());
        btResume.setOnAction(e -> animation.play());
        btReverse.setOnAction(e -> speed *= -1);

        // Add listener to slSpeed
        slSpeed.valueProperty().addListener(e -> {
            if( speed < 0) {
                speed = slSpeed.getValue() * -1;
            } else {
                speed = slSpeed.getValue();
            }
        });
    }

    private EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            arc1.setStartAngle(arc1.getStartAngle() + speed);
            arc2.setStartAngle(arc2.getStartAngle() + speed);
            arc3.setStartAngle(arc3.getStartAngle() + speed);
            arc4.setStartAngle(arc4.getStartAngle() + speed);
        }
    };

    public void runFan(){
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public void pauseFan(){
        animation.pause();
    }

    public void stopFan(){
        animation.stop();
    }
}
