package afterChapterApps.exercise31_04;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by robert on 14.11.2016.
 */
public class Exercise31_04Server extends Application {

    private TextArea taInfo;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        taInfo = new TextArea();
        taInfo.setEditable(false);

        Scene scene = new Scene(new ScrollPane(taInfo), 450, 200);
        primaryStage.setTitle("Exercise31_04Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(() -> taInfo.appendText("Exercise31_04Server started at " + new Date() + '\n'));
                int number = 0;

                while (primaryStage.isShowing()) {
                    Socket socket = serverSocket.accept();

                    new Thread(new HandleClient(socket, number)).start();
                    ++number;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    class HandleClient implements Runnable {
        private Socket socket;
        private int number;

        public HandleClient(Socket socket, int number) {
            this.socket = socket;
            this.number = number;
        }

        @Override
        public void run() {
            Platform.runLater(() -> {
                taInfo.appendText("Starting thread " + number + '\n');
                taInfo.appendText("Client IP / " + socket.getInetAddress().getHostAddress() + '\n');
            });

            try (
                    DataOutputStream toClient = new DataOutputStream(socket.getOutputStream())
            ) {
                toClient.writeUTF("You are visitor " + (number + 1));
                toClient.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
