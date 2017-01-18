package chapterApps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

/**
 * Created by robert on 29.11.2016.
 */
public class FindGrade extends Application {
    // Statement for executing queries
    private Statement stmt;
    private TextField tfSSN = new TextField();
    private TextField tfCourseId = new TextField();
    private Label lblStatus = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initializes database connection and create a Statement object
        initializeDB();

        Button btShowGrade = new Button("Show grade");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("SSN"), tfSSN, new Label("CourseID"), tfCourseId, (btShowGrade));

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(hBox, lblStatus);

        tfSSN.setPrefColumnCount(6);
        tfCourseId.setPrefColumnCount(6);
        btShowGrade.setOnAction(e -> showGrade());

        Scene scene = new Scene(vBox, 420, 80);
        primaryStage.setTitle("Find grade");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeDB() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            // Establish a connection
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost/javabook", "scott", "tiger");
            System.out.println("Database connected");

            // Create a statement
            stmt = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showGrade() {
        String ssn = tfSSN.getText();
        String courseId = tfCourseId.getText();
        if (!ssn.isEmpty() || !courseId.isEmpty()) {

            try {
                String queryString = "SELECT firstName, mi, lastName, title, grade FROM Student, Enrollment, Course " +
                        "WHERE Student.ssn = " + ssn + " AND Enrollment.courseId = " + courseId + " AND " +
                        "Enrollment.courseId = Course.courseId AND Enrollment.ssn = Student.ssn";
                ResultSet resultSet = stmt.executeQuery(queryString);

                if (resultSet.next()) {
                    String firstName = resultSet.getString(1);
                    String mi = resultSet.getString(2);
                    String lastName = resultSet.getString(3);
                    String title = resultSet.getString(4);
                    String grade = resultSet.getString(5);

                    // Display result in a label
                    lblStatus.setText(firstName + " " + mi + " " + lastName + "'s grade on course " + title + " is " + grade);
                } else {
                    lblStatus.setText("Not found");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            lblStatus.setText("You must fill all fields.");
        }

    }
}
