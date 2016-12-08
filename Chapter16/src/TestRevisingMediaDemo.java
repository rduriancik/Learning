import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by robert on 13.11.15.
 */
public class TestRevisingMediaDemo extends Application {
    private static final String MEDIA_URL = "http://cs.armstrong.edu/liang/common/sample.mp4";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a main border pane
        BorderPane pane = new BorderPane();

        // Create a center media
        Media media = new Media(MEDIA_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(1);
        MediaView mediaView = new MediaView(mediaPlayer);
        pane.setCenter(mediaView);

        // Create a bottom HBox for controls
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5));
        Button btControl = new Button("\u25ba"); // \u25ba is triangle symbol for play
        hBox.getChildren().add(btControl);
        hBox.getChildren().add(new Label("Time"));
        Slider slCurrentTime = new Slider();
        mediaPlayer.totalDurationProperty().addListener(e -> {
            slCurrentTime.setMax(mediaPlayer.getTotalDuration().toSeconds());
        });
        hBox.getChildren().add(slCurrentTime);
        Label lbTime = new Label("00:00:00/00:00:00");
        mediaPlayer.currentTimeProperty().addListener(e -> {
            Duration currentTime = mediaPlayer.getCurrentTime();
            Duration totalTime = mediaPlayer.getTotalDuration();
            lbTime.setText(String.format("%02d:%02d:%02d/%02d:%02d:%02d",
                    ((int) currentTime.toHours()), ((int) currentTime.toMinutes()) % 60, ((int) currentTime.toSeconds()) % 60,
                    ((int) totalTime.toHours()), ((int) totalTime.toMinutes()) % 60, ((int) totalTime.toSeconds()) % 60));
            slCurrentTime.setValue(currentTime.toSeconds());
        });
        hBox.getChildren().add(lbTime);
        hBox.getChildren().add(new Label("Volume"));
        Slider slVolume = new Slider(0, 100, 1);
        slVolume.setValue(20);
        mediaPlayer.volumeProperty().bind(slVolume.valueProperty().divide(100));
        hBox.getChildren().add(slVolume);

        pane.setBottom(hBox);

        // Create an ActionEvent for the button and slider of current time
        btControl.setOnAction(e -> {
            if (btControl.getText().equals("\u25ba")) {
                mediaPlayer.play();
                btControl.setText("||");
            } else {
                mediaPlayer.pause();
                btControl.setText("\u25ba");
            }
        });

        slCurrentTime.setOnMouseDragged(e -> {
            mediaPlayer.seek(Duration.seconds(slCurrentTime.getValue()));
        });
        slCurrentTime.setOnMousePressed(e -> {
            mediaPlayer.seek(Duration.seconds(slCurrentTime.getValue()));
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 720, 515);
        primaryStage.setTitle("TestRevisingMediaDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
