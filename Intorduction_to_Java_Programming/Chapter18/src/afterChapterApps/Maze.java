package afterChapterApps;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.nio.file.Paths;

/**
 * Created by robert on 24.01.16.
 */
public class Maze extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a main BorderPane
        BorderPane pane = new BorderPane();

        // Create a label for information and place it in the top of the main pane
        Label lbInfo = new Label("Find the path between" +
                " the upper-left corner cell and " +
                "the lower-right corner cell in the maze");
        lbInfo.setWrapText(true);

        pane.setTop(lbInfo);
        BorderPane.setAlignment(lbInfo, Pos.CENTER);
        BorderPane.setMargin(lbInfo, new Insets(5));

        // Create a GridPane with cells and place it in the center of the main pane
        CellPane maze = new CellPane();

        pane.setCenter(maze);
        BorderPane.setMargin(maze, new Insets(0, 5, 0, 5));

        // Create an mouse event
        Cell[][] cells = maze.getCells();
        maze.setOnMouseClicked(e -> {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    if (cells[i][j].contains(e.getX(), e.getY())) {
                        if ((i == 0 && j == 0) || (i == 7 && j == 7)) {
                            lbInfo.setText("The first and last cell cannot be marked.");
                            break;
                        }
                        if (cells[i][j].isWall()) {
                            cells[i][j].setWall(false);
                        } else {
                            cells[i][j].setWall(true);
                        }
                    }
                }
            }
        });

        // Create the control buttons and place they in a HBox
        HBox hbButtons = new HBox(5);
        hbButtons.setAlignment(Pos.CENTER);
        hbButtons.setPadding(new Insets(5));

        Button btFindPath = new Button("Find Path");
        Button btClearPath = new Button("Clear Path");

        // Create an action event for the button btCleatPath
        btClearPath.setOnAction(e -> maze.clearAllPath());

        // Create an action event fot the button btFindPath
        btFindPath.setOnAction(e -> {
            maze.clearAllPath();
            if (maze.findPath(0, 0)) {
                lbInfo.setText("The path was found");
            } else {
                lbInfo.setText("The path was not found");
            }
        });

        hbButtons.getChildren().addAll(btFindPath, btClearPath);

        // Place the HBox with buttons in the main pane
        pane.setBottom(hbButtons);

        // Create a scene and place it in te stage
        Scene scene = new Scene(pane, 250, 340);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Maze");
        primaryStage.show();
    }

    /**
     * Class that represents a Cell
     */
    static class Cell extends Rectangle {
        private boolean isWall;
        private boolean isInPath;
        private boolean wasHere;
        private Image wallImg = new Image(Paths.get("src/afterChapterApps/x.png").toUri().toString());

        public Cell(double x, double y, double width, double height) {
            super(x, y, width, height);
            this.isWall = false;
            this.isInPath = false;
            this.wasHere = false;
        }

        public boolean isWall() {
            return isWall;
        }

        public void setWall(boolean isWall) {
            if (isWall) {
                this.setFill(new ImagePattern(wallImg));
            } else {
                this.setFill(Color.WHITE);
            }

            this.isWall = isWall;
        }

        public boolean wasHere() {
            return wasHere;
        }

        public void setWasHere(boolean wasHere) {
            this.wasHere = wasHere;
        }

        public boolean isInPath() {
            return isInPath;
        }

        public void setInPath(boolean isInPath) {
            if (isInPath) {
                this.isInPath = true;
                this.setStroke(Color.BLACK);
                this.setFill(Color.RED);
            } else {
                this.isInPath = false;
                if (!isWall()) {
                    this.setStroke(Color.BLACK);
                    this.setFill(Color.WHITE);
                }
            }
        }
    }

    /**
     * Pane formed by cells
     */
    static class CellPane extends Pane {

        private Cell[][] cells = new Cell[8][8];

        public CellPane() {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    cells[i][j] = new Cell(30 * j, 30 * i, 30, 30);
                    cells[i][j].setFill(Color.WHITE);
                    cells[i][j].setStroke(Color.BLACK);

                    this.getChildren().addAll(cells[i][j]);
                }
            }
        }

        public Cell[][] getCells() {
            return cells;
        }

        private boolean findPath(int row, int column) {
            System.out.println(row + " " + column);
            if (row == cells.length - 1 && column == cells[0].length - 1) {
                cells[row][column].setInPath(true);
                return true;
            }

            if (cells[row][column].isWall() || cells[row][column].wasHere()) {
                return false;
            }

            cells[row][column].setWasHere(true);

            if (row != cells.length - 1) {
                if (findPath(row + 1, column)) {
                    cells[row][column].setInPath(true);
                    return true;
                }
            }

            if (column != cells[0].length - 1) {
                if (findPath(row, column + 1)) {
                    cells[row][column].setInPath(true);
                    return true;
                }
            }

            if (row != 0) {
                if (findPath(row - 1, column)) {
                    cells[row][column].setInPath(true);
                    return true;
                }
            }

            if (column != 0) {
                if (findPath(row, column - 1)) {
                    cells[row][column].setInPath(true);
                    return true;
                }
            }

            return false;
        }

        /**
         * This method clears all of the created path
         */
        private void clearAllPath() {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    cells[i][j].setWasHere(false);
                    cells[i][j].setInPath(false);
                }
            }
        }
    }
}
