package afterChapterApps.CountOccurrenceOfWordsExtra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by robert on 15.8.2016.
 */
public class CountOccurrenceOfWordsExtra {
    public static void main(String[] args) {
        // Set text in a String
        String text = "Good morning. Have a good class. " +
                "Have a good visit. Have fun!";

        List<WordOccurrence> list = new ArrayList<>();

        String[] words = text.split("[ \\n\\t\\r.,;:!?(){]");
        for (int i = 0; i < words.length; i++) {
            String key = words[i].toLowerCase();

            if (key.length() > 0) {
                Integer index = getIndex(list, key);
                if (index != null) {
                    int count = list.get(index).getCount();
                    list.get(index).setCount(count + 1);
                } else {
                    list.add(new WordOccurrence(key, 1));
                }
            }
        }

        Collections.sort(list);
        for (WordOccurrence w : list) {
            System.out.println(w.getCount() + "\t" + w.getWord());
        }


    }

    private static Integer getIndex(List<WordOccurrence> list, String s) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getWord().equals(s)) {
                return i;
            }
        }

        return null;
    }
}
