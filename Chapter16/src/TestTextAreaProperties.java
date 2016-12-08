import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.geometry.Pos;

/**
 * Created by robkio on 17.10.2015.
 */
public class TestTextAreaProperties extends Application {
    @Override
    public void start(Stage primaryStage){
        BorderPane pane = new BorderPane();
        TextArea textArea = new TextArea();
        CheckBox chkEditable = new CheckBox("Editable");
        chkEditable.setSelected(true);
        CheckBox chkWrap = new CheckBox("Wrap");
        HBox hBoxForCheckBoxies = new HBox(10);
        hBoxForCheckBoxies.getChildren().addAll(chkEditable, chkWrap);
        hBoxForCheckBoxies.setAlignment(Pos.CENTER);
        pane.setCenter(textArea);
        pane.setBottom(hBoxForCheckBoxies);

        chkEditable.setOnAction(e -> {
            if(chkEditable.isSelected()){
                textArea.setEditable(true);
            } else {
                textArea.setEditable(false);
            }
        });

        chkWrap.setOnAction(e -> {
            if(chkWrap.isSelected()){
                textArea.setWrapText(true);
            } else {
                textArea.setWrapText(false);
            }
        });

        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestTextAreaProperties");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}
}
