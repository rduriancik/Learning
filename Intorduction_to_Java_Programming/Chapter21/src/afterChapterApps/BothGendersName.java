package afterChapterApps;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by robert on 30.8.2016.
 */
public class BothGendersName {

    private static Set<String> male = new HashSet<>();
    private static Set<String> female = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a filename for baby name ranking: ");
        String filename = "src/afterChapterApps/namePopularity/" + scanner.nextLine();
        if (!isValidFile(filename)) {
            System.err.println("Invalid file name, must be babynamesranking\"2001 - 2010\".txt");
            System.exit(1);
        }

        Path file = Paths.get(filename);
        if (Files.notExists(file)) {
            System.out.println("File " + filename + " does not exist");
            System.exit(1);
        }

        if (!Files.isReadable(file)) {
            System.out.println("Cannot read from file " + filename);
            System.exit(1);
        }

        loadFile(file);
        printConcurrence();
    }

    private static boolean loadFile(Path file) {
        if (file == null) {
            throw new NullPointerException("File is null");
        }

        try (
                BufferedReader reader = Files.newBufferedReader(file)
        ) {
            String line = reader.readLine();

            while (line != null) {
                String[] parsed = line.split("\\s+");

                male.add(parsed[1]);
                female.add(parsed[3]);

                line = reader.readLine();
            }

        } catch (IOException ex) {
            System.err.println("Error while reading a file");
            return false;
        }

        return true;
    }

    private static boolean isValidFile(String filename) {
        filename = filename.substring(filename.lastIndexOf('/') + 1, filename.length());
        return Pattern.matches("babynamesranking200[1-9]\\.txt|babynamesranking2010\\.txt", filename);
    }

    private static void printConcurrence() {
        if (male == null || female == null) {
            throw new NullPointerException("Male or female set is null");
        }

        male.retainAll(female);

        System.out.println(male.size() + " names used for both genders");
        System.out.print("They are ");
        for (String s : male) {
            System.out.print(s + " ");
        }
        System.out.println();

    }
}
