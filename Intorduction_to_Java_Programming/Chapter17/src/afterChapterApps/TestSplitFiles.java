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

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Created by robert on 31.12.15.
 */
public class TestSplitFiles extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a main BorderPane
        BorderPane pane = new BorderPane();

        // Create a top Label with information and set it in the top of the BorderPane
        Label info = new Label("When you split a file named temp.txt into three smaller files, " +
                "the three smaller files are temp.txt.1, temp.txt.2, temp.txt.3.");
        pane.setTop(info);
        info.setWrapText(true); // multi-line text
        BorderPane.setAlignment(info, Pos.CENTER);
        BorderPane.setMargin(info, new Insets(5));

        // Create a GridPane for input
        GridPane inputGridPane = new GridPane();
        inputGridPane.setHgap(5);
        inputGridPane.setVgap(5);
        inputGridPane.setPadding(new Insets(5));

        // Create a label and a textField for source file
        inputGridPane.add(new Label("Source file:"), 0, 0);
        TextField tfSource = new TextField();
        tfSource.setPrefColumnCount(10);
        tfSource.setAlignment(Pos.CENTER_LEFT);
        inputGridPane.add(tfSource, 1, 0);

        // Create a label and a textField for the count of smaller files
        inputGridPane.add(new Label("Number of smaller files:"), 0, 1);
        TextField tfSmallerFilesNumb = new TextField();
        tfSmallerFilesNumb.setPrefColumnCount(2);
        tfSmallerFilesNumb.setAlignment(Pos.CENTER_LEFT);
        inputGridPane.add(tfSmallerFilesNumb, 1, 1);

        // Create a label and a textField for target directory
        inputGridPane.add(new Label("Target directory:"), 0, 2);
        TextField tfTarget = new TextField();
        tfTarget.setPrefColumnCount(10);
        tfTarget.setAlignment(Pos.CENTER_LEFT);
        inputGridPane.add(tfTarget, 1, 2);

        // Set inputGridPane in the main BorderPane
        pane.setCenter(inputGridPane);

        // Create a bottom split button and set it in the bottom of the BorderPane
        Button btSplit = new Button("Split");
        pane.setBottom(btSplit);
        BorderPane.setAlignment(btSplit, Pos.CENTER);
        BorderPane.setMargin(btSplit, new Insets(5));

        // Create a button action event
        btSplit.setOnAction(e -> {
            info.setText(splitFiles(tfSource.getText(),
                    tfTarget.getText(), Integer.parseInt(tfSmallerFilesNumb.getText())));
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 345, 190);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TestSplitFiles");
        primaryStage.show();
    }

    /**
     * Splits a given file into smallerFilesNumb smaller files
     *
     * @param sourceFilePath      a path to a source file
     * @param targetDirectoryPath a path to a target directory
     * @param smallerFilesNumb    number of smaller files
     * @return a string message
     */
    private String splitFiles(String sourceFilePath, String targetDirectoryPath, int smallerFilesNumb) {
        File source = new File(sourceFilePath);

        if (!source.exists()) {
            return "Source file does not exist.";
        }

        if (source.isDirectory()) {
            return "Source file cannot be a directory.";
        }

        if (smallerFilesNumb < 0) {
            return "Number of smaller files cannot be less than 0";
        }

        File target = new File(targetDirectoryPath);

        if (target.isFile()) {
            return "Target directory cannot be a file.";
        }

        try (
                RandomAccessFile input = new RandomAccessFile(source, "r")
        ) {
            Files.createDirectories(Paths.get(target.toString()));

            long bytesForFile = (long) Math.ceil(source.length() / (double) smallerFilesNumb);

            for (int i = 1; i <= smallerFilesNumb; i++) {
                File temp = new File(target.getCanonicalPath() + "/" +
                        source.getName() + String.format(".%d", i));
                RandomAccessFile output = new RandomAccessFile(temp, "rw");
                output.setLength(0);

                for (int j = 0; j < bytesForFile; j++) {
                    output.writeByte(input.readByte());
                }

                output.close();
            }

        } catch (EOFException ex) {
            return "Splitting was successful.";
        } catch (IOException ex) {
            return "Splitting was not successful." + ex;
        }

        return "Splitting was successful.";
    }
}
