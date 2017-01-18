package afterChapterApps;

import java.util.Scanner;

/**
 * Created by robert on 20.9.2016.
 */
public class MaxConsecIncreasOrderSubstr {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = in.nextLine();
        System.out.println(getResult(str));
    }

    private static String getResult(String str) {
        String result = "";
        int end;

        for (int i = 0; i < str.length() - 1; i = end) {
            end = i + 1;
            while (end < str.length()
                    && Character.compare(str.charAt(end - 1), str.charAt(end)) <= 0) {
                end++;
            }

            if (result.length() < end - i) {
                result = str.substring(i, end);
            }
        }

        return result;
    }
}
