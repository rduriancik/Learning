package afterChapterApps.closestPairOfPoints;

import java.util.Comparator;

/**
 * Created by robert on 2.5.2016.
 */
public class CompareY implements Comparator<Point> {
    @Override
    public int compare(Point o1, Point o2) {
        int compare = Double.compare(o1.getY(), o2.getY());

        return (compare == 0) ? Double.compare(o1.getX(), o2.getX()) : compare;
    }
}
