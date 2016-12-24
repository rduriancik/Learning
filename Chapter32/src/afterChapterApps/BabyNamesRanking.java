package afterChapterApps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by robert on 24.12.2016.
 */
public class BabyNamesRanking {
    public static void main(String[] args) {
        Statement statement;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost/javabook", "scott", "tiger");
            statement = connection.createStatement();

            /*
            statement.execute("CREATE TABLE Babyname (\n" +
                    "year INTEGER ,\n" +
                    "name VARCHAR(50),\n" +
                    "gender CHAR(1),\n" +
                    "count INTEGER ,\n" +
                    "CONSTRAINT pkBabyname PRIMARY KEY (year, name, gender)\n" +
                    ")");
            */
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver not found");
        } catch (SQLException ex) {
            System.err.println("Error while connecting to the database: " + ex.getMessage());
        }


    }
}
