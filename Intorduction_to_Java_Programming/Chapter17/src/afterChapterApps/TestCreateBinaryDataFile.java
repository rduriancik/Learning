package afterChapterApps;

import java.io.*;

/**
 * Created by robert on 29.11.15.
 */
public class TestCreateBinaryDataFile {
    public static void main(String[] args) throws IOException {
        File file = new File("Exercise17_02.dat");

        try (DataOutputStream output =
                     new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file, file.exists())));
             DataInputStream input =
                     new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            for (int i = 0; i < 100; i++) {
                output.write((int) (Math.random() * 100));
                System.out.print(input.read() + " ");
            }
        }
    }
}
