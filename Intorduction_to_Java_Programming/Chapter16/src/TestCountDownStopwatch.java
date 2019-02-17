import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Created by robert on 03.11.15.
 */
public class TestCountDownStopwatch extends Application {
    private int seconds;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane pane = new StackPane();

        TextField tfSCountDown = new TextField();
        tfSCountDown.setAlignment(Pos.CENTER);
        tfSCountDown.setPrefColumnCount(2);
        tfSCountDown.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 30));
        pane.getChildren().add(tfSCountDown);

        Media audio = new Media("http://cs.armstrong.edu/liang/common/sample.mp4");
        MediaPlayer player = new MediaPlayer(audio);

        EventHandler<ActionEvent> handler = e -> {
            if (seconds > 0) {
                seconds--;
                tfSCountDown.setText(seconds + "");
                player.stop();
            } else {
                player.play();
            }
        };

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), handler));
        animation.setCycleCount(Timeline.INDEFINITE);

        tfSCountDown.setOnAction(e -> {
            seconds = Integer.parseInt(tfSCountDown.getText());
            animation.play();
        });
        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestCountDown");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}