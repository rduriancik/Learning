import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

/**
 * Created by robkio on 2.10.2015.
 */
public class TestConverNumbers extends Application {
    @Override
    public void start(Stage primaryStage){
        // Create a pane to hold objects
        GridPane pane = new GridPane();

        TextField tfDecimal = new TextField();
        tfDecimal.setAlignment(Pos.CENTER_RIGHT);
        TextField tfHex = new TextField();
        tfHex.setAlignment(Pos.CENTER_RIGHT);
        TextField tfBinary = new TextField();
        tfBinary.setAlignment(Pos.CENTER_RIGHT);

        pane.add(new Label("Decimal"), 0, 0);
        pane.add(tfDecimal, 1, 0);
        pane.add(new Label("Hex"), 0, 1);
        pane.add(tfHex, 1, 1);
        pane.add(new Label("Binary"), 0, 2);
        pane.add(tfBinary, 1, 2);
        pane.setPadding(new Insets(8));
        pane.setHgap(10);
        pane.setVgap(5);

        // Create action events for text fields
        tfDecimal.setOnMousePressed(e -> tfDecimal.setText(""));
        tfDecimal.setOnAction(e -> {
            int value = Integer.valueOf(tfDecimal.getText());
            tfBinary.setText(Integer.toBinaryString(value));
            tfHex.setText(Integer.toHexString(value));
        });

        tfHex.setOnMousePressed(e -> tfHex.setText(""));
        tfHex.setOnAction(e -> {
            String value = tfHex.getText();
            tfDecimal.setText(String.valueOf(binaryToInt(hexToBinary(value))));
            tfBinary.setText(hexToBinary(value));
        });

        tfBinary.setOnMousePressed(e -> tfBinary.setText(""));
        tfBinary.setOnAction(e -> {
            String value = tfBinary.getText();
            tfDecimal.setText(String.valueOf(binaryToInt(value)));
            tfHex.setText(Integer.toHexString(binaryToInt(value)));
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestConvertNumbers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}

    /** Return integer value from a binary number in a String format
     * @param binary number in String format
     * @return int
     */
    public static int binaryToInt(String binary){
        double num = 0;
        int exponent = 0;
        for(int i = binary.length() - 1; i >= 0; i--){
            double temp = Integer.valueOf(Character.toString(binary.charAt(i))) *
                    Math.pow( 2, exponent);
            num += temp;
            exponent++;
        }
        return (int) num;
    }

    /** Return a binary value in a String format from a hexadecimal value
     * @param hex number in String format
     * @return String
     */
    public static String hexToBinary(String hex) {
        String binary = "";
        for (int i = 0; i < hex.length(); i++) {
            switch (hex.charAt(i)) {
                case '0':
                    binary += "0000";
                    break;
                case '1':
                    binary += "0001";
                    break;
                case '2':
                    binary += "0010";
                    break;
                case '3':
                    binary += "0011";
                    break;
                case '4':
                    binary += "0100";
                    break;
                case '5':
                    binary += "0101";
                    break;
                case '6':
                    binary += "0110";
                    break;
                case '7':
                    binary += "0111";
                    break;
                case '8':
                    binary += "1000";
                    break;
                case '9':
                    binary += "1001";
                    break;
                case 'A':
                    binary += "1010";
                    break;
                case 'B':
                    binary += "1011";
                    break;
                case 'C':
                    binary += "1100";
                    break;
                case 'D':
                    binary += "1101";
                    break;
                case 'E':
                    binary += "1110";
                    break;
                case 'F':
                    binary += "1111";
                    break;
            }
        }
        return binary;
    }
}
