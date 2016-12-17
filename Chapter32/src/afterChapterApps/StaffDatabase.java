package afterChapterApps;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by robert on 17.12.2016.
 */
public class StaffDatabase extends Application {
    private TextField tfId;
    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfMI;
    private TextField tfAddress;
    private TextField tfCity;
    private TextField tfState;
    private TextField tfTelephone;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(5);
        pane.setVgap(5);

        Label lbInfo = new Label("Staff Database");
        pane.add(lbInfo, 0, 0, 6, 1);

        pane.add(new Label("ID"), 0, 1);
        tfId = new TextField();
        pane.add(tfId, 1, 1);

        pane.add(new Label("Last Name"), 0, 2);
        tfLastName = new TextField();
        pane.add(tfLastName, 1, 2);

        pane.add(new Label("First Name"), 2, 2);
        tfFirstName = new TextField();
        pane.add(tfFirstName, 3, 2);

        pane.add(new Label("MI"), 4, 2);
        tfMI = new TextField();
        tfMI.setPrefColumnCount(1);
        pane.add(tfMI, 5, 2);

        pane.add(new Label("Address"), 0, 3);
        tfAddress = new TextField();
        pane.add(tfAddress, 1, 3);

        pane.add(new Label("City"), 0, 4);
        tfCity = new TextField();
        pane.add(tfCity, 1, 4);

        Label lbState = new Label("State");
        pane.add(lbState, 2, 4);
        GridPane.setHalignment(lbState, HPos.CENTER);
        tfState = new TextField();
        pane.add(tfState, 3, 4, 2, 1);

        pane.add(new Label("Telephone"), 0, 5);
        tfTelephone = new TextField();
        pane.add(tfTelephone, 1, 5);

        HBox buttons = new HBox(5);
        buttons.setAlignment(Pos.CENTER);
        Button btView = new Button("View");
        Button btInsert = new Button("Insert");
        Button btUpdate = new Button("Update");
        Button btClear = new Button("Clear");
        buttons.getChildren().addAll(btView, btInsert, btUpdate, btClear);

        pane.add(buttons, 0, 6, 6, 1);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Staff");
        primaryStage.show();
    }
}
