package afterChapterApps;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Created by robert on 16.8.2016.
 */
public class NonduplicatedWords {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java NonduplicatedWords \"filename\"");
            System.exit(1);
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            System.err.println("File not found: " + file);
            System.exit(1);
        }

        printWords(file);
    }

    private static void printWords(File file) throws Exception {
        Scanner reader = new Scanner(new BufferedReader(new FileReader(file)));
        Set<String> set = new TreeSet<>();

        while (reader.hasNext()) {
            String word = reader.next();
            set.addAll(Arrays.asList(word.split("[.;,([}]{)\":<>]")));
        }

        for (String s : set) {
            System.out.println(s);
        }
    }
}
