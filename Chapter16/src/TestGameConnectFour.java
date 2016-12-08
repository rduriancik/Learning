import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by robert on 22.11.15.
 */
public class TestGameConnectFour extends Application {

    private Circle[][] circles = new Circle[6][7];
    private Label lbInfo;
    Turn turn = Turn.RED;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a main BorderPane
        BorderPane pane = new BorderPane();

        // Create a GridPane for game
        Pane paneForCells = new Pane();
        paneForCells.setStyle("-fx-background-color: blue");
        paneForCells.setPadding(new Insets(0, 5, 5, 0));

        // Create cells
        for (int i = 0; i < circles.length; i++) {
            for (int j = 0; j < circles[i].length; j++) {
                circles[i][j] = new Circle(35 + (j * 65), 35 + (i * 65), 30);
                circles[i][j].setStyle("-fx-stroke-width: 2px");
                circles[i][j].setStroke(Color.BLACK);
                circles[i][j].setFill(Color.WHITE);
                paneForCells.getChildren().add(circles[i][j]);
            }
        }

        pane.setTop(paneForCells); // place the paneForCells in the main pane

        // Create a bottom information label
        lbInfo = new Label("Welcome. RED go");
        pane.setBottom(lbInfo);
        BorderPane.setAlignment(lbInfo, Pos.CENTER);

        // Create OnMouseClick event for the paneForCells
        paneForCells.setOnMouseClicked(e -> {
            for (int i = 0; i < circles.length; i++) {
                for (int j = 0; j < circles[i].length; j++) {
                    if (circles[i][j].contains(e.getX(), e.getY())) {
                        if (!isFull()) {
                            if (!areFourInDiagonal() && !areFourInRow() && !areFourInColumn()) {
                                fillCircleInColumn(j);
                            } else {
                                break;
                            }
                        } else {
                            lbInfo.setText("It is a DRAW");
                        }
                    }
                }
            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Game: Connect Four");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private boolean fillCircleInColumn(int j){
        for(int i = circles.length - 1; i >= 0; i--){
            if (circles[i][j].getFill() == Color.WHITE) {
                if (turn.equals(Turn.RED)) {
                    circles[i][j].setFill(Color.RED);
                    turn = Turn.YELLOW;
                    lbInfo.setText("YELLOW go");
                    return !areFourInDiagonal() && !areFourInRow() && !areFourInColumn();
                } else {
                    circles[i][j].setFill(Color.YELLOW);
                    turn = Turn.RED;
                    lbInfo.setText("RED go");
                    return !areFourInDiagonal() && !areFourInRow() && !areFourInColumn();
                }
            }
        }

        return !areFourInDiagonal() && !areFourInRow() && !areFourInColumn();
    }


    /**
     * Returns true if all circles are colorful, false if not
     *
     * @return true if grid is full, false if not
     */
    private boolean isFull() {
        for (int i = 0; i < circles.length; i++) {
            for (int j = 0; j < circles[i].length; j++) {
                if (circles[i][j].getFill() == Color.WHITE) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns true if there are four consecutive circles with the same color in a row and mark them, false if not
     *
     * @return true if there are four consecutive circles with the same color in a row, false if not
     */
    private boolean areFourInRow() {
        boolean areFourInRow = false;
        int countRed;
        int countYellow;
        int[] indexOfCells = new int[2];
        boolean winRed = false;
        boolean winYellow = false;

        for (int i = circles.length - 1; i >= 0; i--) {
            countRed = 1;
            countYellow = 1;
            for (int j = 0; j < circles[i].length - 1; j++) {
                if (circles[i][j].getFill() == Color.RED
                        && circles[i][j].getFill() == circles[i][j + 1].getFill()) {
                    countRed++;
                    countYellow = 1;
                } else if (circles[i][j].getFill() == Color.YELLOW
                        && circles[i][j].getFill() == circles[i][j + 1].getFill()) {
                    countYellow++;
                    countRed = 1;
                }

                if (countRed == 4) {
                    winRed = true;
                    areFourInRow = true;
                    indexOfCells[0] = i;
                    indexOfCells[1] = j - 2;
                    break;
                }

                if (countYellow == 4) {
                    winYellow = true;
                    areFourInRow = true;
                    indexOfCells[0] = i;
                    indexOfCells[1] = j - 2;
                    break;
                }
            }
        }

        if (areFourInRow) {
            if (winRed) {
                lbInfo.setText("RED IS WINNER!!!");
                EventHandler<ActionEvent> handler = e -> {
                    for (int j = indexOfCells[1]; j < indexOfCells[1] + 4; j++) {
                        if (circles[indexOfCells[0]][j].getStroke() == Color.YELLOW) {
                            circles[indexOfCells[0]][j].setStroke(Color.BLACK);
                        } else {
                            circles[indexOfCells[0]][j].setStroke(Color.YELLOW);
                        }
                    }
                };

                Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), handler));
                animation.setCycleCount(Timeline.INDEFINITE);
                animation.play();
            } else if (winYellow) {
                lbInfo.setText("YELLOW IS WINNER!!!");
                EventHandler<ActionEvent> handler = e -> {
                    for (int j = indexOfCells[1]; j < indexOfCells[1] + 4; j++) {
                        if (circles[indexOfCells[0]][j].getStroke() == Color.RED) {
                            circles[indexOfCells[0]][j].setStroke(Color.BLACK);
                        } else {
                            circles[indexOfCells[0]][j].setStroke(Color.RED);
                        }
                    }
                };

                Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), handler));
                animation.setCycleCount(Timeline.INDEFINITE);
                animation.play();
            }
        }

