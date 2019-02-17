package chapterApps;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by robert on 13.8.2016.
 */
public class TestMethodsInCollection {
    public static void main(String[] args) {
        // Create set1
        Set<String> set1 = new HashSet<>();

        // Add strings to set1
        set1.add("London");
        set1.add("Paris");
        set1.add("Beijing");
        set1.add("San Francisco");
        set1.add("New York");

        System.out.println("Set1 is " + set1);
        System.out.println(set1.size() + " elements in set1");

        // Delete a string from set1
        set1.remove("London");
        System.out.println("\nSet1 is " + set1);
        System.out.println(set1.size() + " elements in set1");

        // Create set2
        Set<String> set2 = new HashSet<>();

        // Add strings to set2
        set2.add("London");
        set2.add("Shanghai");
        set2.add("Paris");
        System.out.println("\nSet2 is " + set2);
        System.out.println(set2.size() + " elements in set2");

        System.out.println("\nIs Taipei in set2? " + set2.contains("Taipei"));

        set1.addAll(set2);
        System.out.println("\nAfter adding set2 to set1, set1 is " + set1);

        set1.removeAll(set2);
        System.out.println("\nAfter removing set2 from set1, set1 is " + set1);

        set1.retainAll(set2);
        System.out.println("\nAfter removing common elements in set2 from set1, set1 is " +
                set1);
    }
}
