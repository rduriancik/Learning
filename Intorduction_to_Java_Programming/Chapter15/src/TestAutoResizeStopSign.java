/**
 * Created by robkio on 30.8.2015.
 */
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;


public class TestAutoResizeStopSign extends Application{
    Pane pane = new Pane();
    Polygon sign = new Polygon(10, 100, 10, 200, 100, 290, 200, 290, 290, 200, 290, 100, 200, 10, 100, 10);
    Text text = new Text(17, 185, "STOP");
    @Override
    public void start(Stage stage){
        sign.setStyle("-fx-fill: RED");
        text.setStyle("-fx-fill: WHITE; -fx-font-size: 90; -fx-font-weight: BOLD");
        pane.getChildren().addAll(sign, text);

        ChangeListener listener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                computePoints();
            }
        };
        pane.widthProperty().addListener(listener);
        pane.heightProperty().addListener(listener);

        Scene scene = new Scene(pane, 300, 300);
        stage.setTitle("Auto Resize Stop Sign");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }


    public void computePoints(){
        sign.getPoints().set(0, pane.getWidth() / 30.0);
        sign.getPoints().set(1, pane.getHeight() / 3.0);
        sign.getPoints().set(2, pane.getWidth() / 30.0);
        sign.getPoints().set(3, pane.getHeight() / (300/200.0));
        sign.getPoints().set(4, pane.getWidth() / 3.0);
        sign.getPoints().set(5, pane.getHeight() / (300/290.0));
        sign.getPoints().set(6, pane.getWidth() / (300/200.0));
        sign.getPoints().set(7, pane.getHeight() / (300/290.0));
        sign.getPoints().set(8, pane.getWidth() / (300/290.0));
        sign.getPoints().set(9, pane.getHeight() / (300/200.0));
        sign.getPoints().set(10, pane.getWidth() / (300/290.0));
        sign.getPoints().set(11, pane.getHeight() / 3);
        sign.getPoints().set(12, pane.getWidth() / (300/200.0));
        sign.getPoints().set(13, pane.getHeight() / 30);
        sign.getPoints().set(14, pane.getWidth() / 3);
        sign.getPoints().set(15, pane.getHeight() / 30);
        text.xProperty().bind(pane.widthProperty().divide(300 / 17.0));
        text.yProperty().bind(pane.heightProperty().divide(300/185.0));
        text.setFont(Font.font("Arial", FontWeight.BOLD, pane.getWidth() / (300/90.0)));
    }
}
