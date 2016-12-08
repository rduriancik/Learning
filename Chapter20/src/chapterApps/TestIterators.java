package chapterApps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by robert on 8.4.2016.
 */
public class TestIterators {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        collection.add("New York");
        collection.add("Atlanta");
        collection.add("Dallas");
        collection.add("Madison");

        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next().toUpperCase() + " ");
        }
        System.out.println();
    }
}
