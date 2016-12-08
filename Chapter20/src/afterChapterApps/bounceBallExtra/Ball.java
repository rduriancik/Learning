package afterChapterApps.bounceBallExtra;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by robert on 11.4.2016.
 */
public class Ball extends Circle {

    private double dx = 1;
    private double dy = 1;

    public Ball(double x, double y, double radius, Color color) {
        super(x, y, radius);
        setFill(color);
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public boolean contains(Ball b) {
        if (b == null) {
            return false;
        }

        double distance = Math.sqrt(Math.pow(this.getCenterX() - b.getCenterX(), 2)
                + Math.pow(this.getCenterY() - b.getCenterY(), 2));

        return distance < this.getRadius() + b.getRadius();
    }
}
