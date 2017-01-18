import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * Created by robkio on 23.9.2015.
 */
public class TextFieldDemo extends RadioButtonDemo {
    @Override
    protected BorderPane getPane(){
        BorderPane pane = super.getPane();

        BorderPane paneForTextField = new BorderPane();
        paneForTextField.setPadding(new Insets(5));
        paneForTextField.setStyle("-fx-border-color: green");
        paneForTextField.setLeft(new Label("Enter a new message: "));

        TextField tf = new TextField();
        tf.setAlignment(Pos.BOTTOM_RIGHT);
        paneForTextField.setCenter(tf);
        pane.setTop(paneForTextField);

        tf.setOnAction(e -> text.setText(tf.getText()));

        return pane;
    }

    public static void main(String[] args){launch(args);}
}
