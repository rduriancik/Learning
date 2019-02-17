package chapterApps;

import java.sql.*;

/**
 * Created by robert on 1.12.2016.
 */
public class FindUserTables {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        // Connect to a database
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/javabook", "scott", "tiger");
        System.out.println("Database connected");

        DatabaseMetaData dbMetaData = connection.getMetaData();

        ResultSet rsTable = dbMetaData.getTables(null, null, "%", new String[]{"TABLE"});
        System.out.println("User tables: ");
        while (rsTable.next()) {
            System.out.println(rsTable.getString("TABLE_NAME") + " ");
        }

        // Close the connection
        connection.close();
    }
}
