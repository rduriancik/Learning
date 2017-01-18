import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.*;


/**
 * Created by robert on 17.11.15.
 */
public class TestDisplayCalendar extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a main pane
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: white");

        // Create a bottom HBox for buttons
        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color: white");
        Button btNext = new Button("Next");
        Button btPrior = new Button("Prior");
        hBox.getChildren().addAll(btPrior, btNext);
        pane.setBottom(hBox);

        // Create a GregorianCalendar instance
        GregorianCalendar currentMonthCalendar = new GregorianCalendar();

        // Create a top label with the current month and the year
        Label lbMonthAndYear =
                new Label(String.format("%s, %d", getMonth(currentMonthCalendar), currentMonthCalendar.get(YEAR)));
        pane.setTop(lbMonthAndYear);
        BorderPane.setAlignment(lbMonthAndYear, Pos.CENTER);

        // Create a GridPane and a calendar
        GridPane gpCalendar = new GridPane();
        gpCalendar.setPadding(new Insets(10));
        gpCalendar.setAlignment(Pos.CENTER);
        gpCalendar.setHgap(10);
        gpCalendar.setVgap(5);
        gpCalendar.setStyle("-fx-background-color: white");

        gpCalendar.add(new Label("Sunday"), 0, 0);
        gpCalendar.add(new Label("Monday"), 1, 0);
        gpCalendar.add(new Label("Tuesday"), 2, 0);
        gpCalendar.add(new Label("Wednesday"), 3, 0);
        gpCalendar.add(new Label("Thursday"), 4, 0);
        gpCalendar.add(new Label("Friday"), 5, 0);
        gpCalendar.add(new Label("Saturday"), 6, 0);

        Calendar calendar = new GregorianCalendar(currentMonthCalendar.get(YEAR), currentMonthCalendar.get(MONTH), 1);
        Label[][] days = getDays(calendar, currentMonthCalendar.get(Calendar.DAY_OF_MONTH));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                gpCalendar.add(days[i][j], j, i + 1);
            }
        }
        pane.setCenter(gpCalendar);

        btNext.setOnAction(e -> {
            if (calendar.get(MONTH) == 11) {
                calendar.set(calendar.get(YEAR) + 1, Calendar.JANUARY, 1);
                lbMonthAndYear.setText(String.format("%s, %d", getMonth(calendar), calendar.get(YEAR)));
            } else {
                calendar.set(calendar.get(YEAR), calendar.get(MONTH) + 1, 1);
                lbMonthAndYear.setText(String.format("%s, %d", getMonth(calendar), calendar.get(YEAR)));
            }

            Label[][] newDays;
            if (calendar.get(MONTH) == currentMonthCalendar.get(MONTH)
                    && calendar.get(YEAR) == currentMonthCalendar.get(YEAR)) {
                newDays = getDays(calendar, currentMonthCalendar.get(DAY_OF_MONTH));
            } else {
                newDays = getDays(calendar, 0);
            }
            gpCalendar.getChildren().remove(7, gpCalendar.getChildren().size() - 1);
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    gpCalendar.add(newDays[i][j], j, i + 1);
                }
            }
        });

        btPrior.setOnAction(e -> {
            if (calendar.get(MONTH) == 0) {
                calendar.set(calendar.get(YEAR) - 1, Calendar.DECEMBER, 1);
                lbMonthAndYear.setText(String.format("%s, %d", getMonth(calendar), calendar.get(YEAR)));
            } else {
                calendar.set(calendar.get(YEAR), calendar.get(MONTH) - 1, 1);
                lbMonthAndYear.setText(String.format("%s, %d", getMonth(calendar), calendar.get(YEAR)));
            }

            Label[][] newDays;
            if (calendar.get(MONTH) == currentMonthCalendar.get(MONTH)
                    && calendar.get(YEAR) == currentMonthCalendar.get(YEAR)) {
                newDays = getDays(calendar, currentMonthCalendar.get(DAY_OF_MONTH));
            } else {
                newDays = getDays(calendar, 0);
            }
            gpCalendar.getChildren().remove(7, gpCalendar.getChildren().size() - 1);
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    gpCalendar.add(newDays[i][j], j, i + 1);
                }
            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestDisplayCalendar");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Returns a string representation of the current month
     *
     * @param calendar instance of the Calendar class
     * @return a string representation of the current month
     */
    private String getMonth(Calendar calendar) {
        String month = "";
        switch (calendar.get(MONTH)) {
            case JANUARY:
                month = "January";
                break;
            case FEBRUARY:
                month = "February";
                break;
            case MARCH:
                month = "March";
                break;
            case APRIL:
                month = "April";
                break;
            case MAY:
                month = "May";
                break;
            case JUNE:
                month = "June";
                break;
            case JULY:
                month = "July";
                break;
            case AUGUST:
                month = "August";
                break;
            case SEPTEMBER:
                month = "September";
                break;
            case OCTOBER:
                month = "October";
                break;
            case NOVEMBER:
                month = "November";
                break;
            case DECEMBER:
                month = "December";
                break;
        }

        return month;
    }

    /**
     * Returns an array of the Labels with days of current month
     *
     * @param calendar   instance of the Calendar class
     * @param currentDay the current day
     * @return days of current month
     */
    private Label[][] getDays(Calendar calendar, int currentDay) {
        Label[][] days = new Label[6][7];

        int currentMonthDays = calendar.getActualMaximum(DAY_OF_MONTH);
        int priorMonthDays;
        if (calendar.get(MONTH) == 0) {
            Calendar priorMonthCalendar = new GregorianCalendar();
            priorMonthCalendar.set(calendar.get(YEAR), DECEMBER, calendar.get(DAY_OF_MONTH));
            priorMonthDays = priorMonthCalendar.getActualMaximum(DAY_OF_MONTH);
        } else {
            Calendar priorMonthCalendar = new GregorianCalendar();
            priorMonthCalendar.set(calendar.get(YEAR), calendar.get(MONTH) - 1, calendar.get(DATE));
            priorMonthDays = priorMonthCalendar.getActualMaximum(DAY_OF_MONTH);
        }

        int day = 1;
        boolean prior = false;
        boolean next = true;
        if (calendar.get(DAY_OF_WEEK) > 1) {
            prior = true;
            next = false;
            day = priorMonthDays - (calendar.get(DAY_OF_WEEK) - 2);
        }

        for (int i = 0; i < days.length; i++) {
            for (int j = 0; j < days[i].length; j++) {
                days[i][j] = new Label(day + "");
                if (prior) {
                    days[i][j].setTextFill(Color.GRAY);
                    if (day == priorMonthDays) {
                        day = 1;
                        prior = false;
                        next = true;
                        continue;
                    }
                }

                if (next) {
                    if (day == currentDay) {
                        days[i][j].setStyle("-fx-background-color: red");
                        days[i][j].setTextFill(Color.WHITE);
                    } else {
                        days[i][j].setTextFill(Color.BLACK);
                    }
                    if (day == currentMonthDays) {
                        day = 1;
                        prior = true;
                        next = false;
                        continue;
                    }
                }
                day++;
            }
        }

        return days;
    }
}
