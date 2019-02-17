package afterChapterApps.bounceBallExtra;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Created by robert on 11.4.2016.
 */
public class MultipleBallPane extends Pane {
    private Timeline animation;

    public MultipleBallPane() {
        // Create an animation for moving the ball
        animation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public void add() {
        Color color = new Color(Math.random(), Math.random(), Math.random(), 0.5);
        getChildren().add(new Ball(30, 30, 5, color));
    }

    public void subtract() {
        if (getChildren().size() > 0) {
            getChildren().remove(getChildren().size() - 1);
        }
    }

    public void play() {
        animation.play();
    }

    public void pause() {
        animation.pause();
    }

    public void increaseSpeed() {
        animation.setRate(animation.getRate() + 0.1);
    }

    public void decreaseSpeed() {
        animation.setRate(
                animation.getRate() > 0 ? animation.getRate() - 0.1 : 0
        );
    }

    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }

    protected void moveBall() {
        detectCollision();
        for (Node node : this.getChildren()) {
            Ball ball = (Ball) node;
            // Check boundaries
            if (ball.getCenterX() < ball.getRadius() ||
                    ball.getCenterX() > getWidth() - ball.getRadius()) {
                ball.setDx(ball.getDx() * -1); // Change ball move direction
            }

            if (ball.getCenterY() < ball.getRadius() ||
                    ball.getCenterY() > getHeight() - ball.getRadius()) {
                ball.setDy(ball.getDy() * -1); // Change ball move direction
            }

            // Adjust ball position
            ball.setCenterX(ball.getDx() + ball.getCenterX());
            ball.setCenterY(ball.getDy() + ball.getCenterY());
        }
    }

    private void detectCollision() {
        ObservableList<Node> balls = this.getChildren();
        for (int i = 0; i < balls.size(); i++) {
            for (int j = i + 1; j < balls.size(); j++) {
                Ball b1 = (Ball) balls.get(j);
                Ball b2 = (Ball) balls.get(i);
                if (b1.contains(b2)) {
                    b1.setRadius(b1.getRadius() + b2.getRadius());
                    this.getChildren().remove(b2);

                    if (b1.getCenterX() < b1.getRadius()) {
                        b1.setCenterX(b1.getCenterX() + (b1.getRadius() - b1.getCenterX()));
                    }

                    if (b1.getCenterX() > getWidth() - b1.getRadius()) {
                        b1.setCenterX(b1.getCenterX() - (b1.getCenterX() - getWidth() + b1.getRadius()));
                    }

                    if (b1.getCenterY() < b1.getRadius()) {
                        b1.setCenterY(b1.getCenterY() + (b1.getRadius() - b1.getCenterY()));
                    }

                    if (b1.getCenterY() > getHeight() - b1.getRadius()) {
                        b1.setCenterY(b1.getCenterY() - (b1.getCenterY() - getHeight() + b1.getRadius()));
                    }
                }
            }
        }
    }

}
