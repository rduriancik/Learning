package afterChapterApps.comparePoints;

import java.util.Arrays;

/**
 * Created by robert on 2.5.2016.
 */
public class TestPoints {
    public static void main(String[] args) {
        Point[] points = new Point[100];

        for(int i = 0; i < points.length; i++) {
            points[i] = new Point((int)(Math.random() * 100) / 10.0, (int)(Math.random() * 100) / 10.0);
        }

        Arrays.sort(points);
        System.out.println("Natural sorted points: " + Arrays.toString(points));
        Arrays.sort(points, new CompareY());
        System.out.println("Natural sorted points: " + Arrays.toString(points));
    }
}
