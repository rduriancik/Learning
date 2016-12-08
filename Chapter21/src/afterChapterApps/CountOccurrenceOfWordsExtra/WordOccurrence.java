package afterChapterApps.CountOccurrenceOfWordsExtra;

/**
 * Created by robert on 29.8.2016.
 */
public class WordOccurrence implements Comparable {

    private String word;
    private int count;

    public WordOccurrence(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }

        if (o instanceof WordOccurrence) {
            WordOccurrence obj = (WordOccurrence) o;
            return Integer.compare(this.count, obj.count);
        } else {
            throw new ClassCastException();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordOccurrence that = (WordOccurrence) o;

        if (count != that.count) return false;
        return word != null ? word.equals(that.word) : that.word == null;

    }

    @Override
    public int hashCode() {
        int result = word != null ? word.hashCode() : 0;
        result = 31 * result + count;
        return result;
    }
}
