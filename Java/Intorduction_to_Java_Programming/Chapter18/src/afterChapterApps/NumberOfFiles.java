package afterChapterApps;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by rduriancik on 2/4/16.
 */
public class NumberOfFiles extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        HBox hBox = new HBox(5);
        TextField tfDirectory = new TextField();
        tfDirectory.setPrefColumnCount(20);
        tfDirectory.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().addAll(new Label("Enter a directory: "), tfDirectory);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10));

        pane.setTop(hBox);

        Label lbInfo = new Label("Count the number of files in a directory.");
        pane.setBottom(lbInfo);
        BorderPane.setAlignment(lbInfo, Pos.CENTER);
        BorderPane.setMargin(lbInfo, new Insets(0, 10, 10, 10));

        Button btStart = new Button("Start");
        pane.setCenter(btStart);
        BorderPane.setAlignment(btStart, Pos.CENTER);
        BorderPane.setMargin(btStart, new Insets(0, 10, 10, 10));

        btStart.setOnAction(e -> {
            lbInfo.setText(countFiles(tfDirectory.getText()));
        });

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("NumberOfFiles");
        primaryStage.show();
    }

    private String countFiles(String directory) {
        if (directory == null || directory.isEmpty()) {
            return "Enter a directory.";
        }

        File directoryPath = new File(directory);

        if (!directoryPath.exists()) {
            return "Directory doesn't exists.";
        }

        if (directoryPath.isFile()) {
            return "Enter a path to a directory.";
        }


        int count = directoryPath.listFiles().length == 0 ? 0 : 1;

        for (File f : directoryPath.listFiles()) {
            if (f.isDirectory()) {
                count += Integer.parseInt(countFiles(f.toString()));
            }

            count++;
        }

        return Integer.toString(count);
    }
}
