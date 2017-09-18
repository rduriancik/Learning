package io.catter2.favorites;

import java.util.List;

/**
 * Created by robert on 14.9.2017.
 */

public class AddFavoriteUseCase {
    private FavoritesRepository repository;

    public AddFavoriteUseCase(FavoritesRepository repository) {
        this.repository = repository;
    }

    public boolean addUrlToUserFavoritesList(String url) {
        if (url == null) {
            return false;
        }

        long timeNow = System.currentTimeMillis();
        FavoriteModel model = new FavoriteModel(timeNow, url);
        List<FavoriteModel> currentList = repository.addFavorite(model);
        return currentList.contains(model);
    }
}
