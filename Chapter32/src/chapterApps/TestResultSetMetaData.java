package chapterApps;

import java.sql.*;

/**
 * Created by robert on 1.12.2016.
 */
public class TestResultSetMetaData {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        // Connection to a database
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/javabook", "scott", "tiger");
        System.out.println("Database connected");

        // Create a statement
        Statement statement = connection.createStatement();

        // Execute a statement
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Enrollment");

        ResultSetMetaData rsMetaData = resultSet.getMetaData();
        for (int i = 1; i <= rsMetaData.getColumnCount(); ++i) {
            System.out.printf("%-12s\t", rsMetaData.getColumnName(i));
        }
        System.out.println();

        // Iterate through the result and print the student's names
        while (resultSet.next()) {
            for (int i = 1; i <= rsMetaData.getColumnCount(); ++i)
                System.out.printf("%-12s\t", resultSet.getObject(i));

            System.out.println();
        }

        // Close the connection
        connection.close();
    }
}
