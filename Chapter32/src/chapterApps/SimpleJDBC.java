package chapterApps;

import java.sql.*;

/**
 * Created by robert on 26.11.2016.
 */
// You have to add MYSQL connector library
public class SimpleJDBC {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        // Connect to a database
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
        System.out.println("Database connected");

        // Create a statement
        Statement statement = connection.createStatement();

        // Execute a statement
        ResultSet resultSet = statement.executeQuery("SELECT firstName, mi, lastName FROM Student WHERE lastName = 'Smith'");

        // Iterate through the result and print the student names
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));
        }

        // Close the connection
        connection.close();
    }
}
