package afterChapterApps.hangmanGUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 4.5.2016.
 */
public class HangmanBackend {
    private List<String> words;
    private String actualWord;
    private StringBuilder actualWordSkeleton;
    private int index;

    public HangmanBackend() {
        words = new ArrayList<>(5);
        index = 0;
    }

    public boolean addWord(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        words.add(word);
        return true;
    }

    public void initialize() {
        if (words.isEmpty()) {
            throw new NullPointerException("You must add some words.");
        }

        actualWord = words.get(index % words.size());
        index++;
        actualWordSkeleton = new StringBuilder();
        for (int i = 0; i < actualWord.length(); i++) {
            actualWordSkeleton.append("_");
        }
    }

    public boolean guessWord(char character) {
        boolean change = false;
        for (int i = 0; i < actualWord.length(); i++) {
            if (actualWord.charAt(i) == character && actualWordSkeleton.charAt(i) != character) {
                actualWordSkeleton.setCharAt(i, character);
                change = true;
            }
        }

        return change;
    }

    public String getWord() {
        return actualWordSkeleton.toString();
    }

    public boolean isWholeWord() {
        return actualWord.equals(actualWordSkeleton.toString());
    }
}
