package afterChapterApps;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

/**
 * Created by robert on 25.5.2016.
 */
public class MatchGroupingSymbols {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java MatchGroupingSymbols \"sourceFile\"");
            System.exit(1);
        }

        Stack<Character> stack = new Stack<>();
        boolean correct = true;
        char temp;
        try (BufferedReader input =
                     Files.newBufferedReader(Paths.get(args[0]))) {
            int c = input.read();
            while (c != -1) {
                if (c == '{' || c == '('
                        || c == '[') {
                    stack.push((char) c);
                }

                if (c == '}' || c == ')'
                        || c == ']') {
                    if (!stack.isEmpty()) {
                        temp = stack.pop();
                    } else {
                        System.out.println("The file has incorrect pairs of grouping symbols.");
                        correct = false;
                        break;
                    }
                    if (temp == '{' && c != '}') {
                        System.out.println("The file has incorrect pairs of grouping symbols.");
                        correct = false;
                        break;
                    } else if (temp == '(' && c != ')') {
                        System.out.println("The file has incorrect pairs of grouping symbols.");
                        correct = false;
                        break;
                    } else if (temp == '[' && c != ']') {
                        System.out.println("The file has incorrect pairs of grouping symbols.");
                        correct = false;
                        break;
                    }
                }
                c = input.read();
            }

            if (correct) {
                System.out.println("The file has correct pairs of grouping symbols.");
            }
        } catch (IOException ex) {
            System.out.println("Error when reading file. - " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
