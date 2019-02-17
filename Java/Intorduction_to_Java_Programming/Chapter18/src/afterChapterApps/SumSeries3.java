package afterChapterApps;

/**
 * Created by robert on 21.01.16.
 */
public class SumSeries3 {
    public static void main(String[] args) {
        System.out.println("Sum series 1/i");
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + ": " + sum(i));
        }
    }

    public static double sum(int n) {
        if (n == 1) {
            return 1 / 2.0;
        } else {
            return ((double) n / (n + 1)) + sum(n - 1);
        }
    }
}
