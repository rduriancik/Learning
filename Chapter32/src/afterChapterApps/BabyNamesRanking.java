package afterChapterApps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;

/**
 * Created by robert on 24.12.2016.
 */
public class BabyNamesRanking {
    public static void main(String[] args) {
        Statement statement = null;

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

        BufferedReader reader = null;
        try {
            for (int i = 1; i <= 10; ++i) {
                int year = 2000 + i;
                String filename = String.format("babyNames/babynamesranking%d.txt", year);
                reader = new BufferedReader(new FileReader(filename));

                StringBuilder values = new StringBuilder();
                Formatter formatter = new Formatter(values);
                reader.lines().forEach(s -> {
                    String[] data = s.split("\\s+");
                    formatter.format("(%d, \'%s\',\'M\',%d),", year, data[1], Integer.valueOf(data[2]));
                    formatter.format("(%d, \'%s\',\'F\',%d),", year, data[3], Integer.valueOf(data[4]));
                });

                values.setCharAt(values.length() - 1, ';');
                statement.executeUpdate("INSERT INTO Babyname VALUES " + values.toString());

                reader.close();
            }

            reader = null;
        } catch (SQLException ex) {
            System.err.println("Error while querying the database: " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("File not found.");
        } catch (IOException ex) {
            System.err.println("I/O Exception: " + ex.getMessage());
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ex) {
                System.err.println("I/O Exception: " + ex.getMessage());
            }
        }
    }
}
