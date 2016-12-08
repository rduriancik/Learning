import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

/**
 * Created by robkio on 24.9.2015.
 */
public class TextAreaDemo extends  Application {
    @Override
    public void start(Stage primaryStage){
        //Declare and create a description pane
        DescriptionPane descriptionPane = new DescriptionPane();

        //Set title, text and image in the description pane
        descriptionPane.setTitle("Canada");
        String description = "The Canadian national flag ...";
        descriptionPane.setImageView(new ImageView("http://www.cs.armstrong.edu/liang/common/image/ca.gif"));
        descriptionPane.setDescription(description);

        //Create a scene and place it in the stage
        Scene scene = new Scene(descriptionPane, 450, 150);
        primaryStage.setTitle("TextAreaDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){launch(args);}
}
