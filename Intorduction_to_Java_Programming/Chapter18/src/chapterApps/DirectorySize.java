package chapterApps;

import java.io.File;
import java.util.Scanner;

/**
 * Created by robert on 14.01.16.
 */
public class DirectorySize {
    public static void main(String[] args){
        // Prompt the user to enter a directory or a file
        System.out.print("Enter a directory or a file: ");
        Scanner input = new Scanner(System.in);
        String directory = input.nextLine();

        // Display the size
        System.out.println(getSize(new File(directory)) + " bytes");
    }

    public static long getSize(File file){
        long size = 0; // Store the total size of all files

        if(file.isDirectory()){
            File[] files = file.listFiles(); // All files and subdirectories
            for(int i = 0; files != null && i < files.length; i++){
                size += getSize(files[i]);
            }
        } else {
            size += file.length();
        }

        return size;
    }
}
