package chapterApps;

/**
 * Created by robert on 14.01.16.
 */
public class RecursiveBinarySearch {
    public static int recursiveBinarySearch(int[] list, int key){
        return recursiveBinarySearch(list, key, 0, list.length -1);
    }

    public static int recursiveBinarySearch(int[] list, int key, int low, int high){
        if(low > high){
            return -low - 1; // the list has been exhausted without a match
        }

        int mid = (low + high) / 2;
        if(key < list[mid]){
            return recursiveBinarySearch(list, key, low, mid - 1);
        } else if (key == list[mid]){
            return mid;
        } else {
            return recursiveBinarySearch(list, key, mid + 1, high);
        }
    }
}
