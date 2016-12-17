package afterChapterApps;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by robert on 17.12.2016.
 */
public class StaffDatabase extends Application {
    private TextField tfId;
    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfMI;
    private TextField tfAddress;
    private TextField tfCity;
    private TextField tfState;
    private TextField tfTelephone;
    private Connection connection;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeDB();

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(5);
        pane.setVgap(5);

        Label lbInfo = new Label("Staff Database");
        pane.add(lbInfo, 0, 0, 6, 1);

        //TODO: obmedzit fieldy na urcity pocet znakov
        pane.add(new Label("ID"), 0, 1);
        tfId = new TextField();
        pane.add(tfId, 1, 1);

        pane.add(new Label("Last Name"), 0, 2);
        tfLastName = new TextField();
        pane.add(tfLastName, 1, 2);

        pane.add(new Label("First Name"), 2, 2);
        tfFirstName = new TextField();
        pane.add(tfFirstName, 3, 2);

        pane.add(new Label("MI"), 4, 2);
        tfMI = new TextField();
        tfMI.setPrefColumnCount(1);
        pane.add(tfMI, 5, 2);

        pane.add(new Label("Address"), 0, 3);
        tfAddress = new TextField();
        pane.add(tfAddress, 1, 3);

        pane.add(new Label("City"), 0, 4);
        tfCity = new TextField();
        pane.add(tfCity, 1, 4);

        Label lbState = new Label("State");
        pane.add(lbState, 2, 4);
        GridPane.setHalignment(lbState, HPos.CENTER);
        tfState = new TextField();
        pane.add(tfState, 3, 4, 2, 1);

        pane.add(new Label("Telephone"), 0, 5);
        tfTelephone = new TextField();
        pane.add(tfTelephone, 1, 5);

        HBox buttons = new HBox(5);
        buttons.setAlignment(Pos.CENTER);
        Button btView = new Button("View");
        Button btInsert = new Button("Insert");
        Button btUpdate = new Button("Update");
        Button btClear = new Button("Clear");
        buttons.getChildren().addAll(btView, btInsert, btUpdate, btClear);

        pane.add(buttons, 0, 6, 6, 1);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Staff");
        primaryStage.show();

        btClear.setOnAction(e -> {
            try {
                Statement statement = connection.createStatement();
                statement.execute("DELETE FROM Staff");
                lbInfo.setText("Staff database was deleted");
            } catch (SQLException ex) {
                lbInfo.setText(ex.getMessage());
            }
        });

        btInsert.setOnAction(e -> {
            try {
                String id = tfId.getText();
                if (id.isEmpty()) {
                    lbInfo.setText("Invalid ID");
                    return;
                }
                if (id.length() > 9) {
                    lbInfo.setText("ID cannot be longer than 9 characters");
                    return;
                }


                String lastName = tfLastName.getText();
                if (lastName.isEmpty()) {
                    lbInfo.setText("Invalid last name");
                    return;
                }

                String firstName = tfFirstName.getText();
                if (firstName.isEmpty()) {
                    lbInfo.setText("Invalid first name");
                    return;
                }

                String mi = tfMI.getText();
                if (mi.isEmpty() || mi.length() != 1) {
                    lbInfo.setText("Invalid MI");
                    return;
                }

                String address = tfAddress.getText();
                if (address.isEmpty()) {
                    lbInfo.setText("Invalid address");
                    return;
                }

                String city = tfCity.getText();
                if (city.isEmpty()) {
                    lbInfo.setText("Invalid city");
                    return;
                }

                String state = tfState.getText();
                if (state.isEmpty()) {
                    lbInfo.setText("Invalid state");
                    return;
                }

                String phone = tfTelephone.getText();
                if (phone.isEmpty()) {
                    lbInfo.setText("Invalid phone");
                    return;
                }

                PreparedStatement statement = connection.prepareStatement
                        ("INSERT INTO Staff VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                statement.setString(1, id);
                statement.setString(2, lastName);
                statement.setString(3, firstName);
                statement.setString(4, mi);
                statement.setString(5, address);
                statement.setString(6, city);
                statement.setString(7, state);
                statement.setString(8, phone);

                if (statement.executeUpdate() != 1) {
                    lbInfo.setText("Insertion Error");
                } else {
                    lbInfo.setText("Insertion complete");
                }
            } catch (SQLException ex) {
                lbInfo.setText(ex.getMessage());
            } catch (NumberFormatException ex) {
                lbInfo.setText("Invalid id");
            }
        });

        //TODO: eventy pre ostatne buttony
    }

    private void initializeDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost/javabook", "scott", "tiger");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
