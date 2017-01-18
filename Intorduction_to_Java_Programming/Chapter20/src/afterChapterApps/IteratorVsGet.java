package afterChapterApps;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by robert on 3.5.2016.
 */
public class IteratorVsGet {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < 5000; i++) {
            list.add((int) (Math.random() * 1000));
        }

        long time = System.currentTimeMillis();
        int temp = 0;
        for (int i = 0; i < 5000; i++) {
            temp = +list.get(i);
        }
        time = System.currentTimeMillis() - time;

        System.out.println("Traverse using get(index): " + time + " last: " + temp);

        time = System.currentTimeMillis();
        for (Integer i : list) {
            temp = +i;
        }
        time = System.currentTimeMillis() - time;

        System.out.println("Traverse using for-each loop: " + time + " last: " + temp);

        time = System.currentTimeMillis();
        list.forEach((i) -> i += i);
        time = System.currentTimeMillis() - time;
        System.out.println("Traverse using for-each method: " + time + " last: " + temp);

        Iterator<Integer> it = list.listIterator();

        time = System.currentTimeMillis();
        while (it.hasNext()) {
            temp += it.next();
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Traverse using iterator: " + time + " last: " + temp);
    }
}
