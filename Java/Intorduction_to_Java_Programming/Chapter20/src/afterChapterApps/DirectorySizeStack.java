package afterChapterApps;

import java.io.File;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by robert on 08.08.16.
 */
public class DirectorySizeStack {

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
        Stack<File> fileStack = new Stack<>();
        fileStack.push(file);

        while (!fileStack.isEmpty()) {
            File temp = fileStack.pop();

            if (temp.isDirectory()) {
                File[] files = temp.listFiles();
                if (files != null) {
                    for (File f : files) {
                        fileStack.push(f);
                    }
                }
            } else {
                size += temp.length();
            }
        }

        return size;
    }
}