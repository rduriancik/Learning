package chapterApps;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by robert on 13.8.2016.
 */
public class TestLinkedHashSet {
    public static void main(String[] args) {
        // Create a linked hash set
        Set<String> set = new LinkedHashSet<>();

        // Add strings to the set
        set.add("London");
        set.add("Paris");
        set.add("New York");
        set.add("San Francisco");
        set.add("Beijing");
        set.add("New York");

        System.out.println(set);

        // Display the elements in the hash set
        for (String s : set) {
            System.out.print(s.toUpperCase() + " ");
        }
    }
}
