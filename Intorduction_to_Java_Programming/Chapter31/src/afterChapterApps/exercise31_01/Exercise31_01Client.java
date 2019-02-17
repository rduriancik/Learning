package afterChapterApps.exercise31_01;

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
public class Exercise31_01Client extends Application {

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

        TextField tfInterestRate = new TextField();
        tfInterestRate.setAlignment(Pos.CENTER_RIGHT);
        TextField tfYears = new TextField();
        tfYears.setAlignment(Pos.CENTER_RIGHT);
        TextField tfAmount = new TextField();
        tfAmount.setAlignment(Pos.CENTER_RIGHT);
        Button btSubmit = new Button("Submit");

        inputPane.add(new Label("Annual Interest Rate"), 0, 0);
        inputPane.add(tfInterestRate, 1, 0);
        inputPane.add(new Label("Number Of Years"), 0, 1);
        inputPane.add(tfYears, 1, 1);
        inputPane.add(new Label("Loan Amount"), 0, 2);
        inputPane.add(tfAmount, 1, 2);
        inputPane.add(btSubmit, 2, 1);

        TextArea taInfo = new TextArea();
        taInfo.setEditable(false);

        BorderPane pane = new BorderPane();
        pane.setTop(inputPane);
        pane.setCenter(new ScrollPane(taInfo));

        Scene scene = new Scene(pane, 390, 250);
        primaryStage.setTitle("Exercise31_01Client");
        primaryStage.setScene(scene);
        primaryStage.show();

        btSubmit.setOnAction(e -> {
            try {
                Socket socket = new Socket("localhost", 8000);

                double annualInterestRate = Double.valueOf(tfInterestRate.getText());
                taInfo.appendText("Annual Interest rate: " + annualInterestRate + '\n');
                double years = Double.valueOf(tfYears.getText());
                taInfo.appendText("Number of Years: " + years + '\n');
                double amount = Double.valueOf(tfAmount.getText());
                taInfo.appendText("Loan Amount: " + amount + '\n');

                toServer = new DataOutputStream(socket.getOutputStream());
                fromServer = new DataInputStream(socket.getInputStream());

                toServer.writeDouble(annualInterestRate);
                toServer.writeDouble(years);
                toServer.writeDouble(amount);
                toServer.flush();

                double monthlyPayment = fromServer.readDouble();
                double totalPayment = fromServer.readDouble();
                taInfo.appendText("Monthly Payment: " + monthlyPayment + '\n');
                taInfo.appendText("Total Payment: " + totalPayment + '\n');
                taInfo.appendText("-------------------------------------------------------\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NumberFormatException ex) {
                taInfo.appendText("ERROR: Invalid value! Each value must be a number\n");
            } finally {
                try {
                    toServer.close();
                    fromServer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
