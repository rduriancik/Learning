package afterChapterApps;

import java.util.Scanner;

/**
 * Created by robert on 23.01.16.
 */
public class Dec2Hex {
    public static void main(String[] args) {
        System.out.print("Enter a number: ");
        Scanner input = new Scanner(System.in);

        int number = input.nextInt();

        System.out.println("The Hexadecimal form of the number " + number + " is " + dec2Hex(number));
    }

    public static String dec2Hex(int n) {
        if (n == 0) {
            return "";
        } else {
            String temp = "";
            switch (n % 16) {
                case 10:
                    temp = "A";
                    break;
                case 11:
                    temp = "B";
                    break;
                case 12:
                    temp = "C";
                    break;
                case 13:
                    temp = "D";
                    break;
                case 14:
                    temp = "E";
                    break;
                case 15:
                    temp = "F";
                    break;
                default:
                    temp = n % 16 + "";
            }

            return dec2Hex(n / 16) + temp;
        }
    }
}
