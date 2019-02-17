package afterChapterApps;

import java.util.Scanner;

/**
 * Created by robert on 23.01.16.
 */
public class Dec2Bin {

    public static void main(String[] args) {
        System.out.print("Enter a number: ");
        Scanner input = new Scanner(System.in);

        int number = input.nextInt();

        System.out.println("The binary form of the number " + number + " is " + dec2Bin(number));
    }

    public static String dec2Bin(int n) {
        if (n == 0) {
            return "";
        } else {
            return dec2Bin(n / 2) + n % 2;
        }
    }
}
