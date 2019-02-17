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
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by robert on 17.11.2016.
 */
public class ChatClient extends Application {
    private Socket socket = null;
    private DataOutputStream toServer = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextArea taServer = new TextArea();
        taServer.setEditable(false);
        TextArea taClient = new TextArea();

        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(new Label("Server"), new ScrollPane(taServer));
        vBox.getChildren().addAll(new Label("Client"), new ScrollPane(taClient));

        Scene scene = new Scene(vBox, 300, 350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ChatClient");
        primaryStage.show();

        taClient.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                if (!socket.isClosed()) {
                    new Thread(() -> {
                        try {
                            String message = taClient.getText();
                            toServer.writeUTF(message);
                            toServer.flush();
                            Platform.runLater(() -> taClient.clear());
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
                    DataInputStream fromServer = new DataInputStream(socket.getInputStream())
            ) {
                while (primaryStage.isShowing()) {
                    String message = fromServer.readUTF().trim();
                    if (!message.isEmpty() && !message.equals("\n")) {
                        Platform.runLater(() -> taServer.appendText(message + '\n'));
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
            socket = new Socket("localhost", 8000);
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void disconnect() {
        try {
            if (!socket.isClosed()) {
                toServer.writeUTF("Client disconnected");

                toServer.close();
                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
