package afterChapterApps;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.*;


/**
 * Created by robert on 20.12.2016.
 */
public class FindGradesPlus extends Application {
    private PreparedStatement statement;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5));
        TextField tfSsn = new TextField();
        Button btShowGrade = new Button("Show Grade");
        hBox.getChildren().addAll(new Label("SSN"), tfSsn, btShowGrade);

        TextArea taGrades = new TextArea();
        taGrades.setEditable(false);

        Label lbInfo = new Label("Enter SSN");

        BorderPane pane = new BorderPane();
        pane.setTop(hBox);
        pane.setCenter(new ScrollPane(taGrades));
        pane.setBottom(lbInfo);
        BorderPane.setMargin(lbInfo, new Insets(2));

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Find Grades");
        primaryStage.show();

        // Connect to a database
        new Thread(() -> {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection
                        ("jdbc:mysql://localhost/javabook", "scott", "tiger");
                String queryString = "SELECT firstName, mi, lastName, title, grade FROM Enrollment, Student, Course " +
                        "WHERE Student.ssn = ? AND Enrollment.courseId = Course.courseId AND Enrollment.ssn = Student.ssn";

                statement = connection.prepareStatement(queryString);
            } catch (SQLException ex) {
                Platform.runLater(() -> lbInfo.setText("Error while connecting the database: " + ex.getMessage()));
            } catch (ClassNotFoundException ex) {
                Platform.runLater(() -> lbInfo.setText("Driver not found"));
            }
        }).start();

        btShowGrade.setOnAction(e -> {
            String ssn = tfSsn.getText();
            if (!ssn.isEmpty()) {
                new Thread(() -> {
                    try {
                        statement.setString(1, ssn);
                        ResultSet resultSet = statement.executeQuery();

                        int count = 0;
                        StringBuilder result = new StringBuilder();
                        while (resultSet.next()) {
                            result.append(String.format("%s %s %s grade on course %s is %s\n",
                                    resultSet.getString("firstName"),
                                    resultSet.getString("mi"),
                                    resultSet.getString("lastName"),
                                    resultSet.getString("title"),
                                    resultSet.getString("grade")));
                            ++count;
                        }

                        if (count == 0) {
                            Platform.runLater(() -> {
                                taGrades.clear();
                                lbInfo.setText("No course found for this SSN");
                            });
                        } else {
                            int finalCount = count;
                            Platform.runLater(() -> {
                                taGrades.setText(result.toString());
                                lbInfo.setText(finalCount + (finalCount > 1 ? " courses " : " course ") + "found");
                            });
                        }
                    } catch (SQLException ex) {
                        Platform.runLater(() -> {
                            taGrades.clear();
                            lbInfo.setText("Error while querying the database");
                        });
                    }
                }).start();
            }
        });
    }
}
