package afterChapterApps;

/**
 * Created by robert on 21.01.16.
 */
public class SumSeries {
    public static void main(String[] args) {
        System.out.println("Sum series 1/i");
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + ": " + sum(i));
        }
    }

    public static double sum(int i) {
        if (i == 1) {
            return 1;
        } else {
            return (1.0 / i) + sum(i - 1);
        }
    }
}
