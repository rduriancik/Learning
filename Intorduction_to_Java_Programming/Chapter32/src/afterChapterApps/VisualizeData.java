package afterChapterApps;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.*;

/**
 * Created by robert on 19.12.2016.
 */
public class VisualizeData extends Application {
    private ObservableList<PieChart.Data> pieChartData;
    private ObservableList<XYChart.Series<String, Number>> barChartData;
    private final String[] colors = {"#ffd700", "#860061", "#ff5700", "#adff2f"};

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        obtainData();

        HBox pane = new HBox(10);

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setLegendVisible(false);
        pane.getChildren().add(pieChart);

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis, barChartData);
        barChart.setLegendVisible(false);
        pane.getChildren().add(barChart);

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
            barChartData = FXCollections.observableArrayList();
            XYChart.Series<String, Number> series = new XYChart.Series<>();

            int i = 0; // for color index
            while (resultSet.next()) {
                String name = resultSet.getString("deptId");
                int count = resultSet.getInt("count(*)");

                int index = (i++) % colors.length;

                PieChart.Data pcd = new PieChart.Data(name, count);
                pcd.nodeProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        newValue.setStyle("-fx-pie-color: " + colors[index]);
                    }
                });
                pieChartData.add(pcd);

                XYChart.Data<String, Number> xcd = new XYChart.Data<>(name, count);
                xcd.nodeProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        newValue.setStyle("-fx-bar-fill: " + colors[index]);
                    }
                });
                series.getData().add(xcd);
            }

            barChartData.add(series);

        } catch (SQLException ex) {
            System.err.println("Error while obtaining data: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver not found");
        }
    }
}
