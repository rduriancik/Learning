package afterChapterApps;

import java.util.Scanner;

/**
 * Created by robert on 21.01.16.
 */
public class ComputeGCD {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter two numbers: ");

        int m = input.nextInt();
        int n = input.nextInt();

        System.out.println("The greatest common divisor of these numbers is " + gcd(m, n));
    }

    public static int gcd(int m, int n) {
        return m % n == 0 ? n : gcd(n, m % n);
    }
}
