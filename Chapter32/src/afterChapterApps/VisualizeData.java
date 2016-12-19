package afterChapterApps;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.*;

/**
 * Created by robert on 19.12.2016.
 */
public class VisualizeData extends Application {
    private ObservableList<PieChart.Data> pieChartData;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        obtainData();

        HBox pane = new HBox(10);

        PieChart pieChart = new PieChart(pieChartData);
        pane.getChildren().add(pieChart);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise32_02");
        primaryStage.show();
    }

    private void obtainData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost/javabook", "scott", "tiger");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select deptId, count(*)" +
                    " from Student" +
                    " where deptId is not null" +
                    " group by deptId;");

            pieChartData = FXCollections.observableArrayList();

            while (resultSet.next()) {
                String name = resultSet.getString("deptId");
                int count = resultSet.getInt("count(*)");
                pieChartData.add(new PieChart.Data(name, count));
            }
        } catch (SQLException ex) {
            System.err.println("Error while obtaining data: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver not found");
        }
    }
}
