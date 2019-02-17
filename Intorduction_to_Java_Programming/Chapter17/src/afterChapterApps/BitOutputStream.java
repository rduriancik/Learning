package afterChapterApps;

import java.io.*;

/**
 * Created by robert on 05.01.16.
 */
public class BitOutputStream implements Closeable {

    private StringBuilder b;
    private int byteLength;
    private DataOutputStream outputStream;

    BitOutputStream(File file) throws IOException {
        // Create the file if doesn't exist
        file.createNewFile();

        // Initialize attributes
        b = new StringBuilder(8);
        byteLength = 0;

        // Open the outputStream
        outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
    }

    public void writeBit(char bit) throws IOException {
        if (!isBit(bit)) {
            throw new IllegalArgumentException("Only 1 or 0");
        }

        byteLength++;

        b.append(bit);

        if (byteLength % 8 == 0) {
            flush();
        }
    }

    public void writeBit(String bit) throws IOException {
        if (!isBit(bit)) {
            throw new IllegalArgumentException("Only 1 or 0");
        }

        for (int i = 0; i < bit.length(); i++) {
            writeBit(bit.charAt(i));
        }
    }

    public void flush() throws IOException {
        outputStream.writeChars(b.toString());
        outputStream.flush();
        b.delete(0, b.length());
    }

    @Override
    public void close() throws IOException {

        while (byteLength % 8 != 0) {
            if (byteLength < 8) {
                b.insert(0, '0');
            } else {
                b.append('0');
            }
            byteLength++;
        }

        flush();
        outputStream.close();
    }

    private boolean isBit(char bit) {
        return bit == '0' || bit == '1';
    }

    private boolean isBit(String bit) {
        for (int i = 0; i < bit.length(); i++) {
            if (!isBit(bit.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
