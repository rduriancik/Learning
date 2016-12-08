import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * Created by robkio on 3.10.2015.
 */
public class TestDemonstrateTextField extends Application {
    @Override
    public void start(Stage primaryStage){
        // Create a HBox pane to hold text field an its label
        HBox hBoxForTf = new HBox(5);
        TextField tfJavaFX = new TextField("JavaFX");
        tfJavaFX.setEditable(false);
        tfJavaFX.setAlignment(Pos.CENTER);
        hBoxForTf.getChildren().addAll(new Label("Text Field"), tfJavaFX);
        hBoxForTf.setAlignment(Pos.CENTER);
        hBoxForTf.setPadding(new Insets(5));

        // Create a HBox pane to hold radio buttons and column size text field
        HBox hBoxForControls = new HBox(5);
        ToggleGroup group = new ToggleGroup(); // Create a toggle group for radio buttons
        RadioButton rbLeft = new RadioButton("Left");
        rbLeft.setToggleGroup(group);
        RadioButton rbCenter = new RadioButton("Center");
        rbCenter.setToggleGroup(group);
        rbCenter.setSelected(true);
        RadioButton rbRight = new RadioButton("Right");
        rbRight.setToggleGroup(group);
        TextField tfColumnSize = new TextField();
        tfColumnSize.setPrefColumnCount(3);
        tfColumnSize.setAlignment(Pos.CENTER_RIGHT);
        hBoxForControls.getChildren().addAll(rbLeft, rbCenter, rbRight);
        hBoxForControls.getChildren().addAll(new Label("Column Size"), tfColumnSize);
        hBoxForControls.setAlignment(Pos.CENTER);
        hBoxForControls.setPadding(new Insets(5));

        // Create a border pane
        BorderPane pane = new BorderPane();
        pane.setCenter(hBoxForTf);
        pane.setBottom(hBoxForControls);

        // Create action events
        rbLeft.setOnAction(e -> tfJavaFX.setAlignment(Pos.CENTER_LEFT));
        rbCenter.setOnAction(e -> tfJavaFX.setAlignment(Pos.CENTER));
        rbRight.setOnAction(e -> tfJavaFX.setAlignment(Pos.CENTER_RIGHT));

        tfColumnSize.setOnAction(e -> {
            tfJavaFX.setPrefColumnCount(Integer.valueOf(tfColumnSize.getText()));
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestDemonstrateTextField");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){ launch(args);}
}
