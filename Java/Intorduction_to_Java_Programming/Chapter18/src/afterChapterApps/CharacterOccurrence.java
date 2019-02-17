package afterChapterApps;

import java.util.Scanner;

/**
 * Created by robert on 21.01.16.
 */
public class CharacterOccurrence {

    public static void main(String[] args) {
        System.out.print("Enter a string and character: ");
        Scanner input = new Scanner(System.in);

        String str = input.nextLine();
        System.out.println("The occurrence of the character " + str.split(" ")[1]
                + " in the string " + str.split(" ")[0] + " is "
                + count(str.split(" ")[0], str.split(" ")[1].charAt(0)));
    }

    public static int count(String str, char a) {
        int count = 0;
        if (str.length() == 0) {
            return count;
        } else {
            if (str.charAt(str.length() - 1) == a) {
                count++;
            }
            return count + count(str.substring(0, str.length() - 1), a);
        }
    }
}
