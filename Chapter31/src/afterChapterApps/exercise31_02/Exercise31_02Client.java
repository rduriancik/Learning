package afterChapterApps.exercise31_02;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by robert on 13.11.2016.
 */
public class Exercise31_02Client extends Application {

    private DataInputStream fromServer;
    private DataOutputStream toServer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane inputPane = new GridPane();
        inputPane.setHgap(5);
        inputPane.setVgap(2);
        inputPane.setPadding(new Insets(5));

        TextField tfWeight = new TextField();
        tfWeight.setPrefColumnCount(5);
        tfWeight.setAlignment(Pos.CENTER_RIGHT);
        TextField tfHeight = new TextField();
        tfHeight.setPrefColumnCount(5);
        tfHeight.setAlignment(Pos.CENTER_RIGHT);
        Button btSubmit = new Button("Submit");

        inputPane.add(new Label("Weight in pounds"), 0, 0);
        inputPane.add(tfWeight, 1, 0);
        inputPane.add(new Label("Height in inches"), 0, 1);
        inputPane.add(tfHeight, 1, 1);
        inputPane.add(btSubmit, 2, 1);

        TextArea taInfo = new TextArea();

        BorderPane pane = new BorderPane();
        pane.setTop(inputPane);
        pane.setCenter(new ScrollPane(taInfo));

        Scene scene = new Scene(pane, 350, 200);
        primaryStage.setTitle("Exercise31_02Client");
        primaryStage.setScene(scene);
        primaryStage.show();

        btSubmit.setOnAction(e -> {
            try {
                Socket socket = new Socket("localhost", 8000);

                fromServer = new DataInputStream(socket.getInputStream());
                toServer = new DataOutputStream(socket.getOutputStream());

                double weight = Double.valueOf(tfWeight.getText());
                taInfo.appendText("Weight: " + weight + '\n');
                double height = Double.valueOf(tfHeight.getText());
                taInfo.appendText("Height: " + height + '\n');

                toServer.writeDouble(weight);
                toServer.writeDouble(height);

                double bmi = fromServer.readDouble();
                String status = fromServer.readUTF();
                taInfo.appendText(String.format("BMI is %.2f - %s%n", bmi, status));

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    fromServer.close();
                    toServer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
