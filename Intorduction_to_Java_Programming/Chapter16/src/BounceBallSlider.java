import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import  javafx.scene.layout.BorderPane;
import javafx.scene.control.Slider;

/**
 * Created by robkio on 27.9.2015.
 */
public class BounceBallSlider extends Application {
    @Override
    public void start(Stage primaryStage){
        BallPane ballPane = new BallPane();
        Slider slSpeed = new Slider();
        slSpeed.setMax(20);
        ballPane.rateProperty().bind(slSpeed.valueProperty());

        BorderPane pane = new BorderPane();
        pane.setCenter(ballPane);
        pane.setBottom(slSpeed);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle("BounceBallSlide");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);
    }
}
