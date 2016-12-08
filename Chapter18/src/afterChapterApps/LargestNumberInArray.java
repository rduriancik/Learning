package afterChapterApps;

import java.util.Scanner;

/**
 * Created by robert on 21.01.16.
 */
public class LargestNumberInArray {

    public static void main(String[] args) {
        System.out.print("Enter 8 numbers: ");
        Scanner input = new Scanner(System.in);

        int[] array = new int[8];
        for (int i = 0; i < 8; i++) {
            array[i] = input.nextInt();
        }

        System.out.println("The largest number in the array is " + largestNumber(array));
    }

    public static int largestNumber(int[] array) {
        return largestNumber(array, array.length - 1);
    }

    private static int largestNumber(int[] array, int high) {
        if (high == -1) {
            return Integer.MIN_VALUE;
        } else {
            return array[high] > largestNumber(array, high - 1)
                    ? array[high] : largestNumber(array, high - 1);
        }
    }
}
