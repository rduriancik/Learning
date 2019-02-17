import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

/**
 * Created by robkio on 13.10.2015.
 */
public class TestTextViewer extends Application {
    @Override
    public void start(Stage primaryStage){
        TextArea textArea = new TextArea();

        TextField tfFileAddress = new TextField();
        tfFileAddress.setPrefColumnCount(35);
        Button btView = new Button("View");
        HBox hBox = new HBox(2);
        hBox.getChildren().add(new Label("Filename:"));
        hBox.getChildren().addAll(tfFileAddress, btView);

        btView.setOnAction(e -> {
            String text = getText(new File(tfFileAddress.getText()));
            textArea.setText(text);
        });

        BorderPane pane = new BorderPane();
        pane.setCenter(textArea);
        pane.setBottom(hBox);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestTextViewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}

    public String getText(File file){
        String text = "";

        try(Scanner input = new Scanner(file)){
            while(input.hasNext()){
                text += input.nextLine() + "\n";
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File doesn't exist");
        }

        return text;
    }
}
