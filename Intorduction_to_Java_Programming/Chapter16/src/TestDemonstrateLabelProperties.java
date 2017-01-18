import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ContentDisplay;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

/**
 * Created by robkio on 18.10.2015.
 */
public class TestDemonstrateLabelProperties extends Application {
    @Override
    public void start(Stage primaryStage){
        BorderPane pane = new BorderPane();

        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        ComboBox<ContentDisplay> cboContentDisplay = new ComboBox<>();
        cboContentDisplay.getItems().addAll(ContentDisplay.TOP, ContentDisplay.CENTER, ContentDisplay.BOTTOM,
                ContentDisplay.LEFT, ContentDisplay.RIGHT);
        cboContentDisplay.setValue(ContentDisplay.LEFT);
        TextField tfTextGap = new TextField();
        hBox.getChildren().add(new Label("contentDisplay:"));
        hBox.getChildren().add(cboContentDisplay);
        hBox.getChildren().add(new Label("graphicTextGap"));
        hBox.getChildren().add(tfTextGap);

        pane.setTop(hBox);

        ImageView imgLabel = new ImageView("img.png");
        Label label = new Label("Grapes", imgLabel);
        label.setContentDisplay(cboContentDisplay.getValue());
        pane.setCenter(label);
        BorderPane.setMargin(label, new Insets(20));

        cboContentDisplay.setOnAction(e -> {
            label.setContentDisplay(cboContentDisplay.getValue());
        });

        tfTextGap.setOnAction(e -> {
            label.setGraphicTextGap(Double.parseDouble(tfTextGap.getText()));
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("DemonstrateLabelProperties");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}
}
