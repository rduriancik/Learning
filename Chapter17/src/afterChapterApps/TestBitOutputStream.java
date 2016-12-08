package afterChapterApps;

import java.io.File;
import java.io.IOException;

/**
 * This is a try class for class BitOutputStream
 * Created by robert on 05.01.16.
 */
public class TestBitOutputStream {
    public static void main(String[] args) throws IOException {
        File file = new File("Exercise17_17.dat");

        try (
                BitOutputStream stream = new BitOutputStream(file)
        ) {
            stream.writeBit("010000100100001001101");
        }
    }
}
