package afterChapterApps;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.sql.*;

/**
 * Created by robert on 21.12.2016.
 */
public class DisplayTables extends Application {

    private Statement statement;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(2));

        ComboBox<String> cbTables = new ComboBox<>();
        Button btShow = new Button("Show Contents");
        hBox.getChildren().addAll(new Label("Table name"), cbTables, btShow);

        TextArea taInfo = new TextArea();
        taInfo.setEditable(false);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(1));
        pane.setTop(hBox);
        pane.setCenter(new ScrollPane(taInfo));

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("DisplayTables");
        primaryStage.show();

        btShow.setOnAction(e -> {
            String table = cbTables.getValue();
            new Thread(() -> {
                try {
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table);
                    ResultSetMetaData rsMetaData = resultSet.getMetaData();

                    String[] columnName = new String[rsMetaData.getColumnCount()];
                    for (int i = 1; i <= columnName.length; ++i) {
                        columnName[i-1] = rsMetaData.getColumnName(i);
                    }

                    StringBuilder result = new StringBuilder();
                    while (resultSet.next()) {
                        for (String aColumnName : columnName) {
                            result.append(resultSet.getString(aColumnName));
                            result.append('\t');
                        }
                        result.append('\n');
                    }

                    StringBuilder head = new StringBuilder();
                    for (String aColumnName : columnName) {
                        head.append(aColumnName);
                        head.append('\t');
                    }
                    head.append('\n');
                    result.insert(0, head);

                    Platform.runLater(() -> taInfo.setText(result.toString()));
                } catch (SQLException ex) {
                    Platform.runLater(() -> taInfo.setText("Error while querying the database: " + ex.getMessage()));
                }
            }).start();
        });

        new Thread(() -> {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection
                        ("jdbc:mysql://localhost/javabook", "scott", "tiger");
                statement = connection.createStatement();

                DatabaseMetaData dbMetaData = connection.getMetaData();
                ResultSet resultSet = dbMetaData.getTables(null, null, "%",
                        new String[]{"TABLE"});
                ObservableList<String> tables = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    tables.add(resultSet.getString("TABLE_NAME"));
                }
                Platform.runLater(() -> {
                    cbTables.setItems(tables);
                    cbTables.setValue(tables.get(0));
                });

            } catch (SQLException ex) {
                Platform.runLater(() -> taInfo.setText("SQL error: " + ex.getMessage()));
            } catch (ClassNotFoundException ex) {
                Platform.runLater(() -> taInfo.setText("Driver not found."));
            }
        }).start();
    }
}
