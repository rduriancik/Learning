package afterChapterApps;

import java.util.Scanner;

/**
 * Created by robert on 23.01.16.
 */
public class StringPermutation {

    public static void main(String[] args) {
        System.out.print("Enter a string: ");
        Scanner input = new Scanner(System.in);

        String str = input.nextLine();

        System.out.println("Permutations:");
        displayPermutation(str);
    }

    public static void displayPermutation(String str) {
        displayPermutation(" ", str);
    }

    private static void displayPermutation(String s1, String s2) {
        if (s2.isEmpty()) {
            System.out.println(s1);
        } else {
            for (int i = 0; i < s2.length(); i++) {
                displayPermutation(s1 + s2.charAt(i), new StringBuilder(s2).deleteCharAt(i).toString());
            }
        }
    }
}
