package afterChapterApps.exercise31_01;

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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by robert on 13.11.2016.
 */
public class Exercise31_01Server extends Application {

    private TextArea taInfo;
    private AtomicInteger clientNum = new AtomicInteger(0);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        taInfo = new TextArea();
        taInfo.setEditable(false);

        Scene scene = new Scene(new ScrollPane(taInfo), 450, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise31_01Server");
        primaryStage.show();

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(() -> taInfo.setText("Exercise31_01Server started at " + new Date() + "\n"));
                while (primaryStage.isShowing()) {
                    Socket socket = serverSocket.accept();
                    Platform.runLater(() -> taInfo.appendText("Connected to a clientNum " + clientNum.getAndIncrement() + " at " + new Date() + '\n'));

                    new Thread(new HandleClient(socket, clientNum)).start();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    class HandleClient implements Runnable {
        private Socket socket;
        private AtomicInteger clientNum;

        public HandleClient(Socket socket, AtomicInteger clientNum) {
            this.socket = socket;
            this.clientNum = clientNum;
        }

        @Override
        public void run() {
            try (
                    DataInputStream fromClient = new DataInputStream(socket.getInputStream());
                    DataOutputStream toClient = new DataOutputStream(socket.getOutputStream())) {

                double annualInterestRate = fromClient.readDouble();
                Platform.runLater(() -> taInfo.appendText("Client " + clientNum + " Annual Interest Rate: " + annualInterestRate + '\n'));
                double years = fromClient.readDouble();
                Platform.runLater(() -> taInfo.appendText("Client " + clientNum + " Number of Years: " + years + '\n'));
                double loanAmount = fromClient.readDouble();
                Platform.runLater(() -> taInfo.appendText("Client " + clientNum + " Loan Amount: " + loanAmount + '\n'));

                double monthlyPayment = getMonthlyPayment(annualInterestRate, years, loanAmount);
                Platform.runLater(() -> taInfo.appendText("Client " + clientNum + " Monthly Payment: " + monthlyPayment + '\n'));

                double totalPayment = getTotalPayment(annualInterestRate, years, loanAmount);
                Platform.runLater(() -> taInfo.appendText("Client " + clientNum + " Total Payment: " + totalPayment + '\n'));

                toClient.writeDouble(monthlyPayment);
                toClient.writeDouble(totalPayment);
                Platform.runLater(() -> taInfo.appendText("Client " + clientNum + " data sent\n\n"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        private double getMonthlyPayment(double annualInterestRate, double years, double amount) {
            double monthlyInterestRate = annualInterestRate / 1200;
            return amount * monthlyInterestRate / (1 - (1 / Math.pow(1 + monthlyInterestRate, years * 12)));
        }

        private double getTotalPayment(double annualInterestRate, double years, double amount) {
            return getMonthlyPayment(annualInterestRate, years, amount) * years * 12;
        }
    }

}
