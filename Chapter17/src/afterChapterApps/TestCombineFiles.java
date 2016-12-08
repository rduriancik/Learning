package afterChapterApps;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;

/**
 * Created by robert on 01.01.16.
 */
public class TestCombineFiles extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a main BorderPane
        BorderPane pane = new BorderPane();

        // Create a info label
        Label info = new Label("If you split a file named temp.txt into 3 smaller files," +
                " the three smaller files are temp.txt.1, temp.txt.2, temp.txt.3.");
        info.setWrapText(true);
        // Place the label info into pane
        pane.setTop(info);
        BorderPane.setAlignment(info, Pos.CENTER);
        BorderPane.setMargin(info, new Insets(5));

        // Create a gridPane for TextFields
        GridPane inputGridPane = new GridPane();
        inputGridPane.setHgap(5);
        inputGridPane.setVgap(5);
        inputGridPane.setPadding(new Insets(5));

        // Create the textFields with labels for input and place them in the inputGridPane
        inputGridPane.add(new Label("Enter a file:"), 0, 0);
        TextField tfFile = new TextField();
        tfFile.setPrefColumnCount(10);
        tfFile.setAlignment(Pos.CENTER_LEFT);
        inputGridPane.add(tfFile, 1, 0);

        inputGridPane.add(new Label("Specify the number of smaller files:"), 0, 1);
        TextField tfNumberOfFiles = new TextField();
        tfNumberOfFiles.setPrefColumnCount(10);
        tfNumberOfFiles.setAlignment(Pos.CENTER_LEFT);
        inputGridPane.add(tfNumberOfFiles, 1, 1);

        inputGridPane.add(new Label("Enter a path to a directory with files"), 0, 2);
        TextField tfDirectory = new TextField();
        tfDirectory.setPrefColumnCount(10);
        tfDirectory.setAlignment(Pos.CENTER_LEFT);
        inputGridPane.add(tfDirectory, 1, 2);

        // Create a button for starting the operation and place it in the pane
        Button btStart = new Button("Start");
        pane.setBottom(btStart);
        BorderPane.setAlignment(btStart, Pos.CENTER);
        BorderPane.setMargin(btStart, new Insets(5));

        // Place the inputGridPane into the pane
        pane.setCenter(inputGridPane);

        // Create an action event for btStart
        btStart.setOnAction(e -> {
            info.setText(combineFiles(tfFile.getText(), tfDirectory.getText(),
                    Integer.parseInt(tfNumberOfFiles.getText())));
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 395, 175);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TestCombineFiles");
        primaryStage.show();
    }

    private String combineFiles(String fileName, String directoryPath, int smallerFilesNumb) {
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            return "Source directory doesn't exist.";
        }

        if (directory.isFile()) {
            return "Source directory cannot be a file.";
        }

        if (smallerFilesNumb < 0) {
            return "Number of smaller files cannot be less than 0.";
        }

        File target = new File(directoryPath + "/" + fileName);

        try (
                BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(target))
        ) {
            for (int i = 1; i <= smallerFilesNumb; i++) {
                File temp = new File(target + String.format(".%d", i));

                if (!temp.exists()) {
                    return "File " + temp.getName() + " doesn't exist.";
                }

                BufferedInputStream input = new BufferedInputStream(new FileInputStream(temp));

                int read = input.read();
                while (read != -1) {
                    output.write(read);
                    read = input.read();
                }

                input.close();
            }
        } catch (IOException ex) {
            return "Combination wasn't successful.";
        }

        return "Combination was successful.";
    }
}
