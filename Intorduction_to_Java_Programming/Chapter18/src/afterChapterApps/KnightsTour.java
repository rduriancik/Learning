package afterChapterApps;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by rduriancik on 2/11/16.
 */
public class KnightsTour extends Application {

    // The image of knight
    private ImagePattern knightImage = new ImagePattern(new Image
            (Paths.get("src/afterChapterApps/knightFigure.png").toUri().toString()));

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a main BorderPane
        BorderPane borderPane = new BorderPane();
        // Create a BoardPane and place it in the main pane
        BoardPane boardPane = new BoardPane();

        borderPane.setCenter(boardPane);

        // Create a control Button and place it in the main pane
        Button btSolve = new Button("Solve");

        borderPane.setBottom(btSolve);
        BorderPane.setAlignment(btSolve, Pos.CENTER);
        BorderPane.setMargin(btSolve, new Insets(5));

        // Create an action event for boardPane
        boardPane.setOnMouseClicked(e -> {
            if (boardPane.hasKnight()) {
                boardPane.clearBoard();
            }

            Cell[][] cells = boardPane.getCells();

            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    if (cells[i][j].contains(e.getX(), e.getY())) {
                        cells[i][j].setFill(knightImage);
                        cells[i][j].setInPath(true);
                        boardPane.setKnightX(i);
                        boardPane.setKnightY(j);
                        break;
                    }
                }
            }

        });

        // Create an action event for the btSolve
        btSolve.setOnAction(e -> boardPane.findKnightsTour());

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Knight's tour");
        primaryStage.show();
    }

    /**
     * This class represents a cell object
     */
    static class Cell extends Rectangle {
        private boolean isInPath;

        protected Cell(double x, double y, double width, double height) {
            super(x, y, width, height);
            isInPath = false;
        }

        protected void setInPath(boolean inPath) {
            isInPath = inPath;
        }

        protected boolean isInPath() {
            return isInPath;
        }
    }


    /**
     * This class creates a pane with 8x8 cells
     */
    static class BoardPane extends Pane {

        private Cell[][] cells;
        private int knightX = -1;
        private int knightY = -1;
        private final double SIZE = 25;

        protected BoardPane() {
            cells = new Cell[8][8];

            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    cells[i][j] = new Cell(j * SIZE, i * SIZE, SIZE, SIZE);
                    cells[i][j].setStroke(Color.BLACK);
                    cells[i][j].setFill(Color.WHITE);

                    getChildren().add(cells[i][j]);
                }
            }
        }

        protected void setKnightX(int knightX) {
            this.knightX = knightX;
        }

        protected void setKnightY(int knightY) {
            this.knightY = knightY;
        }

        /**
         * Returns board's cells
         */
        protected Cell[][] getCells() {
            return cells;
        }

        /**
         * Returns true if a knight is in the board, false if not
         */
        protected boolean hasKnight() {
            return knightX >= 0 && knightY >= 0;
        }

        /**
         * Clear the whole board
         */
        protected void clearBoard() {
            if (hasKnight()) {
                getChildren().removeIf(p -> p instanceof Line);
                for (int i = 0; i < cells.length; i++) {
                    for (int j = 0; j < cells[0].length; j++) {
                        if (cells[i][j].getFill() != Color.WHITE) {
                            cells[i][j].setFill(Color.WHITE);
                            cells[i][j].setInPath(false);
                        }

                        cells[i][j].setInPath(false);
                    }
                }
            }
        }

        /**
         * Returns available positions for knight's next move
         */
        private List<Point2D> getAvailablePositions(int knightX, int knightY) {
            if (!hasKnight()) {
                return Collections.EMPTY_LIST;
            }

            List<Point2D> positions = new ArrayList<>();

            // Tests each possible next position
            if (knightY - 2 >= 0) {
                if (knightX - 1 >= 0
                        && !cells[knightX - 1][knightY - 2].isInPath()) {
                    positions.add(new Point2D(knightX - 1, knightY - 2));
                }

                if (knightX + 1 <= cells[0].length - 1
                        && !cells[knightX + 1][knightY - 2].isInPath()) {
                    positions.add(new Point2D(knightX + 1, knightY - 2));
                }
            }

            if (knightY - 1 >= 0) {
                if (knightX - 2 >= 0
                        && !cells[knightX - 2][knightY - 1].isInPath()) {
                    positions.add(new Point2D(knightX - 2, knightY - 1));
                }

                if (knightX + 2 <= cells[0].length - 1
                        && !cells[knightX + 2][knightY - 1].isInPath()) {
                    positions.add(new Point2D(knightX + 2, knightY - 1));
                }
            }

            if (knightY + 2 <= cells.length - 1) {
                if (knightX - 1 >= 0
                        && !cells[knightX - 1][knightY + 2].isInPath()) {
                    positions.add(new Point2D(knightX - 1, knightY + 2));
                }

                if (knightX + 1 <= cells[0].length - 1
                        && !cells[knightX + 1][knightY + 2].isInPath()) {
                    positions.add(new Point2D(knightX + 1, knightY + 2));
                }
            }

            if (knightY + 1 <= cells.length - 1) {
                if (knightX - 2 >= 0
                        && !cells[knightX - 2][knightY + 1].isInPath()) {
                    positions.add(new Point2D(knightX - 2, knightY + 1));
                }

                if (knightX + 2 <= cells[0].length - 1
                        && !cells[knightX + 2][knightY + 1].isInPath()) {
                    positions.add(new Point2D(knightX + 2, knightY + 1));
                }
            }

            return positions;
        }

        /**
         * Returns knight's next move
         */
        private int[] getNextMove() {
            if (!hasKnight()) {
                return null;
            }

            List<Point2D> availablePositions = getAvailablePositions(knightX, knightY);

            if (availablePositions.isEmpty()) {
                return null;
            }

            int[] nextMove = new int[2];
            int biggestSize = Integer.MAX_VALUE;

            // Sorts available positions and chooses the position with the least next available positions
            for (Point2D point : availablePositions) {
                int size = getAvailablePositions((int) point.getX(), (int) point.getY()).size();
                if (size < biggestSize) {
                    biggestSize = size;
                    nextMove[0] = (int) point.getX();
                    nextMove[1] = (int) point.getY();
                }

            }

            return nextMove;
        }

        /**
         * This recursive method finds a possible knight's path
         */
        protected void findKnightsTour() {
            if (hasKnight()) {
                int[] knightsNextMove = getNextMove();

                if (knightsNextMove != null) {
                    Line line = new Line();
                    line.setStartX(cells[knightX][knightY].getX() + (SIZE / 2));
                    line.setStartY(cells[knightX][knightY].getY() + (SIZE / 2));
                    line.setEndX(cells[knightsNextMove[0]][knightsNextMove[1]].getX() + (SIZE / 2));
                    line.setEndY(cells[knightsNextMove[0]][knightsNextMove[1]].getY() + (SIZE / 2));
                    line.setStroke(Color.RED);
                    line.setStrokeWidth(2);

                    getChildren().add(line);

                    cells[knightsNextMove[0]][knightsNextMove[1]].setInPath(true);
                    knightX = knightsNextMove[0];
                    knightY = knightsNextMove[1];

                    findKnightsTour();
                }
            }
        }
    }
}
