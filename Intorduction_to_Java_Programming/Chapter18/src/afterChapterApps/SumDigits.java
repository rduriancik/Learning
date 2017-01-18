package afterChapterApps;

import java.util.Scanner;

/**
 * Created by robert on 21.01.16.
 */
public class SumDigits {
    public static void main(String[] args) {
        System.out.println("Enter a number: ");
        Scanner input = new Scanner(System.in);

        long number = input.nextLong();

        System.out.println("Sum the digits in an integer " + number
                + " is " + sumDigits(number));
    }

    public static int sumDigits(long n) {
        if (n < 10 && n > -10) {
            return (int) n;
        } else {
            return (int) ((n % 10) + sumDigits(n / 10));
        }
    }
}
