package afterChapterApps.bounceBallExtra;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by robert on 11.4.2016.
 */
public class MultipleBounceBall extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MultipleBallPane ballPane = new MultipleBallPane();
        ballPane.setStyle("-fx-border-color: yellow");

        Button btAdd = new Button("+");
        Button btSubtract = new Button("-");
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(btAdd, btSubtract);
        hBox.setAlignment(Pos.CENTER);

        // Add or remove a ball
        btAdd.setOnAction(e -> ballPane.add());
        btSubtract.setOnAction(e -> ballPane.subtract());

        // Use a scroll bar to control animation speed
        ScrollBar sbSpeed = new ScrollBar();
        sbSpeed.setMax(20);
        sbSpeed.setValue(10);
        ballPane.rateProperty().bind(sbSpeed.valueProperty());

        Button btSuspend = new Button("Suspend");
        Button btResume = new Button("Resume");
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(btSuspend, btResume);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(5));

        btResume.setOnAction(e -> ballPane.play());
        btSuspend.setOnAction(e -> ballPane.pause());

        BorderPane pane = new BorderPane();
        pane.setCenter(ballPane);
        pane.setBottom(hBox);
        pane.setTop(sbSpeed);
        pane.setRight(vBox);

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 500, 300);
        primaryStage.setTitle("MultipleBounceBall");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
