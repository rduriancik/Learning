package afterChapterApps.exercise31_06;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by robert on 14.11.2016.
 */
public class Exercise31_06Client extends Application {

    private static Socket socket;
    private static ObjectInputStream fromServer;
    private static ObjectOutputStream toServer;
    private static TextField tfName;
    private static TextField tfStreet;
    private static TextField tfCity;
    private static TextField tfState;
    private static TextField tfZip;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        tfName = new TextField();
        tfStreet = new TextField();
        tfCity = new TextField();
        tfState = new TextField();
        tfState.setPrefColumnCount(2);
        tfZip = new TextField();
        tfZip.setPrefColumnCount(5);

        HBox hbControls = new HBox(5);
        Button btAdd = new Button("Add");
        Button btFirst = new Button("First");
        Button btNext = new Button("Next");
        Button btPrevious = new Button("Previous");
        Button btLast = new Button("Last");
        hbControls.getChildren().addAll(btAdd, btFirst, btNext, btPrevious, btLast);
        hbControls.setAlignment(Pos.CENTER);

        GridPane pane = new GridPane();
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        pane.add(new Label("Name"), 0, 0);
        pane.add(tfName, 1, 0, 5, 1);
        pane.add(new Label("Street"), 0, 1);
        pane.add(tfStreet, 1, 1, 5, 1);
        pane.add(new Label("City"), 0, 2);
        pane.add(tfCity, 1, 2);
        pane.add(new Label("State"), 2, 2);
        pane.add(tfState, 3, 2);
        pane.add(new Label("Zip"), 4, 2);
        pane.add(tfZip, 5, 2);
        pane.add(hbControls, 0, 3, 6, 1);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Exercise31_06Client");
        primaryStage.setScene(scene);
        primaryStage.show();

        // close streams
        primaryStage.setOnCloseRequest(e -> {
            try {
                toServer.close();
                fromServer.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Connect to the server
        new Thread(() -> {
            connect();
        }).start();

        btAdd.setOnAction(e -> {
            String name = tfName.getText();
            if (name.isEmpty()) {
                showAlert("Name");
                return;
            }

            String street = tfStreet.getText();
            if (street.isEmpty()) {
                showAlert("Street");
                return;
            }

            String city = tfCity.getText();
            if (city.isEmpty()) {
                showAlert("City");
                return;
            }

            String state = tfState.getText();
            if (state.isEmpty()) {
                showAlert("State");
                return;
            }

            String zip = tfZip.getText();
            if (zip.isEmpty()) {
                showAlert("Zip");
                return;
            }

            StudentAddress address = new StudentAddress(name, street, city, state, zip);

            new Thread(() -> {
                try {
                    toServer.writeInt(Exercise31_06Constants.ADD);
                    toServer.writeObject(address);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }).start();
        });

        btFirst.setOnAction(e -> {
            new Thread(() -> {
                try {
                    toServer.writeInt(Exercise31_06Constants.FIRST);
                    toServer.flush();
                    StudentAddress address = (StudentAddress) fromServer.readObject();

                    setFields(address);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        });

        btLast.setOnAction(e -> {
            new Thread(() -> {
                try {
                    toServer.writeInt(Exercise31_06Constants.LAST);
                    toServer.flush();

                    StudentAddress address = (StudentAddress) fromServer.readObject();

                    setFields(address);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        });

        btNext.setOnAction(e -> {
            new Thread(() -> {
                try {
                    toServer.writeInt(Exercise31_06Constants.NEXT);
                    toServer.flush();

                    StudentAddress address = (StudentAddress) fromServer.readObject();

                    setFields(address);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        });

        btPrevious.setOnAction(e -> {
            new Thread(() -> {
                try {
                    toServer.writeInt(Exercise31_06Constants.PREVIOUS);
                    toServer.flush();

                    StudentAddress address = (StudentAddress) fromServer.readObject();

                    setFields(address);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        });
    }

    private void setFields(StudentAddress address) {
        Platform.runLater(() -> {
            tfName.setText(address.getName());
            tfStreet.setText(address.getStreet());
            tfCity.setText(address.getCity());
            tfState.setText(address.getState());
            tfZip.setText(address.getZip());
        });
    }

    private void showAlert(String field) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(field + " cannot be empty");
        alert.showAndWait();
    }

    private void connect() {
        try {
            socket = new Socket("localhost", 8000);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
