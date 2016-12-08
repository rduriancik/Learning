package chapterApps;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by robert on 22.9.2016.
 */
public class FlashText extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private String text = "";

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane pane = new StackPane();
        Label lbText = new Label("Programming is fun");
        pane.getChildren().add(lbText);

        new Thread(() -> {
            try {
                while (true) {
                    if (lbText.getText().trim().length() == 0) {
                        text = "Welcome";
                    } else {
                        text = "";
                    }

                    Platform.runLater(() -> lbText.setText(text));

                    Thread.sleep(200);
                }
            } catch (InterruptedException ex) {
            }
        }).start();

        Scene scene = new Scene(pane, 200, 50);
        primaryStage.setTitle("FlashText");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
