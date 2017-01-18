package afterChapterApps;

import java.util.Scanner;

/**
 * Created by robert on 23.01.16.
 */
public class Bin2Dec {
    public static void main(String[] args) {
        System.out.print("Enter a binary number: ");
        Scanner input = new Scanner(System.in);

        String number = input.nextLine();

        System.out.println("The binary form of the number " + number + " is " + bin2Dec(number));
    }

    public static int bin2Dec(String n) {
        if (!isBin(n)) {
            return -1;
        }

        return bin2Dec(n, 0);
    }

    private static int bin2Dec(String n, int exponent) {
        int temp = Integer.parseInt(Character.toString(n.charAt(n.length() - 1)))
                * (int) Math.pow(2, exponent);

        if (n.length() == 1) {
            return temp;
        } else {
            return temp + bin2Dec(n.substring(0, n.length() - 1), exponent + 1);
        }
    }

    private static boolean isBin(String n) {
        if (n == null || n.isEmpty()) {
            return false;
        }

        for (char ch : n.toCharArray()) {
            if (ch != '1' && ch != '0') {
                return false;
            }
        }

        return true;
    }
}

