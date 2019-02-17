package chapterApps.afterChapterApps;

/**
 * Created by robert on 4.4.2016.
 */
public class GenericMethods {
    public static <E extends Comparable<E>> int linearSearch(E[] list, E key) {
        if (list == null || key == null) {
            return -1;
        }

        for (int i = 0; i < list.length; i++) {
            if (list[i] == key) {
                return i;
            }
        }

        return -1;
    }

    public static <E extends Comparable<E>> void shuffle(E[] list) {
        if (list == null || list.length <= 1) {
            return;
        }

        for (int i = 0; i < list.length; i++) {
            swap(list, i, (int) (Math.random() * list.length * 10) % list.length);
        }
    }

    public static <E extends Comparable<E>> E max(E[] list) {
        if (list == null || list.length == 0) {
            return null;
        }

        E max = list[0];
        for (int i = 1; i < list.length; i++) {
            if (max.compareTo(list[i]) < 0) {
                max = list[i];
            }
        }

        return max;
    }

    public static <E extends Comparable<E>> int binarySearch(E[] list, E key) {
        if (list == null || key == null) {
            return -1;
        }

        sort(list);

        int left = 0;
        int right = list.length;
        int mid = (left + right) / 2;


        while (left <= right) {
            if (list[mid].compareTo(key) == 0) {
                return mid;
            } else if (list[mid].compareTo(key) < 0) {
                left = mid + 1;
                mid = (left + right) / 2;
            } else {
                right = mid - 1;
                mid = (left + right) / 2;
            }
        }

        return -1;
    }

    public static <E extends Comparable<E>> void sort(E[] list) {
        if (list == null) {
            return;
        }

        for (int i = 0; i < list.length; i++) {
            for (int j = i; j > 0 && list[j].compareTo(list[j - 1]) <= 0; j--) {
                swap(list, j, j - 1);
            }
        }
    }

    private static <E extends Comparable<E>> void swap(E[] list, int pos1, int pos2) {
        E temp = list[pos1];
        list[pos1] = list[pos2];
        list[pos2] = temp;
    }

}
