import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

/**
 * Created by robkio on 30.10.2015.
 */
public class TestGroupFansControl extends Application {
    @Override
    public void start(Stage primaryStage){
        BorderPane pane = new BorderPane();

        HBox hbForFanPanes = new HBox(5);
        hbForFanPanes.setAlignment(Pos.CENTER);
        hbForFanPanes.setPadding(new Insets(10));
        FanPane fanPane1 = new FanPane();
        fanPane1.setStyle("-fx-border-color: black");
        FanPane fanPane2 = new FanPane();
        fanPane2.setStyle("-fx-border-color: black");
        FanPane fanPane3 = new FanPane();
        fanPane3.setStyle("-fx-border-color: black");
        hbForFanPanes.getChildren().addAll(fanPane1, fanPane2, fanPane3);

        pane.setCenter(hbForFanPanes);

        HBox hbforButtons = new HBox(5);
        hbforButtons.setAlignment(Pos.CENTER);
        Button btStartAll = new Button("Start All");
        Button btStopAll = new Button("Stop All");
        hbforButtons.getChildren().addAll(btStartAll, btStopAll);

        pane.setBottom(hbforButtons);

        btStartAll.setOnAction(e -> {
            fanPane1.runFan();
            fanPane2.runFan();
            fanPane3.runFan();
        });

        btStopAll.setOnAction(e -> {
            fanPane1.stopFan();
            fanPane2.stopFan();
            fanPane3.stopFan();
        });

        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestGroupFansControl");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}
}
