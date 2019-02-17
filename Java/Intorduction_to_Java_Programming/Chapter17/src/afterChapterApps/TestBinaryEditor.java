package afterChapterApps;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 07.01.16.
 */
public class TestBinaryEditor extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a main BorderPane
        BorderPane pane = new BorderPane();

        // Create a top HBox for input
        HBox topHBox = new HBox(5);
        topHBox.setAlignment(Pos.CENTER);
        topHBox.setPadding(new Insets(5));

        topHBox.getChildren().add(new Label("Enter a file:"));

        TextField tfFile = new TextField();
        tfFile.setPrefColumnCount(20);
        tfFile.setAlignment(Pos.CENTER_LEFT);
        topHBox.getChildren().addAll(tfFile);
        // Add the topHBox into the main pane
        pane.setTop(topHBox);

        // Create a textArea for printing out a binary data
        TextArea taEditor = new TextArea();
        taEditor.setWrapText(true);
        // Add the taEditor into the main pane and set its position
        pane.setCenter(taEditor);
        BorderPane.setAlignment(taEditor, Pos.CENTER);
        BorderPane.setMargin(taEditor, new Insets(5));

        // Create a save button and place it into a main pane
        Button btSave = new Button("Save the change");
        pane.setBottom(btSave);
        BorderPane.setAlignment(btSave, Pos.CENTER);
        BorderPane.setMargin(btSave, new Insets(5));

        // Create an action event for the tfFile
        tfFile.setOnAction(e -> {
            taEditor.setText(getBinaryData(tfFile.getText()));
        });

        // Create an action event for the btSave
        btSave.setOnAction(e -> {
            if (tfFile.getText().isEmpty()) {
                tfFile.setText("Enter a file name...");
            }

            if (saveChanges(tfFile.getText(), taEditor.getText())) {
                btSave.setText("Successful!");
            }
        });

        // Add a listener to the taEditor
        taEditor.textProperty().addListener(e -> btSave.setText("Save the change"));

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TestBinaryEditor");
        primaryStage.show();
    }

    /**
     * Converts the data from a file into binary numbers and returns in a string.
     *
     * @param path a path to a source file
     * @return a string with binary data
     */
    private String getBinaryData(String path) {
        File source = new File(path);

        if (!source.exists()) {
            return "File doesn't exist.";
        }

        if (source.isDirectory()) {
            return "File cannot be a directory.";
        }

        String binaryData = "";

        try (DataInputStream input =
                     new DataInputStream(new BufferedInputStream(new FileInputStream(source)))) {
            while (input.available() > 0) {
                byte data = input.readByte();
                String binary = Integer.toBinaryString(data);
                while (binary.length() != 8) {
                    binary = "0" + binary;
                }
                binaryData += binary;
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return binaryData;
    }

    /**
     * Saves the changes from the editor.
     *
     * @param path a path to a target file
     * @param data the modified data
     * @return true if operation was successful, false if not
     */
    private boolean saveChanges(String path, String data) {
        File target = new File(path);

        if (!target.exists()) {
            return false;
        }

        if (target.isDirectory()) {
            return false;
        }

        try (DataOutputStream output =
                     new DataOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            output.write(getByteVersion(data));
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return true;
    }

    /**
     * Converts a string binary data in a bytes array.
     *
     * @param binaryData string binary data
     * @return an array of bytes
     */
    private byte[] getByteVersion(String binaryData) {
        String[] data = splitStringByLength(binaryData, 8);
        byte[] bData = new byte[data.length];

        for (int i = 0; i < bData.length; i++) {
            bData[i] = binaryToByte(data[i]);
        }

        return bData;
    }

    /**
     * Splits a string text into a substring array with specified length of each substring element.
     *
     * @param text   string to split
     * @param lenght of a substring element
     * @return array of substrings with specified length
     */
    private String[] splitStringByLength(String text, int lenght) {
        List<String> list = new ArrayList<>();

        int index = 0;
        while (index < text.length()) {
            list.add(text.substring(index, Math.min(index + 8, text.length())));
            index += 8;
        }

        return list.toArray(new String[0]);
    }

    /**
     * Converts a string binary number in the decimal number.
     *
     * @param binary a string representation of the binary data
     * @return decimal number
     */
    private byte binaryToByte(String binary) {
        byte numb = 0;
        char character;
        for (int i = 0; i < binary.length(); i++) {
            character = binary.charAt(binary.length() - 1 - i);

            // Checks if each character of the modified file is a bit value
            if (!isBit(character)) {
                throw new IllegalArgumentException("Illegal modification in the file.");
            }

            numb += Integer.parseInt(Character.toString(character))
                    * Math.pow(2, i);
        }

        return numb;
    }

    /**
     * Returns true if the character is 0 or 1, false if not.
     *
     * @param ch character representation of a bit
     * @return true if ch is bit, false if not
     */
    private boolean isBit(char ch) {
        return ch == '0' || ch == '1';
    }
}
