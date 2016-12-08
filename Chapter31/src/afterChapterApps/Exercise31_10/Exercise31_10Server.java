package afterChapterApps.Exercise31_10;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by robert on 18.11.2016.
 */
public class Exercise31_10Server extends Application {
    private Map<Socket, DataOutputStream> clientsOutput;
    private TextArea taInfo;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        clientsOutput = new HashMap();
        taInfo = new TextArea();
        taInfo.setWrapText(true);

        Scene scene = new Scene(new ScrollPane(taInfo), 540, 195);
        primaryStage.setTitle("Exercise31_10Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> System.exit(0));

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8000);

                while (primaryStage.isShowing()) {
                    Socket socket = serverSocket.accept();
                    clientsOutput.put(socket, new DataOutputStream(socket.getOutputStream()));

                    new Thread(new HandleClientMessage(socket)).start();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    class HandleClientMessage implements Runnable {
        private Socket socket;

        public HandleClientMessage(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    DataInputStream fromClient = new DataInputStream(socket.getInputStream())
            ) {
                while (true) {
                    String message = fromClient.readUTF().trim();
                    if (!message.isEmpty() && !message.equals("\n")) {
                        // Show connection info
                        StringBuilder info = new StringBuilder("Connection from socket[addr=/");
                        info.append(socket.getInetAddress().getHostAddress());
                        info.append(", port=");
                        info.append(socket.getPort());
                        info.append(", localport=");
                        info.append(socket.getLocalPort());
                        info.append("] at ");
                        info.append(new Date());
                        info.append('\n');
                        Platform.runLater(() -> taInfo.appendText(info.toString()));

                        // send message to clients
                        sendMessage(message);
                    }
                }
            } catch (EOFException ex) {
                try {
                    clientsOutput.remove(socket).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void sendMessage(String message) {
        try {
            for (DataOutputStream outputStream : clientsOutput.values()) {
                outputStream.writeUTF(message);
                outputStream.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
