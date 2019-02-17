import javafx.application.Application;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

/**
 * Created by robkio on 25.9.2015.
 */
public class ListViewDemo  extends Application {
    // Declare an array of Strings for flag titles
    private String[] flagTitles = {"Canada", "United Kingdom", "United States of America"};

    // Declare an ImageView array for the national flags of 9 countries
    private ImageView[] ImageViews = {new ImageView("http://www.cs.armstrong.edu/liang/common/image/ca.gif"),
    new ImageView("http://www.cs.armstrong.edu/liang/common/image/uk.gif"),
    new ImageView("http://www.cs.armstrong.edu/liang/intro7e/book/image/us.gif")};

    @Override
    public void start(Stage primaryStage){
        ListView<String> lv = new ListView<>(FXCollections.observableArrayList(flagTitles));
        lv.setPrefSize(200, 400);
        lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Create a pane to hold image views
        FlowPane imagePane = new FlowPane(10, 10);
        BorderPane pane = new BorderPane();
        pane.setLeft(new ScrollPane(lv));
        pane.setCenter(imagePane);

        lv.getSelectionModel().selectedItemProperty().addListener(
                ov -> {
                    imagePane.getChildren().clear();
                    for(Integer i: lv.getSelectionModel().getSelectedIndices()){
                        imagePane.getChildren().add(ImageViews[i]);
                    }
                }
        );

        // Create a scene and pace it in the stage
        Scene scene = new Scene(pane, 800, 200);
        primaryStage.setTitle("ListViewDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}
}
