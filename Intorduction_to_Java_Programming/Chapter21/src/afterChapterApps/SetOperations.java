package afterChapterApps;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by robert on 16.8.2016.
 */
public class SetOperations {
    public static void main(String[] args) {
        String[] names1 = {"George", "Jim", "John", "Blake", "Kevin", "Michael"};
        String[] names2 = {"George", "Katie", "Kevin", "Michelle", "Ryan"};

        Set<String> set1 = new HashSet<>(Arrays.asList(names1));
        Set<String> set2 = new HashSet<>(Arrays.asList(names2));

        Set<String> union = new HashSet<>();
        union.addAll(set1);
        union.addAll(set2);

        System.out.println("Union: " + union);

        set1.removeAll(set2);
        System.out.println("Difference: " + set1);

        set1.addAll(Arrays.asList(names1));
        set1.retainAll(set2);

        System.out.println("Intersect: " + set1);
    }
}
