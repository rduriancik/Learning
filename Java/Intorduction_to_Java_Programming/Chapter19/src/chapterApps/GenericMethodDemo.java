package chapterApps;

/**
 * Created by robert on 21.3.2016.
 */
public class GenericMethodDemo {
    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] strings = {"London", "New York", "Paris", "Austin"};

        GenericMethodDemo.print(integers);
        GenericMethodDemo.print(strings);
    }

    public static <E> void print(E[] list) {
        for(int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }

        System.out.println();
    }
}
