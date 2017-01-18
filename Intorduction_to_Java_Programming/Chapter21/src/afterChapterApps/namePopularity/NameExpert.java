package afterChapterApps.namePopularity;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by robert on 30.8.2016.
 */
public class NameExpert {

    private Map<String, Integer[]> male;
    private Map<String, Integer[]> female;
    private boolean loaded = false;

    public NameExpert() {
        if (load()) {
            loaded = true;
        }
    }

    public String getRank(String name, String gender, int year) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }

        if (gender == null) {
            throw new NullPointerException("gender cannot be null");
        }

        if (year < 2001 || year > 2010) {
            return "Year must be between 2001 and 2010 included";
        }

        if (!loaded) {
            return "Files with the names are not loaded";
        }

        name = name.toLowerCase();

        if (gender.equals("Male")) {
            Integer[] temp = male.get(name);
            name = getName(name);

            if (temp != null && temp[year - 2001] != null) {
                return "Boy name " + name + " is ranked #" + temp[year - 2001] + " in year " + year;
            } else {
                return "Boy name " + name + " is not ranked in year " + year;
            }
        } else if (gender.equals("Female")) {
            Integer[] temp = female.get(name);
            name = getName(name);

            if (temp != null && temp[year - 2001] != null) {
                return "Girl name " + name + " is ranked #" + temp[year - 2001] + " in year " + year;
            } else {
                return "Girl name " + name + " is not ranked in year " + year;
            }
        } else {
            return "Gender must be either male or female";
        }

    }

    private String getName(String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1, name.length());
    }

    private boolean load() {
        this.male = new HashMap<>();
        this.female = new HashMap<>();

        for (int i = 2001; i <= 2010; i++) {
            Path file = Paths.get("src/afterChapterApps/namePopularity/babynamesranking" + i + ".txt");

            try (
                    BufferedReader reader = Files.newBufferedReader(file)
            ) {

                String line = reader.readLine();
                while (line != null) {
                    String[] parsed = line.split("\\s+");
                    parsed[1] = parsed[1].toLowerCase();
                    parsed[3] = parsed[3].toLowerCase();

                    Integer[] temp;
                    if (this.male.containsKey(parsed[1])) {
                        temp = this.male.get(parsed[1]);
                        temp[i - 2001] = Integer.valueOf(parsed[0]);
                        this.male.put(parsed[1], temp);
                    } else {
                        temp = new Integer[10];
                        temp[i - 2001] = Integer.valueOf(parsed[0]);
                        this.male.put(parsed[1], temp);
                    }

                    if (this.female.containsKey(parsed[3])) {
                        temp = this.female.get(parsed[3]);
                        temp[i - 2001] = Integer.valueOf(parsed[0]);
                        this.female.put(parsed[3], temp);
                    } else {
                        temp = new Integer[10];
                        temp[i - 2001] = Integer.valueOf(parsed[0]);
                        this.female.put(parsed[3], temp);
                    }

                    line = reader.readLine();
                }

            } catch (IOException ex) {
                System.err.println("Error while reading a file: " + ex);
                return false;
            }
        }

        return true;
    }
}
