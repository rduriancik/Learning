package afterChapterApps;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rduriancik on 2/14/16.
 */
public class EightQueens extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a main BoardPane
        BoardPane boardPane = new BoardPane();

        boardPane.solveEightQueens();


        // Create a Scene and place it in the stage
        Scene scene = new Scene(boardPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Eight Queens");
        primaryStage.show();
    }

    /**
     * This class represents a cell in the board
     */
    static class Cell extends Rectangle {
        private boolean hasQueen;
        private boolean isDead; // if the queen was on this cell isDead is true

        public Cell(double x, double y, double width, double height) {
            super(x, y, width, height);
            this.hasQueen = false;
            this.isDead = false;
        }

        public boolean hasQueen() {
            return hasQueen;
        }

        public void setHasQueen(boolean hasQueen) {
            this.hasQueen = hasQueen;
        }

        public boolean isDead() {
            return isDead;
        }

        public void setDead(boolean dead) {
            isDead = dead;
        }
    }

    /**
     * This class represents a chess board
     */
    static class BoardPane extends Pane {

        private Cell[][] cells;
        private final int SIZE = 25;
        private ImagePattern queen = new ImagePattern(new Image(
                Paths.get("src/afterChapterApps/queen.png").toUri().toString()));

        /**
         * Construct a default board pane
         */
        public BoardPane() {
            cells = new Cell[8][8];

            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    cells[i][j] = new Cell(j * SIZE, i * SIZE, SIZE, SIZE);
                    cells[i][j].setFill(Color.WHITE);
                    cells[i][j].setStroke(Color.BLACK);

                    getChildren().add(cells[i][j]);
                }
            }
        }

        /**
         * Removes the queen position and dead cells in a column
         */
        private void removeColumn(int column) {
            for (int i = 0; i < cells.length; i++) {
                if (cells[i][column].hasQueen()) {
                    cells[i][column].setHasQueen(false);
                    cells[i][column].setFill(Color.WHITE);
                }
                if (cells[i][column].isDead()) {
                    cells[i][column].setDead(false);
                }
            }
        }

        /**
         * Returns a list of integers with available positions in a column
         */
        private List<Integer> getAvailablePositions(int column) {
            boolean hasQueen;
            List<Integer> positions = new ArrayList<>();

            for (int i = 0; i < cells.length; i++) {
                hasQueen = false;

                // Checks if the queen is in the cell
                if (cells[i][column].hasQueen()) {
                    // Removes queen from the cell and sets the cell as dead position
                    cells[i][column].setDead(true);
                    cells[i][column].setHasQueen(false);
                    cells[i][column].setFill(Color.WHITE);
                    continue;
                }

                if (cells[i][column].isDead()) {
                    continue;
                }

                // Checks a row
                for (int j = 0; j < column; j++) {
                    if (cells[i][j].hasQueen()) {
                        hasQueen = true;
                        break;
                    }
                }

                if (!hasQueen) {
                    // Checks a diagonal from upper left corner to lower right corner
                    for (int j = 0; j <= (column - i) + cells.length && j != column; j++) {
                        int k = cells.length - (((column - i) + cells.length) - j);

                        if (k >= 0 && k < cells.length && j >= 0 && j < cells[0].length) {

                            if (cells[k][j].hasQueen()) {
                                hasQueen = true;
                                break;
                            }
                        }
                    }

                    if (!hasQueen) {
                        // Checks a diagonal from lower left corner to upper right corner
                        for (int j = column + i; j >= 0; j--) {
                            int k = (column + i) - j;

                            if (j >= 0 && j < cells.length && k >= 0 && k < cells[0].length) {
                                if (k == column) {
                                    break;
                                }

                                if (cells[j][k].hasQueen()) {
                                    hasQueen = true;
                                    break;
                                }
                            }
                        }
                    }
                }

                if (!hasQueen) {
                    positions.add(i);
                }
            }

            return positions;
        }

        /**
         * Returns true if the eight queens problem is solved, false if not
         */
        private boolean isSolved() {
            int count = 0;

            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    if (cells[i][j].hasQueen()) {
                        count++;
                    }
                }
            }

            return count == cells[0].length;
        }

        /**
         * Helper method for the solveEightQueens(int column) method
         */
        public void solveEightQueens() {
            solveEightQueens(0);
        }

        /**
         * Recursive method which finds the possible solution for the eight queens problem
         */
        public void solveEightQueens(int column) {
            if (!isSolved()) {
                List<Integer> positions = getAvailablePositions(column);

                if (positions.isEmpty()) {
                    removeColumn(column);
                    solveEightQueens(column - 1);
                } else {
                    int row = positions.get((int)
                            (Math.random() * positions.size()));

                    cells[row][column].setFill(queen);
                    cells[row][column].setHasQueen(true);
                }

                if (column + 1 < cells[0].length) {
                    solveEightQueens(column + 1);
                }
            }
        }

    }
}
