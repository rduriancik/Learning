package afterChapterApps.exercise31_04;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by robert on 14.11.2016.
 */
public class Exercise31_04Client extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Text text = new Text();

        Scene scene = new Scene(new StackPane(text), 250, 100);
        primaryStage.setTitle("Exercise31_04Client");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try {
                Socket socket = new Socket("localhost", 8000);
                try (DataInputStream fromServer = new DataInputStream(socket.getInputStream())) {
                    String message = fromServer.readUTF();
                    System.out.println(message);
                    Platform.runLater(() -> text.setText(message));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}
