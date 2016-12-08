package afterChapterApps.hangmanGUI;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by robert on 4.5.2016.
 */
public class HangmanPane extends Pane {
    private Queue<Node> hangmanParts;

    public HangmanPane() {
        this.setPrefSize(300, 500);
        setStyle("-fx-border-color: black");
        hangmanParts = new LinkedList<>();
        fillHangmanParts();
    }

    private void fillHangmanParts() {
        // equipment
        hangmanParts.offer(new Line(20, getPrefHeight() - 20, getPrefWidth() / 2 - 20, getPrefHeight() - 20));
        hangmanParts.offer(new Line(getPrefWidth() / 4, getPrefHeight() - 20, getPrefWidth() / 4, 50));
        hangmanParts.offer(new Line(getPrefWidth() / 4, 50, getPrefWidth() / 1.6, 50));
        hangmanParts.offer(new Line(getPrefWidth() / 1.6, 50, getPrefWidth() / 1.6, getPrefHeight() / 4));

        // body
        hangmanParts.offer(new Circle(getPrefWidth() / 1.6, getPrefHeight() / 4 + 35, 35));
        hangmanParts.offer(new Line(getPrefWidth() / 1.6, getPrefHeight() / 4 + 70,
                getPrefWidth() / 1.6, getPrefHeight() / 1.6));
        hangmanParts.offer(new Line(getPrefWidth() / 1.6, getPrefHeight() / 1.6, getPrefWidth() / 1.6 - 40, getPrefHeight() / 1.4));
        hangmanParts.offer(new Line(getPrefWidth() / 1.6, getPrefHeight() / 1.6, getPrefWidth() / 1.6 + 40, getPrefHeight() / 1.4));
        hangmanParts.offer(new Line(getPrefWidth() / 1.6, getPrefHeight() / 2.2,
                getPrefWidth() / 1.6 - 40, getPrefHeight() / 2));
        hangmanParts.offer(new Line(getPrefWidth() / 1.6, getPrefHeight() / 2.2,
                getPrefWidth() / 1.6 + 40, getPrefHeight() / 2));
    }

    public boolean paint() {
        Node n = hangmanParts.poll();
        if (n == null) {
            return false;
        } else {
            getChildren().add(n);
            return true;
        }
    }

    public boolean isWholeHangman() {
        return hangmanParts.isEmpty();
    }

    public void restart() {
        getChildren().clear();
        hangmanParts.clear();
        fillHangmanParts();
    }
}
