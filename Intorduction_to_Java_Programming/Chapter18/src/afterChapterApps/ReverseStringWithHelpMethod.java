package afterChapterApps;

import java.util.Scanner;

/**
 * Created by robert on 21.01.16.
 */
public class ReverseStringWithHelpMethod {
    public static void main(String[] args) {
        System.out.print("Enter a string: ");
        Scanner input = new Scanner(System.in);

        String str = input.nextLine();
        reverseDisplay(str);
    }

    public static void reverseDisplay(String value) {
        reverseDisplay(value, value.length() - 1);
    }

    public static void reverseDisplay(String value, int high) {
        if (high == -1) {
            System.out.println();
        } else {
            System.out.print(value.charAt(high));
            reverseDisplay(value, high - 1);
        }
    }
}
