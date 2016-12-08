import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by robert on 04.11.15.
 */
public class TestImageAnimatorWithAudio extends Application {
    AudioClip audio;
    String prefix;
    int numberImages;
    int imageNumber;
    double duration;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create main pane
        BorderPane pane = new BorderPane();

        // Create top button control
        Button btStartAnimation = new Button("Start Animation");
        pane.setTop(btStartAnimation);
        BorderPane.setAlignment(btStartAnimation, Pos.CENTER_RIGHT);

        // Create bottom gridPane with labels and text fields
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5));
        gridPane.setHgap(2);
        gridPane.setVgap(5);

        Label info = new Label("Enter information for animation");
        info.setFont(Font.font("System", FontWeight.BOLD, 15));
        gridPane.add(info, 0, 0);

        gridPane.add(new Label("Animation speed in milliseconds"), 0, 1);
        TextField tfSpeed = new TextField();
        tfSpeed.setPrefColumnCount(40);
        gridPane.add(tfSpeed, 1, 1);

        gridPane.add(new Label("Image file prefix"), 0, 2);
        TextField tfImagePrefix = new TextField();
        gridPane.add(tfImagePrefix, 1, 2);

        gridPane.add(new Label("Number of images"), 0, 3);
        TextField tfNumberImages = new TextField();
        gridPane.add(tfNumberImages, 1, 3);

        gridPane.add(new Label("Audio file URL"), 0, 4);
        TextField tfAudioURL = new TextField();
        gridPane.add(tfAudioURL, 1, 4);

        pane.setBottom(gridPane);

        EventHandler<ActionEvent> handlerForAnimation = e -> {
            ImageView image = new ImageView("images/" + prefix + imageNumber + ".jpg");
            pane.setCenter(image);
            BorderPane.setMargin(image, new Insets(10));
            if (imageNumber == numberImages) {
                imageNumber = 1;
            } else {
                imageNumber++;
            }
        };

        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);

        btStartAnimation.setOnAction(e -> {
            // Animation solution
            animation.stop();
            animation.getKeyFrames().clear();
            duration = Double.parseDouble(tfSpeed.getText());
            prefix = tfImagePrefix.getText().toUpperCase(); // only L and N
            numberImages = Integer.parseInt(tfNumberImages.getText()); // MAX is 5
            imageNumber = 1;
            animation.getKeyFrames().add(new KeyFrame(Duration.millis(duration), handlerForAnimation));
            animation.play();
            // Audio solution
            if (audio != null && audio.isPlaying()) {
                audio.stop();
            }
            audio = new AudioClip(tfAudioURL.getText()); // URL http://www.cs.armstrong.edu/liang/common/audio/anthem/anthem2.mp3
            audio.play();
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 850, 700);
        primaryStage.setTitle("TestImageAnimatorWithAudio");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
