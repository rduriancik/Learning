package afterChapterApps;

import java.util.Scanner;

/**
 * Created by robert on 22.01.16.
 */
public class UppercaseLetters {
    public static void main(String[] args) {
        System.out.print("Enter a string: ");
        Scanner input = new Scanner(System.in);

        String str = input.nextLine();
        uppercaseLetters(str);
    }

    public static void uppercaseLetters(String str) {
        uppercaseLetters(str, 0);
    }

    private static void uppercaseLetters(String str, int high) {
        if (high >= str.length()) {
            System.out.println();
        } else {
            char chr = str.charAt(high);
            if (chr >= 'A' && chr <= 'Z') {
                System.out.println(chr + ": " + (int) chr);
            }
            uppercaseLetters(str, high + 1);
        }
    }
}
