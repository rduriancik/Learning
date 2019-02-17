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
 * Created by robert on 11.01.16.
 */
public class TestHexEditor extends Application {
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
            taEditor.setText(getHexData(tfFile.getText()));
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
        primaryStage.setTitle("TestHexEditor");
        primaryStage.show();
    }

    /**
     * Converts the data from a file into hex numbers and returns in a string.
     *
     * @param path a path to a source file
     * @return a string with hex data
     */
    private String getHexData(String path) {
        File source = new File(path);

        if (!source.exists()) {
            return "File doesn't exist.";
        }

        if (source.isDirectory()) {
            return "File cannot be a directory.";
        }

        String hexData = "";

        try (DataInputStream input =
                     new DataInputStream(new BufferedInputStream(new FileInputStream(source)))) {
            while (input.available() > 0) {
                byte data = input.readByte();
                String hex = Integer.toHexString(data);
                while (hex.length() < 2) {
                    hex = "0" + hex;
                }
                hexData += hex;
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return hexData;
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

        if (target.isDirectory()) {
            return false;
        }

        try (DataOutputStream output =
                     new DataOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            if (!target.exists()) {
                target.createNewFile();
            }
            output.write(getByteVersion(data));
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return true;
    }

    /**
     * Converts a string hex data in a bytes array.
     *
     * @param hexData string hex data
     * @return an array of bytes
     */
    private byte[] getByteVersion(String hexData) {
        String[] data = splitStringByLength(hexData, 2);
        byte[] bData = new byte[data.length];

        for (int i = 0; i < bData.length; i++) {
            bData[i] = hexToByte(data[i]);
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
            list.add(text.substring(index, Math.min(index + lenght, text.length())));
            index += lenght;
        }

        return list.toArray(new String[0]);
    }

    /**
     * Converts a string hex number in the decimal number.
     *
     * @param hex a string representation of the hex data
     * @return decimal number
     */
    private byte hexToByte(String hex) {
        byte numb = 0;
        char character;
        for (int i = 0; i < hex.length(); i++) {
            character = hex.charAt(hex.length() - 1 - i);

            // Checks if each character of the modified file is a bit value
            if (!isHex(character)) {
                throw new IllegalArgumentException("Illegal modification in the file.");
            }

            character = Character.toLowerCase(character);
            byte temp = 0;
            switch (character) {
                case 'a':
                    temp = 10;
                    break;
                case 'b':
                    temp = 11;
                    break;
                case 'c':
                    temp = 12;
                    break;
                case 'd':
                    temp = 13;
                    break;
                case 'e':
                    temp = 14;
                    break;
                case 'f':
                    temp = 15;
                    break;
                default:
                    temp = Byte.parseByte(Character.toString(character));

            }
            numb += temp
                    * Math.pow(16, i);
        }

        return numb;
    }

    /**
     * Returns true if the character is hexadecimal number, false if not.
     *
     * @param ch character representation of a hex number
     * @return true if ch is hex, false if not
     */
    private boolean isHex(char ch) {
        ch = Character.toLowerCase(ch);
        return Character.isDigit(ch) || ch == 'a' || ch == 'b' || ch == 'c'
                || ch == 'd' || ch == 'e' || ch == 'f';
    }
}
