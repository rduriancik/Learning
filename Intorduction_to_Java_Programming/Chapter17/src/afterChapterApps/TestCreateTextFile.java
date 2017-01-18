package afterChapterApps;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by robert on 29.11.15.
 */
public class TestCreateTextFile {
    public static void main(String[] args) throws IOException {
        File file = new File("Exercise17_10.txt");
        boolean append = file.exists();

        try (PrintWriter output =
                     new PrintWriter(new FileOutputStream(file, append))) {
            for (int i = 0; i < 100; i++) {
                output.print((int) (Math.random() * 100));
                output.print(' ');
            }
        }
    }
}
