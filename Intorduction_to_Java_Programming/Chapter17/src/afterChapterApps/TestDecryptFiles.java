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

/**
 * Created by robert on 02.01.16.
 */
public class TestDecryptFiles extends Application {

    private static final byte codeNumb = TestEncryptFiles.getCodeNumber();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a a main BorderPane
        BorderPane pane = new BorderPane();

        // Create an info label and place it in the main pane
        Label info = new Label("Decode file");
        pane.setTop(info);
        BorderPane.setAlignment(info, Pos.CENTER);
        BorderPane.setMargin(info, new Insets(5));

        // Create the textFields with labels for input place them into a gridPane
        TextField tfSource = new TextField();
        tfSource.setPrefColumnCount(10);
        tfSource.setAlignment(Pos.CENTER_LEFT);

        TextField tfTarget = new TextField();
        tfTarget.setPrefColumnCount(10);
        tfTarget.setAlignment(Pos.CENTER_LEFT);

        GridPane inputGPane = new GridPane();
        inputGPane.setHgap(5);
        inputGPane.setVgap(5);
        inputGPane.setPadding(new Insets(5));

        inputGPane.add(new Label("Enter a source file name:"), 0, 0);
        inputGPane.add(tfSource, 1, 0);
        inputGPane.add(new Label("Enter a target file name:"), 0, 1);
        inputGPane.add(tfTarget, 1, 1);

        pane.setCenter(inputGPane); // Place the inputGPane into the main pane

        // Create a button for starting the operation
        Button btEncode = new Button("Decode");
        pane.setBottom(btEncode); // Place the button into the main pane
        BorderPane.setAlignment(btEncode, Pos.CENTER);
        BorderPane.setMargin(btEncode, new Insets(5));

        // Create an action event for the btEncode
        btEncode.setOnAction(e -> {
            info.setText(encodeFile(tfSource.getText(), tfTarget.getText()));
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TestDecryptFiles");
        primaryStage.show();
    }

    /**
     * This method decodes file and returns a string message about the operation
     *
     * @param sourcePath a path to a source file
     * @param targetPath a path to a output file
     * @return a string message
     */
    private String encodeFile(String sourcePath, String targetPath) {
        File source = new File(sourcePath);

        if (!source.exists()) {
            return "Source file doesn't exist";
        }

        if (source.isDirectory()) {
            return "Source file cannot be a directory";
        }

        File target = new File(targetPath);

        if (target.exists()) {
            return "Output file already exist";
        }

        if (target.isDirectory()) {
            return "Output file cannot be a directory";
        }

        try (
                RandomAccessFile input = new RandomAccessFile(source, "r");
                RandomAccessFile output = new RandomAccessFile(target, "rw")
        ) {
            for (int i = 0; i < input.length(); i++) {
                output.writeByte(input.readByte() - codeNumb);
            }
        } catch (EOFException ex) {
            return "Decoding was successful";
        } catch (IOException ex) {
            return "Decoding wasn't successful";
        }


        return "Decoding was successful";
    }
}
