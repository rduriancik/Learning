import javafx.animation.Animation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;

/**
 * Created by robkio on 3.9.2015.
 */
public class TestSlideShow extends Application{
    private int imageIndex = 0;
    @Override
    public void start(Stage stage){
        StackPane pane = new StackPane();
        Image[] image = new Image[5];
        for(int i = 1; i <= image.length; i++)
            image[i-1] = new Image("slides/" + i + ".jpg");
        ImageView img = new ImageView(image[0]);
        pane.getChildren().add(img);

        EventHandler<ActionEvent> handler = e ->{
            if(imageIndex > image.length - 1)
                imageIndex = 0;
            img.setImage(image[imageIndex]);
            imageIndex++;
        };

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(2000), handler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        pane.setOnMouseClicked(e -> {
            if(animation.getStatus() == Animation.Status.RUNNING)
                animation.pause();
            else if(animation.getStatus() == Animation.Status.PAUSED)
                animation.play();
        });

        Scene scene = new Scene(pane);
        stage.setTitle("SlideSHow");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){launch(args);}
}
