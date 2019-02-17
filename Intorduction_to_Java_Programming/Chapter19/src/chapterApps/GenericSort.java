package chapterApps;

/**
 * Created by robert on 21.3.2016.
 */
public class GenericSort {
    public static void main(String[] args) {
        Integer[] intArray = {2, 4, 3};
        Double[] doubleArray = {3.4, 1.3, -22.1};
        Character[] charArray = {'a', 'J', 'r'};
        String[] stringArray = {"Tom", "Susan", "Kim"};

        sort(intArray);
        sort(doubleArray);
        sort(charArray);
        sort(stringArray);

        System.out.print("Sorted integer objects: ");
        printList(intArray);
        System.out.print("Sorted double objects: ");
        printList(doubleArray);
        System.out.print("Sorted char objects: ");
        printList(charArray);
        System.out.print("Sorted string objects: ");
        printList(stringArray);
    }

    public static <E extends Comparable<E>> void sort(E[] array) {
        E currentMin;
        int currentMinIndex;

        for (int i = 0; i < array.length - 1; i++) {
            currentMin = array[i];
            currentMinIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (currentMin.compareTo(array[j]) > 0) {
                    currentMin = array[j];
                    currentMinIndex = j;
                }
            }

            if (currentMinIndex != i) {
                array[currentMinIndex] = array[i];
                array[i] = currentMin;
            }
        }
    }

    public static void printList(Object[] array){
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

        System.out.println();
    }
}
