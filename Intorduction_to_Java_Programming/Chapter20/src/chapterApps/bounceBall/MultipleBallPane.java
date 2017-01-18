package chapterApps.bounceBall;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Created by robert on 11.4.2016.
 */
public class MultipleBallPane extends Pane {
    private Timeline animation;

    public MultipleBallPane(){
        // Create an animation for moving the ball
        animation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public void add(){
        Color color = new Color(Math.random(), Math.random(), Math.random(), 0.5);
        getChildren().add(new Ball(30, 30, 20, color));
    }

    public void subtract(){
        if (getChildren().size() > 0) {
            getChildren().remove(getChildren().size() - 1);
        }
    }

    public void play(){
        animation.play();
    }

    public void pause(){
        animation.pause();
    }

    public void increaseSpeed(){
        animation.setRate(animation.getRate() + 0.1);
    }

    public void decreaseSpeed(){
        animation.setRate(
                animation.getRate() > 0 ? animation.getRate() - 0.1 : 0
        );
    }

    public DoubleProperty rateProperty(){
        return animation.rateProperty();
    }

    protected void moveBall(){
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
}
