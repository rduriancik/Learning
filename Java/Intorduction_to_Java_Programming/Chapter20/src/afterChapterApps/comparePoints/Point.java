package afterChapterApps.comparePoints;

/**
 * Created by robert on 2.5.2016.
 */
public class Point implements Comparable<Point>{
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        int compare = Double.compare(this.x, o.x);

        return (compare == 0) ? Double.compare(this.y, o.y) : compare;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
