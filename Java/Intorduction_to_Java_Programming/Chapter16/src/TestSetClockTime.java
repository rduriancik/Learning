import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

/**
 * Created by robkio on 4.10.2015.
 */
public class TestSetClockTime extends Application {
    private double paneWidth = 300;
    private double paneHeight = 300;
    @Override
    public void start(Stage primaryStage){
        // Create a clock pane
        ClockPane clockPane = new ClockPane();
        clockPane.setW(paneWidth); // Set clock pane's width
        clockPane.setH(paneHeight); // Set clock pane's height

        // Create HBox to hold text fields and labels
        HBox hBoxForControls = new HBox(5);
        hBoxForControls.setAlignment(Pos.CENTER);
        hBoxForControls.setPadding(new Insets(5));

        hBoxForControls.getChildren().add(new Label("Hour"));
        TextField tfHour = new TextField();
        tfHour.setPrefColumnCount(2);
        tfHour.setAlignment(Pos.CENTER_RIGHT);
        hBoxForControls.getChildren().add(tfHour);

        hBoxForControls.getChildren().add(new Label("Minute"));
        TextField tfMinute = new TextField();
        tfMinute.setPrefColumnCount(2);
        tfMinute.setAlignment(Pos.CENTER_RIGHT);
        hBoxForControls.getChildren().add(tfMinute);

        hBoxForControls.getChildren().add(new Label("Second"));
        TextField tfSecond = new TextField();
        tfSecond.setPrefColumnCount(2);
        tfSecond.setAlignment(Pos.CENTER_RIGHT);
        hBoxForControls.getChildren().add(tfSecond);

        // Create a border pane
        BorderPane pane = new BorderPane();
        pane.setCenter(clockPane);
        pane.setBottom(hBoxForControls);

        // Create action events for text fields
        tfHour.setOnAction(e -> clockPane.setHour(Integer.valueOf(tfHour.getText())));
        tfMinute.setOnAction(e -> clockPane.setMinute(Integer.valueOf(tfMinute.getText())));
        tfSecond.setOnAction(e -> clockPane.setSecond(Integer.valueOf(tfSecond.getText())));

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, paneWidth, paneHeight + 20);
        primaryStage.setTitle("TestSetClockTime");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}
}
