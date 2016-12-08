package afterChapterApps.exercise31_09;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by robert on 17.11.2016.
 */
public class ChatServer extends Application {
    private ServerSocket serverSocket;
    private Socket socket;
    private DataOutputStream toClient;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextArea taServer = new TextArea();
        TextArea taClient = new TextArea();
        taClient.setEditable(false);

        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(new Label("Client"), new ScrollPane(taClient));
        vBox.getChildren().addAll(new Label("Server"), new ScrollPane(taServer));

        Scene scene = new Scene(vBox, 300, 350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ChatServer");
        primaryStage.show();

        taServer.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                if (!socket.isClosed()) {
                    new Thread(() -> {
                        try {
                            String message = taServer.getText();
                            toClient.writeUTF(message);
                            toClient.flush();
                            Platform.runLater(() -> taServer.clear());
                        } catch (IOException ex) {
                            disconnect();
                            ex.printStackTrace();
                        }
                    }).start();
                }
            }
        });

        primaryStage.setOnCloseRequest(e -> disconnect());

        new Thread(() -> {
            connect();
            try (
                    DataInputStream fromClient = new DataInputStream(socket.getInputStream())
            ) {
                while (primaryStage.isShowing()) {
                    String message = fromClient.readUTF().trim();
                    if (!message.isEmpty() && !message.equals("\n")) {
                        Platform.runLater(() -> taClient.appendText(message + '\n'));
                    }
                }
            } catch (EOFException ex) {
                disconnect();
            } catch (SocketException ex) {
                // application was closed
            } catch (IOException ex) {
                disconnect();
                ex.printStackTrace();
            }
        }).start();
    }

    private void connect() {
        try {
            serverSocket = new ServerSocket(8000);
            socket = serverSocket.accept();
            toClient = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void disconnect() {
        try {
            if (!socket.isClosed()) {
                toClient.writeUTF("Server disconnected");

                toClient.close();
                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
