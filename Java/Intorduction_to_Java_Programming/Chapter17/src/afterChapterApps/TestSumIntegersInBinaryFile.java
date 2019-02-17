package afterChapterApps;

import java.io.*;

/**
 * Created by robert on 30.11.15.
 */
public class TestSumIntegersInBinaryFile {
    public static void main(String[] args) throws IOException {
        try (DataOutputStream output =
                     new DataOutputStream(new FileOutputStream("Exercise17_03.dat"));
             DataInputStream input =
                     new DataInputStream(new FileInputStream("Exercise17_03.dat"))
        ) {
            for (int i = 0; i < 10; i++) {
                output.write((int) (Math.random() * 10));
            }
            int r;
            int sum = 0;
            while ((r = input.read()) != -1) {
                System.out.print(r + " ");
                sum += r;
            }

            System.out.println("\nSum of numbers is: " + sum);
        }
    }
}
