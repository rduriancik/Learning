package chapterApps.clientSeverCircleArea;

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
 * Created by robert on 25.10.2016.
 */
public class Server extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Text area for displaying contents
        TextArea ta = new TextArea();

        // Create a scene and place it in the stage
        Scene scene = new Scene(new ScrollPane(ta), 450, 200);
        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try {
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(() -> ta.appendText("Server started at " + new Date() + '\n'));

                // Listen for a connection request
                Socket socket = serverSocket.accept();

                // Create data input and output streams
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    // Receive radius from the client
                    double radius = inputFromClient.readDouble();

                    // Compute area
                    double area = radius * radius * Math.PI;

                    // Send area back to the client
                    outputToClient.writeDouble(area);

                    Platform.runLater(() -> {
                        ta.appendText("Radius received from client: " + radius + '\n');
                        ta.appendText("Area is: " + area + '\n');
                    });
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}
