package afterChapterApps;

import java.util.Scanner;

/**
 * Created by robert on 20.9.2016.
 */
public class PatternMatching {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a string s1: ");
        String s1 = in.nextLine();
        System.out.print("Enter a string s2: ");
        String s2 = in.nextLine();

        System.out.println("Matched at index: " + getIndex(s1, s2));
    }

    private static int getIndex(String s1, String s2) {
        for (int i = 0; i < s1.length() - (s2.length() - 1); i++) {
            if (s2.equals(s1.substring(i, i + s2.length()))) {
                return i;
            }
        }

        return -1;
    }
}
