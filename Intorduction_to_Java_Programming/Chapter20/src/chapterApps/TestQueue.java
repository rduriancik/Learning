package chapterApps;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by robert on 13.4.2016.
 */
public class TestQueue {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        queue.offer("Oklahoma");
        queue.offer("Indiana");
        queue.offer("Georgia");
        queue.offer("Texas");

        while (queue.size() > 0) {
            System.out.println(queue.remove() + "");
        }
    }
}
