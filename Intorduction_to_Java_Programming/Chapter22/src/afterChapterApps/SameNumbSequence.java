package afterChapterApps;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by robert on 20.9.2016.
 */
public class SameNumbSequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter a series of numbers ending with 0:");
        List<Integer> list = new ArrayList<>();

        int temp = in.nextInt();
        while (temp != 0) {
            list.add(temp);
            temp = in.nextInt();
        }

        findLongestSubsequence(list);
    }

    private static void findLongestSubsequence(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("No numbers entered.");
            return;
        }

        int startIndex = 0;
        int lastNumb = list.get(0);
        int value = lastNumb;
        int maxCount = 1;
        int count = 1;

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) == lastNumb) {
                count++;
            } else {
                count = 1;
            }

            if (count > maxCount) {
                maxCount = count;
                value = list.get(i);
                startIndex = i - count + 1;
            }

            lastNumb = list.get(i);
        }

        System.out.println("The longest same number sequence starts at index "
                + startIndex + " with " + maxCount + " values of " + value);
    }
}
