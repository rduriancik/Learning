import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

/**
 * Created by robkio on 21.10.2015.
 */
public class TestUseScbAndSl extends Application {

    @Override
    public void start(Stage primaryStage){
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20, 30, 5, 30));

        Label text = new Label("Show Colors");

        GridPane gridPane = new GridPane();
        Slider slRed = new Slider(0, 1, 0.1);
        gridPane.add(new Label("Red"), 0, 0);
        gridPane.add(slRed, 1, 0);

        Slider slGreen = new Slider(0, 1, 0.1);
        gridPane.add(new Label("Green"), 0, 1);
        gridPane.add(slGreen, 1, 1);

        Slider slBlue = new Slider(0, 1, 0.1);
        gridPane.add(new Label("Blue"), 0, 2);
        gridPane.add(slBlue, 1, 2);

        Slider slOpacity = new Slider(0, 1, 0.1);
        gridPane.add(new Label("Opacity"), 0, 3);
        gridPane.add(slOpacity, 1, 3);

        vBox.getChildren().add(text);
        vBox.getChildren().add(gridPane);

        slRed.valueProperty().addListener(e -> {
            text.setTextFill(new Color(slRed.getValue(), slGreen.getValue(), slBlue.getValue(), slOpacity.getValue()));
        });
        slGreen.valueProperty().addListener(e -> {
            text.setTextFill(new Color(slRed.getValue(), slGreen.getValue(), slBlue.getValue(), slOpacity.getValue()));
        });
        slBlue.valueProperty().addListener(e -> {
            text.setTextFill(new Color(slRed.getValue(), slGreen.getValue(), slBlue.getValue(), slOpacity.getValue()));
        });
        slOpacity.valueProperty().addListener(e -> {
            text.setTextFill(new Color(slRed.getValue(), slGreen.getValue(), slBlue.getValue(), slOpacity.getValue()));
        });

        Scene scene = new Scene(vBox);
        primaryStage.setTitle("TestUseSlider");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}
}
