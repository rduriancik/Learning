package afterChapterApps;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by robert on 15.8.2016.
 */
public class CountOccurrenceOfWordsExtra2 {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: java CountOccurrenceOfWordsExtra2 \"textFile\"");
            System.exit(1);
        }

        Path file = Paths.get(args[0]);
        if (Files.notExists(file)) {
            System.err.println("File does not exists");
            System.exit(2);
        }

        if (!Files.isReadable(file)) {
            System.err.println("File cannot be read");
            System.exit(3);
        }

        Map<String, Integer> map = new TreeMap<>();

        try (
                BufferedReader reader = Files.newBufferedReader(file)
        ) {
            String text = reader.readLine();

            while (text != null) {

                String[] words = text.split("[ \\\\n\\t\\r.,;:!?(){}<>\"\'\\]\\[]");
                for (int i = 0; i < words.length; i++) {
                    String key = words[i].toLowerCase();

                    if (key.length() > 0 && Character.isLetter(key.charAt(0))) {
                        if (!map.containsKey(key)) {
                            map.put(key, 1);
                        } else {
                            int value = map.get(key);
                            value++;
                            map.put(key, value);
                        }
                    }
                }

                text = reader.readLine();
            }
        } catch (IOException ex) {
            System.err.println("Error while reading the file");
        }

        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

        for (Map.Entry<String, Integer> entry : entrySet) {
            System.out.println(entry.getValue() + "\t" + entry.getKey());
        }
    }
}
