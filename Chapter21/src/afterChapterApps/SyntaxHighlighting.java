package afterChapterApps;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by robert on 18.8.2016.
 */
public class SyntaxHighlighting {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java \"sourceFile\" \"outputHtmlFile\"");
            System.exit(1);
        }

        Path source = Paths.get(args[0]);
        if (Files.notExists(source)) {
            System.out.println("Source source does not exist.");
            System.exit(1);
        }

        Path output = Paths.get(args[1]);
        if (Files.exists(output)) {
            System.out.println("Output file already exists.");
            System.exit(1);
        }

        javaToHTML(source, output);
    }

    /**
     * Converts the java source file to HTML file with an output name
     */
    private static void javaToHTML(Path source, Path output) {
        if (Files.notExists(source) || source == null || output == null) {
            return;
        }

        try (
                BufferedWriter writer = Files.newBufferedWriter(output)
        ) {

            writer.write("<!DOCTYPE html>\n<html>\n");

            List<String> lines = Files.readAllLines(source);
            Iterator<String> iterator = lines.iterator();

            while (iterator.hasNext()) {
                String s = iterator.next();

                if (s.length() == 0) {
                    writer.write("<br>\n");
                    continue;
                }

                writer.write("<pre>");
                s = getSubstring(writer, s);
                String[] temp = insertBlanks(s).split(" ");

                for (int i = 0; i < temp.length; i++) {
                    // Blanks
                    if (temp[i].length() == 0) {
                        writer.write(" ");
                    } else
                        // Special characters
                        if (temp[i].equals("<")) {
                            writer.write("&lt;");
                        } else if (temp[i].equals(">")) {
                            writer.write("&gt;");
                        } else
                            // Parentheses
                            if (temp[i].equals("(")) {
                                writer.write("(");

                                while (!(")").equals(temp[i])) {
                                    i++;

                                    if (i == temp.length) {
                                        writer.write("<br>\n");
                                        s = iterator.next();
                                        i = 0;
                                        s = getSubstring(writer, s);
                                        temp = insertBlanks(s).split(" ");
                                    }

                                    if (temp[i].length() == 0) {
                                        writer.write(" ");
                                    } else if (temp[i].equals("\"")) {
                                        writer.write("<span style=\"color: blue;\">" + temp[i]);
                                        i++;

                                        while (!("\"").equals(temp[i])) {
                                            writeCharacter(writer, temp[i]);
                                            i++;

                                            if (i == temp.length) {
                                                writer.write("<br>\n");
                                                s = iterator.next();
                                                i = 0;
                                                s = getSubstring(writer, s);
                                                temp = insertBlanks(s).split(" ");
                                            }

                                        }

                                        writer.write(temp[i] + "</span>");
                                    } else if (temp[i].equals("<")) {
                                        writer.write("&lt;");
                                    } else if (temp[i].equals(">")) {
                                        writer.write("&gt;");
                                    } else {
                                        writer.write(temp[i]);
                                    }

                                }
                            } else
                                // Comments
                                if (temp[i].equals("//")) {
                                    writer.write("<span style=\"color: green\">" + temp[i]);
                                    i++;
                                    for (; i < temp.length; i++) {
                                        writer.write(temp[i].length() == 0 ? " " : temp[i]);
                                    }
                                    writer.write("</span>");
                                    break;
                                } else if (temp[i].equals("/*")) {
                                    writer.write("<span style=\"color: green\">" + temp[i]);
                                    i++;

                                    while (!("*/").equals(temp[i])) {
                                        writeCharacter(writer, temp[i]);
                                        i++;

                                        if (i == temp.length) {
                                            writer.write("<br>\n");
                                            s = iterator.next();
                                            i = 0;
                                            s = getSubstring(writer, s);
                                            temp = insertBlanks(s).split(" ");
                                        }
                                    }

                                    writer.write(temp[i] + "</span>");
                                } else
                                    // Keywords
                                    if (isKeyword(temp[i])) {
                                        writer.write("<span style=\"color: navy;\"><b>" + temp[i] + "</b></span>");
                                    } else {
                                        writer.write(temp[i]);
                                    }

                }

                writer.write("</pre>\n");

            }

            writer.write("</html>\n");
        } catch (IOException ex) {
            System.out.println("Error when reading a file: " + ex);
        }
    }

    /**
     * Writes character in the file
     */
    private static void writeCharacter(BufferedWriter writer, String s) throws IOException {
        if (s == null || writer == null) {
            return;
        }

        if (s.equals("<")) {
            writer.write("&lt;");
        } else if (s.equals(">")) {
            writer.write("&gt;");
        } else {
            writer.write(s.length() == 0 ? " " : s);
        }
    }

    /**
     * Returns a string without whitespace character at the beginning
     */
    private static String getSubstring(BufferedWriter writer, String s) throws IOException {
        if (s == null || writer == null) {
            return "";
        }

        int index = 0;
        while (s.charAt(index) == '\t') {
            writer.write('\t');
            index++;
        }

        s = s.substring(index);
        return s;
    }

    /**
     * Checks if a word is a keyword
     */
    private static boolean isKeyword(String word) {
        if (word == null) {
            return false;
        }

        Set<String> keywordSet = new HashSet<>(Arrays.asList("abstract", "assert", "boolean",
                "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "enum",
                "extends", "for", "final", "finally", "float", "goto",
                "if", "implements", "import", "instanceof", "int",
                "interface", "long", "native", "new", "package", "private",
                "protected", "public", "return", "short", "static",
                "strictfp", "super", "switch", "synchronized", "this",
                "throw", "throws", "transient", "try", "void", "volatile",
                "while", "true", "false", "null"));

        return keywordSet.contains(word);
    }

    /**
     * Inserts blanks in the line
     */
    private static String insertBlanks(String line) {
        if (line == null) {
            return "";
        }

        String result = " ";

        for (int j = 0; j < line.length(); j++) {
            // special characters which must be together
            if (j + 1 < line.length()
                    && ((line.charAt(j) == '/' && line.charAt(j + 1) == '*')
                    || (line.charAt(j) == '/' && line.charAt(j + 1) == '/')
                    || (line.charAt(j) == '*' && line.charAt(j + 1) == '/')
                    || (line.charAt(j) == '\\'))) {
                if (result.charAt(result.length() - 1) == ' ') {
                    result += line.charAt(j) + "" + line.charAt(j + 1) + " ";
                } else {
                    result += " " + line.charAt(j) + "" + line.charAt(j + 1) + " ";
                }
                j++;
            }
            // Non alphanumeric characters
            else if (!Character.isLetterOrDigit(line.charAt(j))) {
                if (result.charAt(result.length() - 1) == ' ') {
                    result += line.charAt(j) + " ";
                } else {
                    result += " " + line.charAt(j) + " ";
                }
            }
            // Alphanumeric characters
            else {
                result += line.charAt(j);
            }
        }

        return result;
    }
}
