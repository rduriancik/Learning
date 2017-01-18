package afterChapterApps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

/**
 * Created by robert on 22.4.2016.
 */
public class AscendingWords {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Usage: java AscendingWords \"filename\"");
            System.exit(1);
        }

        PriorityQueue<String> list = new PriorityQueue<>();

        try (BufferedReader input = new BufferedReader(new FileReader(args[0]))) {
            String word = input.readLine();

            while (word != null) {
                if(Character.isLetter(word.charAt(0))){
                    list.offer(word);
                }

                word = input.readLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(1);
        }

        while(list.size() > 0){
            System.out.print(list.remove() + " ");
        }

    }
}
