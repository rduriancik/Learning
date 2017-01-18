package afterChapterApps;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by robert on 08.08.16.
 */
public class DirectorySizeQueue {

    public static void main(String[] args) {
        // Prompt the user to enter a directory or a file
        System.out.print("Enter a directory or a file: ");
        Scanner input = new Scanner(System.in);
        String directory = input.nextLine();

        // Display the size
        System.out.println(getSize(new File(directory)) + " bytes");
    }

    public static long getSize(File file) {
        long size = 0;
        Queue<File> fileQueue = new LinkedList<>();
        fileQueue.offer(file);

        while (!fileQueue.isEmpty()) {
            File temp = fileQueue.poll();

            if (temp.isDirectory()) {
                File[] files = temp.listFiles();
                if (files != null) {
                    for (File f : files) {
                        fileQueue.offer(f);
                    }
                }
            } else {
                size += temp.length();
            }
        }

        return size;
    }
}