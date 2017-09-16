package io.catter2.favorites;

import android.content.Context;

import java.util.List;

/**
 * Created by robert on 14.9.2017.
 */

public class AddFavoriteUseCase {
    private FavoritesRepository repository;

    public AddFavoriteUseCase(Context context, String userToken) {
        repository = new FavoritesRepository(context, userToken);
    }

    public Boolean addUrlToUserFavoritesList(String url) {
        if (url == null) {
            return null;
        }

        long timeNow = System.currentTimeMillis();
        FavoriteModel model = new FavoriteModel(timeNow, url);
        List<FavoriteModel> currentList = repository.addFavorite(model);
        return currentList.contains(model);
    }
}
