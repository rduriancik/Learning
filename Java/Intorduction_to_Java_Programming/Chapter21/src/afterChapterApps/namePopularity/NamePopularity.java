package afterChapterApps.namePopularity;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by robert on 29.8.2016.
 */
public class NamePopularity extends Application {

    private NameExpert nameExpert = new NameExpert();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(5);
        pane.setHgap(5);

        // Year
        pane.add(new Label("Select a year:"), 0, 0);

        ComboBox<String> cbYear = new ComboBox<>();
        cbYear.getItems().addAll("2001", "2002", "2003", "2004",
                "2005", "2006", "2007", "2008", "2009", "2010");
        cbYear.setValue("2001");
        pane.add(cbYear, 1, 0);

        // Gender
        pane.add(new Label("Boy or girl?"), 0, 1);

        ComboBox<String> cbGender = new ComboBox<>();
        cbGender.getItems().addAll("Male", "Female");
        cbGender.setValue("Male");
        pane.add(cbGender, 1, 1);

        // Name
        pane.add(new Label("Enter a name:"), 0, 2);

        TextField tfName = new TextField();
        tfName.setPrefColumnCount(15);
        pane.add(tfName, 1, 2);

        // Find button
        Button btFind = new Button("Find Ranking");
        pane.add(btFind, 1, 3);

        // Info label
        Label lbInfo = new Label();
        pane.add(lbInfo, 0, 4);
        GridPane.setColumnSpan(lbInfo, 2);

        // Action event for the button btFind
        btFind.setOnAction(e -> {
            int year = Integer.valueOf(cbYear.getValue());
            String gender = String.valueOf(cbGender.getValue());
            String name = tfName.getText();

            if (name.isEmpty()) {
                lbInfo.setText("You must enter a name");
            } else {
                lbInfo.setText(nameExpert.getRank(name, gender, year));
            }
        });


        Scene scene = new Scene(pane, 340, 160);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Baby Name Popularity");
        primaryStage.show();
    }
}
