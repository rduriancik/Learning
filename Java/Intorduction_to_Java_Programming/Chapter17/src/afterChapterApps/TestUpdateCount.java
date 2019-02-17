package afterChapterApps;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by robert on 04.12.15.
 */
public class TestUpdateCount {
    public static void main(String[] args) throws IOException {
        try (RandomAccessFile inout = new RandomAccessFile("Exercise17_08.dat", "rw")) {
            System.out.print(inout.readInt());
            if (inout.length() == 0) {
                inout.writeInt(1);
            } else {
                int count = inout.readInt() + 1;
                inout.setLength(0);
                inout.writeInt(count);
            }
        }
    }
}
