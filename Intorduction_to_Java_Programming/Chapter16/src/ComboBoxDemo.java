import javafx.application.Application;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * Created by robkio on 25.9.2015.
 */
public class ComboBoxDemo extends Application {
    // Declare an array of String for flag titles
    private String[] flagTitles = {"Canada", "United Kingdom", "United States of America"};

    // Declare an ImageView array for the national flags of 9 countries
    private ImageView[] flagImage = {new ImageView("http://www.cs.armstrong.edu/liang/common/image/ca.gif"),
    new ImageView("http://www.cs.armstrong.edu/liang/common/image/uk.gif"),
    new ImageView("http://www.cs.armstrong.edu/liang/intro7e/book/image/us.gif")};

    // Declare an array of strings for flag descriptions
    private String[] flagDescription = new String[9];

    // Declare abd create a description pane
    private DescriptionPane descriptionPane = new DescriptionPane();

    // Create a combo box for selecting countries
    private ComboBox<String> cbo = new ComboBox<>(); //flagTitles

    @Override
    public void start(Stage primaryStage){
        // Set text description
        flagDescription[0] = "The Canadian national flag ...";
        flagDescription[1] = "Description for UK ...";
        flagDescription[2] = "Description for US ...";

        // Set the first country (Canada) for display
        setDisplay(0);

        // Add combo box and decription pane to the border pane
        BorderPane pane = new BorderPane();

        BorderPane paneForComboBox = new BorderPane();
        paneForComboBox.setLeft(new Label("Select a country: "));
        paneForComboBox.setCenter(cbo);
        pane.setTop(paneForComboBox);
        cbo.setPrefWidth(400);
        cbo.setValue("Canada");

        ObservableList<String> items = FXCollections.observableArrayList(flagTitles);
        cbo.getItems().addAll(items);
        pane.setCenter(descriptionPane);

        // Display the selected country
        cbo.setOnAction(e -> setDisplay(items.indexOf(cbo.getValue())));

        //Create a scene and place it in the stage
        Scene scene = new Scene(pane, 450, 170);
        primaryStage.setTitle("ComboBoxDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /** Set display information on the description pane */
    public void setDisplay(int index){
        descriptionPane.setTitle(flagTitles[index]);
        descriptionPane.setImageView(flagImage[index]);
        descriptionPane.setDescription(flagDescription[index]);
    }

    public static void main(String[] args){ launch(args);}
}
