package afterChapterApps;

import java.io.*;
import java.util.Scanner;

/**
 * Created by robert on 01.12.15.
 */
public class TestConvertTextFileIntoUTF {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java TestConvertTextFileIntoUTF sourceFile targetFile");
            System.exit(1);
        }

        File sourceFile = new File(args[0]);
        if (!sourceFile.exists()) {
            System.out.println("Source file doesn't exist");
            System.exit(2);
        }

        File targetFile = new File(args[1]);
        if (targetFile.exists()) {
            System.out.println("Target file will be rewritten");
        }

        try (Scanner input =
                     new Scanner(sourceFile);
             DataOutputStream output =
                     new DataOutputStream(new BufferedOutputStream(new FileOutputStream(targetFile)))
        ) {
            while (input.hasNextLine()) {
                output.writeUTF(input.nextLine());
            }
        }

        try (DataInputStream input1 = new DataInputStream(new BufferedInputStream(new FileInputStream(sourceFile)));
             DataInputStream input2 = new DataInputStream(new BufferedInputStream(new FileInputStream(targetFile)))
        ) {
            int bytes;
            int count = 0;

            while ((bytes = input1.read()) != -1) {
                count++;
            }
            System.out.println("Source file size: " + count + " bytes");

            while ((bytes = input2.read()) != -1) {
                count++;
            }
            System.out.println("Target file size: " + count + " bytes");
        }
    }
}
