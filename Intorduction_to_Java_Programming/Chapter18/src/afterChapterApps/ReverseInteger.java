package afterChapterApps;

import java.util.Scanner;

/**
 * Created by robert on 21.01.16.
 */
public class ReverseInteger {
    public static void main(String[] args) {
        System.out.print("Enter an integer: ");
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();

        reverseDisplay(number);
    }

    public static void reverseDisplay(int n) {
        if (n == 0) {
            System.out.println();
        } else {
            System.out.print(n % 10);
            reverseDisplay(n / 10);
        }
    }
}
