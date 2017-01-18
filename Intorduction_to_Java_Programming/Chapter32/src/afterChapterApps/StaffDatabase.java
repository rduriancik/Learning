package afterChapterApps;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
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

import java.sql.*;

/*
    create table Staff (
    id char(9) not null,
    lastName varchar(15),
    firstName varchar(15),
    mi char(1),
    address varchar(20),
    city varchar(20),
    state char(2),
    telephone char(10),
    primary key (id)
    );
 */

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
        tfId.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue.length() > 9) {
                ((StringProperty) observable).setValue(oldValue);
            }
        }));
        pane.add(tfId, 1, 1);

        pane.add(new Label("Last Name"), 0, 2);
        tfLastName = new TextField();
        tfLastName.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue.length() > 15) {
                ((StringProperty) observable).setValue(oldValue);
            }
        }));
        pane.add(tfLastName, 1, 2);

        pane.add(new Label("First Name"), 2, 2);
        tfFirstName = new TextField();
        tfFirstName.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue.length() > 15) {
                ((StringProperty) observable).setValue(oldValue);
            }
        }));
        pane.add(tfFirstName, 3, 2);

        pane.add(new Label("MI"), 4, 2);
        tfMI = new TextField();
        tfMI.setPrefColumnCount(1);
        tfMI.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue.length() > 1) {
                ((StringProperty) observable).setValue(oldValue);
            }
        }));
        pane.add(tfMI, 5, 2);

        pane.add(new Label("Address"), 0, 3);
        tfAddress = new TextField();
        tfAddress.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue.length() > 20) {
                ((StringProperty) observable).setValue(oldValue);
            }
        }));
        pane.add(tfAddress, 1, 3);

        pane.add(new Label("City"), 0, 4);
        tfCity = new TextField();
        tfCity.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue.length() > 20) {
                ((StringProperty) observable).setValue(oldValue);
            }
        }));
        pane.add(tfCity, 1, 4);

        Label lbState = new Label("State");
        pane.add(lbState, 2, 4);
        GridPane.setHalignment(lbState, HPos.CENTER);
        tfState = new TextField();
        tfState.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue.length() > 2) {
                ((StringProperty) observable).setValue(oldValue);
            }
        }));
        pane.add(tfState, 3, 4, 2, 1);

        pane.add(new Label("Telephone"), 0, 5);
        tfTelephone = new TextField();
        tfTelephone.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue.length() > 10) {
                ((StringProperty) observable).setValue(oldValue);
            }
        }));
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
                clearFields(true);
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
                if (!isValidId(id)) {
                    lbInfo.setText("Invalid ID");
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
                    clearFields(true);
                }
            } catch (SQLException ex) {
                lbInfo.setText(ex.getMessage());
            }
        });

        btUpdate.setOnAction(e -> {
            String id = tfId.getText();
            if (!isValidId(id)) {
                lbInfo.setText("Invalid ID");
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

            try {
                PreparedStatement statement = connection.prepareStatement
                        ("UPDATE Staff SET lastName = ?, firstName = ?, mi = ?, " +
                                "address = ?, city = ?, state = ?, telephone = ? WHERE id = ?");
                statement.setString(1, lastName);
                statement.setString(2, firstName);
                statement.setString(3, mi);
                statement.setString(4, address);
                statement.setString(5, city);
                statement.setString(6, state);
                statement.setString(7, phone);
                statement.setString(8, id);

                if (statement.executeUpdate() != 1) {
                    lbInfo.setText("Update Error. ID was not found");
                } else {
                    lbInfo.setText("Update complete");
                    clearFields(true);
                }
            } catch (SQLException ex) {
                lbInfo.setText(ex.getMessage());
            }
        });

        btView.setOnAction(e -> {
            String id = tfId.getText();
            clearFields(false);
            if (!isValidId(id)) {
                lbInfo.setText("Invalid ID");
                return;
            }

            try {
                ResultSet resultSet = connection.createStatement()
                        .executeQuery("SELECT * FROM Staff WHERE id = " + id);
                if (resultSet.next()) {
                    tfLastName.setText(resultSet.getString("lastName"));
                    tfFirstName.setText(resultSet.getString("firstName"));
                    tfMI.setText(resultSet.getString("mi"));
                    tfAddress.setText(resultSet.getString("address"));
                    tfCity.setText(resultSet.getString("city"));
                    tfState.setText(resultSet.getString("state"));
                    tfTelephone.setText(resultSet.getString("telephone"));
                } else {
                    lbInfo.setText("Staff member not found");
                }
            } catch (SQLException ex) {
                lbInfo.setText(ex.getMessage());
            }
        });
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

    private boolean isValidId(String id) {
        if (id == null || id.isEmpty() || id.length() > 9) {
            return false;
        }

        for (int i = 0; i < id.length(); ++i) {
            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private void clearFields(boolean clearId) {
        if (clearId) {
            tfId.clear();
        }
        tfLastName.clear();
        tfFirstName.clear();
        tfAddress.clear();
        tfMI.clear();
        tfCity.clear();
        tfState.clear();
        tfTelephone.clear();
    }
}
