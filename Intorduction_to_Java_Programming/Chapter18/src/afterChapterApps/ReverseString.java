package afterChapterApps;

import java.util.Scanner;

/**
 * Created by robert on 21.01.16.
 */
public class ReverseString {
    public static void main(String[] args) {
        System.out.print("Enter a string: ");
        Scanner input = new Scanner(System.in);
        String string = input.nextLine();

        reverseDisplay(string);
    }

    public static void reverseDisplay(String string) {
        if (string.length() == 0) {
            System.out.println();
        } else {
            System.out.print(string.charAt(string.length() - 1));
            reverseDisplay(string.substring(0, string.length() - 1));
        }
    }
}
