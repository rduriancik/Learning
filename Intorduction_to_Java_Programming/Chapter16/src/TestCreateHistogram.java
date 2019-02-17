import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by robkio on 14.10.2015.
 */
public class TestCreateHistogram extends Application {
    @Override
    public void start(Stage primaryStage){
        HBox hBox = new HBox(5);
        hBox.setStyle("-fx-border-color: black");
        hBox.getChildren().add(new Label("Filename:"));
        TextField tfFileName = new TextField();
        tfFileName.setPrefColumnCount(40);
        Button btView = new Button("View");
        hBox.getChildren().addAll(tfFileName, btView);

        Histogram histogram = new Histogram();
        histogram.setPadding(new Insets(0, 10, 0, 0));

        BorderPane pane = new BorderPane();
        pane.setCenter(histogram);
        pane.setBottom(hBox);
        pane.setPrefHeight(650);

        btView.setOnAction(e -> {
            File file = new File(tfFileName.getText());
            String text = getText(file);
            int[] counts = getCount(text);
            histogram.setCounts(counts);
        });

        tfFileName.setOnAction(e -> {
            File file = new File(tfFileName.getText());
            String text = getText(file);
            int[] counts = getCount(text);
            histogram.setCounts(counts);
        });

        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestLetters'Histogram");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}

    public String getText(File file){
        try(Scanner input = new Scanner(file)){
            String text = "";
            while(input.hasNext()){
                text += input.nextLine() + " ";
            }
            return text;
        }
        catch(FileNotFoundException e){
            return "File not found.";
        }
    }

    public int[] getCount(String text){
        int[] count = new int[26];
        text = text.toUpperCase();
        for(int i = 0; i < count.length; i++){
            char character = (char)(65 + i);
            for(int j = 0; j < text.length(); j++){
                if(text.charAt(j) == character){
                    count[i]++;
                }
            }
        }
        return count;
    }
}

class Histogram extends Pane{
    private int[] counts = new int[26];
    private Rectangle[] rectangles = new Rectangle[26];
    private Text[] text = new Text[26];

    public Histogram(){
        makeHistogram();
    }

    private int getMaxCount(){
        int max = 0;
        for(int i = 0; i < counts.length; i++){
            if(counts[i] > max){
                max = counts[i];
            }
        }
        return max;
    }

    private void makeHistogram(){
        getChildren().clear();
        double lineY = 10 + (getMaxCount() * 5);
        Line line = new Line(10, lineY, 655, lineY);
        line.setStrokeWidth(5);
        getChildren().add(line);

        for(int i = 0; i < rectangles.length; i++){
            rectangles[i] = new Rectangle(10 + (25 * i), lineY - (counts[i] * 5), 20, counts[i] * 5);
            rectangles[i].setFill(Color.WHITE);
            rectangles[i].setStroke(Color.BLACK);
            char character = (char)((int) 65 + i);
            text[i] = new Text(15 + (25 * i), lineY + 20, Character.toString(character));
        }
        getChildren().addAll(rectangles);
        getChildren().addAll(text);
    }

    public void setCounts(int[] counts){
        if(counts.length == 26) {
            this.counts = counts;
            makeHistogram();
        }
    }
}