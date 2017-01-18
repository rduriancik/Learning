import javafx.application.Application;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.geometry.Pos;

/**
 * Created by robkio on 19.10.2015.
 */
public class TestUseCboAndLv extends Application {
    public static void main(String[] args){launch(args);}

    @Override
    public void start(Stage primaryStage){
        BorderPane pane = new BorderPane();

        ComboBox<SelectionMode> cboSelectionMode = new ComboBox<>();
        cboSelectionMode.getItems().addAll(SelectionMode.SINGLE,  SelectionMode.MULTIPLE);
        cboSelectionMode.setValue(SelectionMode.SINGLE);
        HBox hBoxTop = new HBox(5);
        hBoxTop.getChildren().add(new Label("Choose Selection Mode: "));
        hBoxTop.getChildren().add(cboSelectionMode);
        hBoxTop.setAlignment(Pos.CENTER);

        pane.setTop(hBoxTop);

        ListView<String> listView = new ListView<>();
        listView.getItems().addAll("China", "Japan", "Korea", "India", "Malaysia", "Vietnam", "Thailand", "Tibet",
                "Russia", "Nepal");
        pane.setCenter(listView);

        Label lbItems = new Label();
        pane.setBottom(lbItems);

        cboSelectionMode.setOnAction(e -> {
            listView.getSelectionModel().setSelectionMode(cboSelectionMode.getValue());
        });
        listView.getSelectionModel().selectedItemProperty().addListener(e -> {
            String items = "";
            for(int i = 0; i < listView.getSelectionModel().getSelectedItems().size(); i++){
                items += listView.getSelectionModel().getSelectedItems().get(i) + " ";
            }
            lbItems.setText("Selected items are " + items);
        });
        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("TestUseCboAndLv");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
