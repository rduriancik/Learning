package afterChapterApps;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by robert on 29.8.2016.
 */
public class CountOccurrencesOfNumbers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter numbers: ");

        Map<String, Integer> map = new HashMap<>();
        String key = in.next();
        int max = Integer.MIN_VALUE;

        while (!key.equals("0")) {
            Integer occurrence = map.get(key);

            if (occurrence == null) {
                occurrence = 1;
                map.put(key, 1);
            } else {
                occurrence++;
                map.put(key, occurrence);
            }

            if (occurrence > max) {
                max = occurrence;
            }

            key = in.next();
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                System.out.println(entry.getKey());
            }
        }

    }
}
