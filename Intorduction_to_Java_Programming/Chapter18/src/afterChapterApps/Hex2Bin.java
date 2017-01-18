package afterChapterApps;

import java.util.Scanner;

/**
 * Created by robert on 23.01.16.
 */
public class Hex2Bin {
    public static void main(String[] args) {
        System.out.print("Enter a hexadecimal number: ");
        Scanner input = new Scanner(System.in);

        String number = input.nextLine();

        System.out.println("The Hexadecimal form of the number " + number + " is " + hex2Dec(number));
    }

    public static int hex2Dec(String n) {
        n = n.toUpperCase();

        if (!isHex(n)) {
            return -1;
        }
        return hex2Dec(n, 0);
    }

    private static int hex2Dec(String n, int exponent) {
        int temp = 0;

        switch (n.charAt(n.length() - 1)) {
            case 'A':
                temp = 10;
                break;
            case 'B':
                temp = 11;
                break;
            case 'C':
                temp = 12;
                break;
            case 'D':
                temp = 13;
                break;
            case 'E':
                temp = 14;
                break;
            case 'F':
                temp = 15;
                break;
            default:
                temp = Integer.parseInt(Character.toString(n.charAt(n.length() - 1)));
        }

        if (n.length() == 1) {
            return temp * (int) Math.pow(16, exponent);
        } else {
            return temp * (int) Math.pow(16, exponent)
                    + hex2Dec(n.substring(0, n.length() - 1), exponent + 1);
        }
    }

    private static boolean isHex(String n) {
        if (n == null || n.isEmpty()) {
            return false;
        }

        for (char ch : n.toCharArray()) {
            if (!Character.isDigit(ch) && ch != 'A'
                    && ch != 'B' && ch != 'C'
                    && ch != 'D' && ch != 'E'
                    && ch != 'F') {
                return false;
            }
        }

        return true;
    }
}
