import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;

import java.net.URL;

/**
 * Created by robert on 04.11.15.
 */
public class TestSoundClipControl extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button btPlay = new Button("Play");
        Button btLoop = new Button("Loop");
        Button btStop = new Button("Stop");
        HBox pane = new HBox(5);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        pane.getChildren().addAll(btPlay, btLoop, btStop);


        AudioClip audio = new AudioClip(getClass().getResource("audio.mp3").toString());

        btPlay.setOnAction(e -> {
            audio.stop();
            audio.setCycleCount(1);
            audio.play();
        });

        btLoop.setOnAction(e -> {
            audio.stop();
            audio.setCycleCount(Timeline.INDEFINITE);
            audio.play();
        });

        btStop.setOnAction(e -> {
            audio.stop();
        });

        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestSoundClipControl");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
