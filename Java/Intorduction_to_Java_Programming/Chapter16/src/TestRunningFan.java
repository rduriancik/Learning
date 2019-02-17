import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * Created by robkio on 28.10.2015.
 */
public class TestRunningFan extends Application {
    @Override
    public void start(Stage primaryStage){
        FanPane pane = new FanPane();

        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestRunningFan");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}
}
