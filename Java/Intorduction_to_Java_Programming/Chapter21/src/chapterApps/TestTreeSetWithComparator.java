package chapterApps;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by robert on 13.8.2016.
 */
public class TestTreeSetWithComparator {

    private static Comparator<Integer> reverseIntegerComparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    };

    public static void main(String[] args) {
        // Create a tree set for integers using comparator
        Set<Integer> set = new TreeSet<>(reverseIntegerComparator);

        set.add(1);
        set.add(2);
        set.add(5);
        set.add(8);
        set.add(4);
        set.add(6);

        // Display integers in the tree set
        System.out.println("A sorted set of integers");
        for (Integer i : set) {
            System.out.println(i);
        }
    }
}
