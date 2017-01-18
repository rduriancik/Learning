package afterChapterApps.closestPairOfPoints;

import java.util.Arrays;

/**
 * Created by robert on 22.9.2016.
 */
public class ClosestPointsPair {
    /**
     * Return the distance of the closest pair of points
     */
    public static Pair getClosestPair(double[][] points) {

    }

    /**
     * Return the distance of the closest pair of points
     */
    public static Pair getClosestPair(Point[] points) {
        Arrays.parallelSort(points);

    }

    /**
     * Return the distance of the closest pair of points
     * in pointsOrderedOnX[low..high]. This is a recursive
     * method. pointsOrderedOnX and pointsOrderedOnY are
     * not changed in the subsequent recursive calls.
     */
    public static Pair distance(Point[] pointsOrderedOnX,
                                int low, int high, Point[] pointsOrderedOnY) {

    }

    /**
     * Compute the distance between two points p1 and p2
     */
    public static double distance(Point p1, Point p2) {
        return new Pair(p1, p2).getDistance();
    }

    /**
     * Compute the distance between points (x1, y1) and (x2, y2)
     */
    public static double distance(double x1, double y1,
                                  double x2, double y2) {

    }
}
