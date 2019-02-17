package chapterApps.afterChapterApps;

import java.util.Arrays;

/**
 * Created by robert on 4.4.2016.
 */
public class TestGenericMethods {
    public static void main(String[] args) {
        Integer[] list = new Integer[]{2, 3, 5, 7, 6, 8, 1, 10, 0, 9};

        GenericMethods.sort(list);
        System.out.println(Arrays.toString(list));

        int index = GenericMethods.linearSearch(list, 0);
        System.out.println(list[index] + "");

        index = GenericMethods.binarySearch(list, 0);
        System.out.println(list[index] + "");

        System.out.println(GenericMethods.max(list).toString());

        GenericMethods.shuffle(list);
        System.out.println(Arrays.toString(list));
    }
}
