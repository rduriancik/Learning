import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

/**
 * Created by robkio on 2.10.2015.
 */
public class TestMilesKilometersConverter extends Application {
    private final double RATIO = 1.602307322544464;
    @Override
    public void start(Stage primaryStage){
        // Create text fields
        TextField miles = new TextField();
        miles.setAlignment(Pos.CENTER_RIGHT);
        TextField kilometers = new TextField();
        kilometers.setAlignment(Pos.CENTER_RIGHT);

        // Create a gridPane to hold text fields and labels
        GridPane pane = new GridPane();
        pane.add(new Label("Mile"), 0, 0);
        pane.add(miles, 1, 0);
        pane.add(new Label("Kilometer"), 0, 1);
        pane.add(kilometers, 1, 1);
        pane.setPadding(new Insets(5));
        pane.setHgap(5);

        // Create action events for text fields
        miles.setOnMousePressed(e -> miles.setText(""));
        miles.setOnAction(e -> {
            double temp = Double.valueOf(miles.getText()) * RATIO; // Convert miles to kilometers
            kilometers.setText(temp + "");
        });

        kilometers.setOnMousePressed(e -> kilometers.setText(""));
        kilometers.setOnAction(e -> {
            double temp = Double.valueOf(kilometers.getText()) / RATIO; // Converts kilometers to miles
            kilometers.setText("");
            miles.setText(temp + "");
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestMilesKilometersConverter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}
}
