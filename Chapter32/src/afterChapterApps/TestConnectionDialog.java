package afterChapterApps;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by robert on 20.12.2016.
 */
class ConnectionDialog extends BorderPane {

    private ObjectProperty<Connection> connection = null;

    public ConnectionDialog() {
        Label lbInfo = new Label("No connection");
        this.setTop(lbInfo);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(2);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(5));

        ComboBox<String> cbDriver = new ComboBox<>();
        cbDriver.getItems().add("com.mysql.cj.jdbc.Driver");
        gridPane.add(new Label("JDBC Driver"), 0, 0);
        gridPane.add(cbDriver, 1, 0);

        ComboBox<String> cbURL = new ComboBox<>();
        cbURL.getItems().add("jdbc:mysql://localhost/javabook");
        gridPane.add(new Label("Database URL"), 0, 1);
        gridPane.add(cbURL, 1, 1);

        TextField tfUsername = new TextField();
        gridPane.add(new Label("Username"), 0, 2);
        gridPane.add(tfUsername, 1, 2);

        PasswordField pfPassword = new PasswordField();
        gridPane.add(new Label("Password"), 0, 3);
        gridPane.add(pfPassword, 1, 3);

        this.setCenter(gridPane);

        Button btConnect = new Button("Connect to DB");
        this.setBottom(btConnect);
        BorderPane.setAlignment(btConnect, Pos.CENTER_RIGHT);

        this.setPadding(new Insets(5, 0, 5, 0));

        btConnect.setOnAction(event -> {
            String driver = cbDriver.getValue();
            if (driver == null || driver.isEmpty()) {
                lbInfo.setText("Select a JDBC Driver");
                return;
            }

            String url = cbURL.getValue();
            if (url == null || url.isEmpty()) {
                lbInfo.setText("Select a URL");
                return;
            }

            String username = tfUsername.getText();
            if (username.isEmpty()) {
                lbInfo.setText("Enter a username");
                return;
            }

            String password = pfPassword.getText();
            if (password.isEmpty()) {
                lbInfo.setText("Enter a password");
                return;
            }

            try {
                Class.forName(driver);
                Connection conn = DriverManager.getConnection(url, username, password);
                connection = new SimpleObjectProperty<Connection>(conn);
                lbInfo.setText("Connected to " + url);
            } catch (ClassNotFoundException ex) {
                lbInfo.setText("Driver not found");
            } catch (SQLException ex) {
                lbInfo.setText("Access denied");
            }
        });
    }

    public ObjectProperty<Connection> getConnectionProperty() {
        return connection;
    }
}

public class TestConnectionDialog extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ConnectionDialog connectionDialog = new ConnectionDialog();

        Scene scene = new Scene(connectionDialog);
        primaryStage.setTitle("Connection Dialog test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
