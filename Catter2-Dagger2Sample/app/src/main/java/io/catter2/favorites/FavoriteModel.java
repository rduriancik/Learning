package io.catter2.favorites;

public class FavoriteModel {
    private long timeAdded;
    private String url;

    public FavoriteModel(long timeAdded, String url) {
        this.timeAdded = timeAdded;
        this.url = url;
    }

    public long getTimeAdded() {
        return timeAdded;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FavoriteModel) {
            FavoriteModel f = (FavoriteModel) o;
            return url.equals(f.url) && timeAdded == f.timeAdded;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }
}
