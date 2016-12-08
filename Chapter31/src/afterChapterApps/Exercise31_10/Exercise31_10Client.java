package afterChapterApps.Exercise31_10;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
 * Created by robert on 18.11.2016.
 */
public class Exercise31_10Client extends Application {
    private Socket socket;
    private DataOutputStream toServer;
    private TextArea taChat;
    private String name;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        taChat = new TextArea();
        TextField tfName = new TextField();
        TextField tfText = new TextField();

        VBox vBox = new VBox(5);
        vBox.setPadding(new Insets(2));
        vBox.getChildren().addAll(new Label("Name"), tfName);
        vBox.getChildren().addAll(new Label("Enter text"), tfText);
        vBox.getChildren().add(new ScrollPane(taChat));

        Scene scene = new Scene(vBox, 350, 290);
        primaryStage.setTitle("Exercise31_10Client");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> disconnect());

        tfText.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                if (tfName.getText().isEmpty()) {
                    new Alert(Alert.AlertType.WARNING, "You must type a name.").show();
                } else {
                    name = tfName.getText();
                    new Thread(() -> {
                        try {
                            if (!socket.isClosed()) {
                                StringBuilder message = new StringBuilder(name);
                                message.append(": ");
                                message.append(tfText.getText());
                                toServer.writeUTF(message.toString());
                                toServer.flush();
                            } else {
                                Platform.runLater(() -> taChat.appendText("ERROR: Server disconnected\n"));
                            }
                            Platform.runLater(() -> tfText.clear());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }).start();
                }
            }
        });

        new Thread(() -> {
            connect();
            try (
                    DataInputStream fromServer = new DataInputStream(socket.getInputStream())
            ) {
                while (primaryStage.isShowing()) {
                    String message = fromServer.readUTF();
                    if (!message.isEmpty() && !message.equals("\n")) {
                        Platform.runLater(() -> taChat.appendText(message + '\n'));
                    }
                }
            } catch (SocketException ex) {
                // Program was closed
            } catch (EOFException ex) {
                disconnect();
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
                toServer.writeUTF(name + " disconnected");
                toServer.close();
                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
