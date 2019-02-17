import com.sun.javafx.binding.StringFormatter;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;


/**
 * Created by robkio on 30.10.2015.
 */
public class TestStopWatch extends Application {
    private int seconds;
    private int minutes;
    private int hours;

    @Override
    public void start(Stage primaryStage){
        BorderPane pane = new BorderPane();

        Text time = new Text(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        time.setFont(Font.font("System", FontWeight.SEMI_BOLD, 50));
        pane.setCenter(time);
        BorderPane.setMargin(time, new Insets(10));

        Button btControl = new Button("Start");
        Button btClear = new Button("Clear");
        HBox hBoxForButtons = new HBox(5);
        hBoxForButtons.setAlignment(Pos.CENTER);
        hBoxForButtons.getChildren().addAll(btControl, btClear);
        pane.setBottom(hBoxForButtons);

        EventHandler<ActionEvent> handler = e -> {
            seconds++;

            if(seconds == 60){
                minutes++;
                seconds = 0;
            }

            if(minutes == 60){
                hours++;
                minutes = 0;
                seconds = 0;
            }

            time.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        };

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), handler));
        animation.setCycleCount(Timeline.INDEFINITE);

        btClear.setOnAction(e -> {
            seconds = 0;
            minutes = 0;
            hours = 0;
            animation.stop();
            time.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
            btControl.setText("Start");
        });

        btControl.setOnAction(e -> {
            switch(btControl.getText()){
                case "Start":
                    animation.play();
                    btControl.setText("Pause");
                    break;
                case "Pause":
                    animation.pause();
                    btControl.setText("Resume");
                    break;
                case "Resume":
                    animation.play();
                    btControl.setText("Pause");
                    break;
            }
        });


        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestStopWatch");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}
}
