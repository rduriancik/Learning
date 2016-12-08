package afterChapterApps;

import java.io.*;
import java.util.Scanner;

/**
 * Created by robert on 05.01.16.
 */
public class TestFrequencyOfCharacters {

    private static int[] chars = new int[128];

    public static void main(String[] args) throws IOException {
        System.out.println("Enter a path to a ASCII file: ");

        Scanner input = new Scanner(System.in);
        File file = new File(input.nextLine());

        if (!file.exists()) {
            System.out.println("File doesn't exist.");
            System.exit(1);
        }

        if (file.isDirectory()) {
            System.out.println("File cannot be a directory.");
            System.exit(1);
        }

        try (
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(file)))
        ) {
            int character = inputStream.read();
            while (character != -1) {
                addCharacter(character);
                character = inputStream.read();
            }
        }

        printoutCount();
    }

    private static void addCharacter(int ch) {
        chars[ch]++;
    }

    private static void printoutCount() {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != 0) {
                System.out.printf("%c: %d\n", (char) i, chars[i]);
            }
        }
    }
}
