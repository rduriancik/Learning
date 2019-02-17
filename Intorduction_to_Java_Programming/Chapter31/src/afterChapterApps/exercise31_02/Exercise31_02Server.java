package afterChapterApps.exercise31_02;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by robert on 13.11.2016.
 */
public class Exercise31_02Server extends Application {

    private TextArea taInfo;
    private DataInputStream fromClient;
    private DataOutputStream toClient;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        taInfo = new TextArea();
        taInfo.setEditable(false);

        Scene scene = new Scene(new ScrollPane(taInfo), 450, 200);
        primaryStage.setTitle("Exercise31_02Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(() -> taInfo.appendText("Exercise31_02Server started at " + new Date() + '\n'));

                Socket socket = serverSocket.accept();
                Platform.runLater(() -> taInfo.appendText("Connected to a client at " + new Date() + '\n'));

                fromClient = new DataInputStream(socket.getInputStream());
                toClient = new DataOutputStream(socket.getOutputStream());

                double weight = fromClient.readDouble();
                Platform.runLater(() -> taInfo.appendText("Weight: " + weight + '\n'));
                double height = fromClient.readDouble();
                Platform.runLater(() -> taInfo.appendText("Height: " + height + '\n'));

                double bmi = (weight * 703) / (height * height);

                String status;
                if (bmi < 18.5) {
                    status = "Underweight";
                } else if (bmi < 25) {
                    status = "Normal weight";
                } else if (bmi < 30) {
                    status = "Overweight";
                } else {
                    status = "Obese";
                }

                Platform.runLater(() -> taInfo.appendText(String.format("BMI is %.2f - %s%n", bmi, status)));
                toClient.writeDouble(bmi);
                toClient.writeUTF(status);
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    toClient.close();
                    fromClient.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
}
