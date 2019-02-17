package afterChapterApps;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by robert on 12.10.2016.
 */
public class TaskThreadDemoGUI extends Application {

    private TextArea textArea = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();

        textArea.setPrefSize(500, 200);
        textArea.setWrapText(true);
        textArea.setEditable(false);
        pane.getChildren().add(textArea);

        new Thread(new PrintChar('a', 1000)).start();
        new Thread(new PrintChar('b', 1000)).start();
        new Thread(new PrintNum(1000)).start();

        Scene scene = new Scene(pane, 500, 200);
        primaryStage.setTitle("ThreadDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class PrintChar implements Runnable {
        private char charToPrint;
        private int times;

        public PrintChar(char ch, int times) {
            this.charToPrint = ch;
            this.times = times;
        }

        @Override
        public void run() {
            for (int i = 0; i < times; i++) {
                Platform.runLater(() -> textArea.setText(textArea.getText() + charToPrint));
            }
        }
    }

    class PrintNum implements Runnable {
        private int lastNum;

        public PrintNum(int lastNum) {
            this.lastNum = lastNum;
        }

        @Override
        public void run() {
            Platform.runLater(() -> {
                for (int i = 0; i < lastNum; i++) {
                    textArea.setText(textArea.getText() + i + " ");
                }
            });
        }
    }
}


