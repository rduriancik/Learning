import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.util.List;

/**
 * Created by robkio on 17.10.2015.
 */
public class TestSelecFont extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        // Top pane
        HBox hBox = new HBox(5);
        ComboBox<String> cboFonts = new ComboBox<>();
        List<String> fonts = Font.getFamilies();
        cboFonts.getItems().addAll(fonts);
        ComboBox<Integer> cboFontSize = new ComboBox<>();
        for (int i = 1; i <= 100; i++) {
            cboFontSize.getItems().add(i);
        }
        cboFonts.setValue("Book Antiqua");
        cboFontSize.setValue(48);
        hBox.getChildren().add(new Label("Font Name"));
        hBox.getChildren().add(cboFonts);
        hBox.getChildren().add(new Label("Font Size"));
        hBox.getChildren().add(cboFontSize);
        hBox.setAlignment(Pos.CENTER);

        pane.setTop(hBox);

        // Bottom pane
        HBox hBox1 = new HBox(5);
        CheckBox chkBold = new CheckBox("Bold");
        CheckBox chkItalic = new CheckBox("Italic");
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().add(chkBold);
        hBox1.getChildren().add(chkItalic);
        pane.setBottom(hBox1);

        // Center pane
        StackPane stackPane = new StackPane();
        Text text = new Text("Programming is fun");
        text.setFont(Font.font(cboFonts.getValue(), cboFontSize.getValue()));
        stackPane.getChildren().add(text);
        stackPane.setPadding(new Insets(10));
        pane.setCenter(stackPane);

        cboFonts.setOnAction(e -> {
            text.setFont(Font.font(cboFonts.getValue(), cboFontSize.getValue()));
            if(chkBold.isSelected() && chkItalic.isSelected()){
                text.setFont(Font.font(cboFonts.getValue(), FontWeight.BOLD,
                        FontPosture.ITALIC, cboFontSize.getValue()));
            } else if(chkBold.isSelected()){
                text.setFont(Font.font(cboFonts.getValue(), FontWeight.BOLD, cboFontSize.getValue()));
            } else if(chkItalic.isSelected()) {
                text.setFont(Font.font(cboFonts.getValue(), FontPosture.ITALIC, cboFontSize.getValue()));
            }
        });
        cboFontSize.setOnAction(e -> {
            text.setFont(Font.font(cboFonts.getValue(), cboFontSize.getValue()));
            if(chkBold.isSelected() && chkItalic.isSelected()){
                text.setFont(Font.font(cboFonts.getValue(), FontWeight.BOLD,
                        FontPosture.ITALIC, cboFontSize.getValue()));
            } else if(chkBold.isSelected()){
                text.setFont(Font.font(cboFonts.getValue(), FontWeight.BOLD, cboFontSize.getValue()));
            } else if(chkItalic.isSelected()) {
                text.setFont(Font.font(cboFonts.getValue(), FontPosture.ITALIC, cboFontSize.getValue()));
            }
        });
        chkBold.setOnAction(e -> {
            if( chkBold.isSelected() && chkItalic.isSelected()){
                text.setFont(Font.font(cboFonts.getValue(), FontWeight.BOLD,
                        FontPosture.ITALIC, cboFontSize.getValue()));
            } else if(chkBold.isSelected()){
                text.setFont(Font.font(cboFonts.getValue(), FontWeight.BOLD, cboFontSize.getValue()));
            } else if(chkItalic.isSelected()) {
                text.setFont(Font.font(cboFonts.getValue(), FontPosture.ITALIC, cboFontSize.getValue()));
            } else {
                text.setFont(Font.font(cboFonts.getValue(), FontWeight.NORMAL, cboFontSize.getValue()));
            }
        });
        chkItalic.setOnAction(e -> {
            if( chkBold.isSelected() && chkItalic.isSelected()){
                text.setFont(Font.font(cboFonts.getValue(), FontWeight.BOLD,
                        FontPosture.ITALIC, cboFontSize.getValue()));
            } else if(chkItalic.isSelected()){
                text.setFont(Font.font(cboFonts.getValue(), FontPosture.ITALIC, cboFontSize.getValue()));
            } else if(chkBold.isSelected()) {
                text.setFont(Font.font(cboFonts.getValue(), FontWeight.BOLD, cboFontSize.getValue()));
            } else {
                text.setFont(Font.font(cboFonts.getValue(), FontPosture.REGULAR, cboFontSize.getValue()));
            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestSelectFont");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}
}