        return areFourInRow;
    }

    /**
     * Returns true if there are four consecutive circles with the same color in a column and mark them, false if not
     *
     * @return true if there are four consecutive circles with the same color in a column, false if not
     */
    private boolean areFourInColumn() {
        boolean areFourInColumn = false;
        int countRed;
        int countYellow;
        int[] indexOfCells = new int[2];
        boolean winRed = false;
        boolean winYellow = false;

        for (int j = circles[0].length - 1; j >= 0; j--) {
            countRed = 1;
            countYellow = 1;
            for (int i = 0; i < circles.length - 1; i++) {
                if (circles[i][j].getFill() == Color.RED
                        && circles[i][j].getFill() == circles[i + 1][j].getFill()) {
                    countRed++;
                    countYellow = 1;
                } else if (circles[i][j].getFill() == Color.YELLOW
                        && circles[i][j].getFill() == circles[i + 1][j].getFill()) {
                    countYellow++;
                    countRed = 1;
                }

                if (countRed == 4) {
                    winRed = true;
                    areFourInColumn = true;
                    indexOfCells[0] = i - 2;
                    indexOfCells[1] = j;
                    break;
                }

                if (countYellow == 4) {
                    winYellow = true;
                    areFourInColumn = true;
                    indexOfCells[0] = i - 2;
                    indexOfCells[1] = j;
                    break;
                }
            }
        }

        if (areFourInColumn) {
            if (winRed) {
                lbInfo.setText("RED IS WINNER!!!");
                EventHandler<ActionEvent> handler = e -> {
                    for (int i = indexOfCells[0]; i < indexOfCells[0] + 4; i++) {
                        if (circles[i][indexOfCells[1]].getStroke() == Color.YELLOW) {
                            circles[i][indexOfCells[1]].setStroke(Color.BLACK);
                        } else {
                            circles[i][indexOfCells[1]].setStroke(Color.YELLOW);
                        }
                    }
                };

                Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), handler));
                animation.setCycleCount(Timeline.INDEFINITE);
                animation.play();
            } else if (winYellow) {
                lbInfo.setText("YELLOW IS WINNER!!!");
                EventHandler<ActionEvent> handler = e -> {
                    for (int i = indexOfCells[0]; i < indexOfCells[0] + 4; i++) {
                        if (circles[i][indexOfCells[1]].getStroke() == Color.RED) {
                            circles[i][indexOfCells[1]].setStroke(Color.BLACK);
                        } else {
                            circles[i][indexOfCells[1]].setStroke(Color.RED);
                        }
                    }
                };

                Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), handler));
                animation.setCycleCount(Timeline.INDEFINITE);
                animation.play();
            }
        }

        return areFourInColumn;
    }

    /**
     * Returns true if there are four consecutive circles with the same color in a diagonal and mark them, false if not
     *
     * @return true if there are four consecutive circles with the same color in a diagonal, false if not
     */
    private boolean areFourInDiagonal() {
        boolean areFourInDiagonal = false;
        int countRed;
        int countYelllow;
        int[] indexOfCells = new int[2];
        boolean winRed = false;
        boolean winYellow = false;

        for (int k = 3; k < circles.length + circles[0].length - 4; k++) {
            countRed = 1;
            countYelllow = 1;
            for (int j = 0; j <= k; j++) {
                int i = (circles.length - 1) - (k - j);
                if (i >= 0 && i < circles.length - 1 && j >= 0 && j < circles[0].length - 1) {
                    if (circles[i][j].getFill() == Color.RED
                            && circles[i][j].getFill() == circles[i + 1][j + 1].getFill()) {
                        countRed++;
                        countYelllow = 1;
                    } else if (circles[i][j].getFill() == Color.YELLOW
                            && circles[i][j].getFill() == circles[i + 1][j + 1].getFill()) {
                        countRed++;
                        countRed = 1;
                    }

                    if (countRed == 4) {
                        winRed = true;
                        areFourInDiagonal = true;
                        indexOfCells[0] = i - 2;
                        indexOfCells[1] = j - 2;
                        break;
                    }

                    if (countYelllow == 4) {
                        winYellow = true;
                        areFourInDiagonal = true;
                        indexOfCells[0] = i - 2;
                        indexOfCells[1] = j - 2;
                        break;
                    }
                }
            }
        }

        if (areFourInDiagonal) {
            if (winRed) {
                lbInfo.setText("RED IS WINNER!!!");
                EventHandler<ActionEvent> handler = e -> {
                    for (int i = 0; i < 4; i++) {
                        if (circles[indexOfCells[0] + i][indexOfCells[1] + i].getStroke() == Color.YELLOW) {
                            circles[indexOfCells[0] + i][indexOfCells[1] + i].setStroke(Color.BLACK);
                        } else {
                            circles[indexOfCells[0] + i][indexOfCells[1] + i].setStroke(Color.YELLOW);
                        }
                    }
                };

                Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), handler));
                animation.setCycleCount(Timeline.INDEFINITE);
                animation.play();
            } else if (winYellow) {
                lbInfo.setText("YELLOW IS WINNER!!!");
                EventHandler<ActionEvent> handler = e -> {
                    for (int i = 0; i < 4; i++) {
                        if (circles[indexOfCells[0] + i][indexOfCells[1] + i].getStroke() == Color.RED) {
                            circles[indexOfCells[0] + i][indexOfCells[1] + i].setStroke(Color.BLACK);
                        } else {
                            circles[indexOfCells[0] + i][indexOfCells[1] + i].setStroke(Color.RED);
                        }
                    }
                };

                Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), handler));
                animation.setCycleCount(Timeline.INDEFINITE);
                animation.play();
            }
        } else {
            // Check the diagonal from the first element of the first column to the last element of the last column
            for (int k = 3; k < circles.length + circles[0].length - 4; k++) {
                countRed = 1;
                countYelllow = 1;
                for (int j = 0; j <= k; j++) {
                    int i = k - j;
                    if (i > 0 && i < circles.length && j >= 0 && j < circles[0].length - 1) {

                        if (circles[i][j].getFill() == Color.RED
                                && circles[i][j].getFill() == circles[i - 1][j + 1].getFill()) {
                            countRed++;
                            countYelllow = 1;
                        } else if (circles[i][j].getFill() == Color.YELLOW
                                && circles[i][j].getFill() == circles[i - 1][j + 1].getFill()) {
                            countYelllow++;
                            countRed = 1;
                        }

                        if (countRed == 4) {
                            winRed = true;
                            areFourInDiagonal = true;
                            indexOfCells[0] = i + 2;
                            indexOfCells[1] = j - 2;
                            break;
                        }

                        if (countYelllow == 4) {
                            winYellow = true;
                            areFourInDiagonal = true;
                            indexOfCells[0] = i + 2;
                            indexOfCells[1] = j - 2;
                            break;
                        }
                    }
                }
            }

            if (areFourInDiagonal) {
                if (winRed) {
                    lbInfo.setText("RED IS WINNER!!!");
                    EventHandler<ActionEvent> handler = e -> {
                        for (int i = 0; i < 4; i++) {
                            if (circles[indexOfCells[0] - i][indexOfCells[1] + i].getStroke() == Color.YELLOW) {
                                circles[indexOfCells[0] - i][indexOfCells[1] + i].setStroke(Color.BLACK);
                            } else {
                                circles[indexOfCells[0] - i][indexOfCells[1] + i].setStroke(Color.YELLOW);
                            }
                        }
                    };

                    Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), handler));
                    animation.setCycleCount(Timeline.INDEFINITE);
                    animation.play();
                } else if (winYellow) {
                    lbInfo.setText("YELLOW IS WINNER!!!");
                    EventHandler<ActionEvent> handler = e -> {
                        for (int i = 0; i < 4; i++) {
                            if (circles[indexOfCells[0] - i][indexOfCells[1] + i].getStroke() == Color.RED) {
                                circles[indexOfCells[0] - i][indexOfCells[1] + i].setStroke(Color.BLACK);
                            } else {
                                circles[indexOfCells[0] - i][indexOfCells[1] + i].setStroke(Color.RED);
                            }
                        }
                    };

                    Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), handler));
                    animation.setCycleCount(Timeline.INDEFINITE);
                    animation.play();
                }
            }
        }

        return areFourInDiagonal;
    }
}

enum Turn {
    RED, YELLOW;
}
