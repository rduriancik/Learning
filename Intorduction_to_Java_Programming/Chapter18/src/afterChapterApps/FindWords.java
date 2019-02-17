package afterChapterApps;

import java.io.File;

/**
 * Created by rduriancik on 2/4/16.
 */
public class FindWords {

    public static void main(String[] args) {
        if (args.length != 2 || args[0].isEmpty() || args[1].isEmpty()) {
            System.out.println("Usage: java FindWords dirPath word");
            System.exit(1);
        }

        File directory = new File(args[0]);

        if (!directory.exists()) {
            System.out.println("Directory doesn't exists.");
            System.exit(1);
        }

        if (directory.isFile()) {
            System.out.println("The directory path cannot be a path to a file.");
            System.exit(1);
        }

        printFiles(directory, args[1]);

    }

    private static void printFiles(File directory, String word) {
        for (File f : directory.listFiles()) {
            if (f.isDirectory()) {
                printFiles(f, word);
                continue;
            }

            if (containsWord(f.getName(), word)) {
                System.out.println(f.toString());
            }
        }
    }

    private static boolean containsWord(String str, String word) {
        if (str.length() <= word.length()) {
            return str.equals(word);
        } else {
            String temp = str.substring(0, word.length());
            if (temp.equals(word)) {
                return true;
            }

            return containsWord(str.substring(1, str.length()), word);
        }
    }
}
