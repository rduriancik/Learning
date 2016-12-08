package afterChapterApps;

import java.io.*;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by robert on 04.12.15.
 */
public class TestStoreObjectsAndArrays {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream output =
                     new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Exercise17_05.dat")))
        ) {
            output.writeObject(new int[]{1, 2, 3, 4, 5});
            output.writeObject(new Date());
            output.writeDouble(5.5);
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(new BufferedInputStream(new FileInputStream("Exercise17_05.dat")))
        ) {
            int[] numbers = (int[]) input.readObject();
            System.out.println(Arrays.toString(numbers));
            System.out.println(input.readObject());
            System.out.println(input.readDouble() + "");
        }
    }
}
