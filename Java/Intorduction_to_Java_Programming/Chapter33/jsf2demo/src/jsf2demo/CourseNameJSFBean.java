package jsf2demo;

import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 28.1.2017.
 */

@ManagedBean
public class CourseNameJSFBean {
    private PreparedStatement studentStatement = null;
    private String choice;
    private String[] titles;

    /**
     * Creates a new instance of CourseName
     */
    public CourseNameJSFBean() {
        initializeJdbc();
    }

    /**
     * Initialize database connection
     */
    private void initializeJdbc() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/javabook", "scott", "tiger"
            );

            // Get course titles
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT title FROM Course");
            ResultSet resultSet = preparedStatement.executeQuery();

            // Store resultSet into array titles
            List<String> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
            titles = new String[list.size()]; // Array for titles
            list.toArray(titles); // Copy strings from list to array

            // Define a SQL statement for getting students
            studentStatement = connection.prepareStatement(
                    "SELECT  Student.ssn, Student.firstName, Student.mi, Student.lastName," +
                            "Student.phone, Student.birthDate, Student.street, Student.zipCode," +
                            "Student.deptId FROM Student, Enrollment, Course " +
                            "WHERE Course.title = ? AND Student.ssn = Enrollment.ssn " +
                            "AND Enrollment.courseId = Course.courseId;"
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String[] getTitles() {
        return titles;
    }

    public ResultSet getStudents() throws SQLException {
        if (choice == null) {
            if (titles.length == 0) {
                return null;
            } else {
                studentStatement.setString(1, titles[0]);
            }
        } else {
            studentStatement.setString(1, choice);
        }

        // Get students for the specified course
        return studentStatement.executeQuery();
    }
}
