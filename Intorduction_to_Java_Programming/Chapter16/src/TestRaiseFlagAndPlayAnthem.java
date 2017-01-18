import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by robert on 15.11.15.
 */
public class TestRaiseFlagAndPlayAnthem extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a main border pane
        BorderPane pane = new BorderPane();

        // Create a bottom HBox with controls
        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        Button btPlay = new Button("Play");
        ComboBox<String> cboImages = new ComboBox<>();
        cboImages.getItems().addAll("UK", "US", "Canada");
        cboImages.setValue("UK");
        hBox.getChildren().addAll(btPlay, cboImages);

        pane.setBottom(hBox);

        // Create a pane for images and ImageViews
        Pane imagesPane = new Pane();
        pane.setPrefHeight(150);
        pane.setPrefWidth(200);
        pane.setCenter(imagesPane);
        ImageView UK = new ImageView("http://www.cs.armstrong.edu/liang/common/image/uk.gif");
        ImageView US = new ImageView("http://www.cs.armstrong.edu/liang/intro7e/book/image/us.gif");
        ImageView Canada = new ImageView("http://www.cs.armstrong.edu/liang/common/image/ca.gif");

        // Create a PathTransition for images
        Line line = new Line(100, -50, 100, 50);
        PathTransition ptUK = new PathTransition(Duration.millis(10000), line, UK);
        ptUK.setCycleCount(1);
        PathTransition ptUS = new PathTransition(Duration.millis(10000), line, US);
        ptUS.setCycleCount(1);
        PathTransition ptCanada = new PathTransition(Duration.millis(10000), line, Canada);
        ptCanada.setCycleCount(1);

        // Create audio clips of the national anthems
        AudioClip ukAnthem = new AudioClip(getClass().getResource("anthems/UK.mp3").toString());
        AudioClip usAnthem = new AudioClip(getClass().getResource("anthems/US.mp3").toString());
        AudioClip canadaAnthem = new AudioClip(getClass().getResource("anthems/Canada.mp3").toString());

        // Create an action event for play button
        btPlay.setOnAction(e -> {
            switch (cboImages.getValue()) {
                case "UK":
                    if (usAnthem.isPlaying()) {
                        usAnthem.stop();
                    }
                    if (canadaAnthem.isPlaying()) {
                        canadaAnthem.stop();
                    }
                    if (ukAnthem.isPlaying()) {
                        ukAnthem.stop();
                    }
                    ptUK.stop();
                    imagesPane.getChildren().clear();
                    imagesPane.getChildren().add(UK);
                    ukAnthem.play();
                    ptUK.play();
                    break;
                case "US":
                    if (usAnthem.isPlaying()) {
                        usAnthem.stop();
                    }
                    if (canadaAnthem.isPlaying()) {
                        canadaAnthem.stop();
                    }
                    if (ukAnthem.isPlaying()) {
                        ukAnthem.stop();
                    }
                    ptUS.stop();
                    imagesPane.getChildren().clear();
                    imagesPane.getChildren().add(US);
                    usAnthem.play();
                    ptUS.play();
                    break;
                case "Canada":
                    if (usAnthem.isPlaying()) {
                        usAnthem.stop();
                    }
                    if (canadaAnthem.isPlaying()) {
                        canadaAnthem.stop();
                    }
                    if (ukAnthem.isPlaying()) {
                        ukAnthem.stop();
                    }
                    ptCanada.stop();
                    imagesPane.getChildren().clear();
                    imagesPane.getChildren().add(Canada);
                    canadaAnthem.play();
                    ptCanada.play();
                    break;
            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestRaiseFlagAndPlayAnthem");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
