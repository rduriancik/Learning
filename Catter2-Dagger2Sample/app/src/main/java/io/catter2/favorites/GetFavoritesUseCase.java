package io.catter2.favorites;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 14.9.2017.
 */

public class GetFavoritesUseCase {
    public interface Callback {
        void favoriteUrlsUpdated(List<String> favoriteUrls);
    }

    private FavoritesRepository repository;

    public GetFavoritesUseCase(FavoritesRepository repository) {
        repository = repository;
    }

    public void getFavorites(final Callback callback) {
        callback.favoriteUrlsUpdated(favoritesToUrls(repository.getFavorites()));

        repository.registerOnChangeListener(new FavoritesRepository.OnChangeListener() {
            @Override
            public void onChanged(List<FavoriteModel> favorites) {
                callback.favoriteUrlsUpdated(favoritesToUrls(favorites));
            }
        });
    }

    public void clear() {
        repository.clearOnChangeListener();
    }

    private List<String> favoritesToUrls(List<FavoriteModel> favorites) {
        ArrayList<String> urls = new ArrayList<>(favorites.size());
        for (FavoriteModel favorite : favorites) {
            urls.add(favorite.getUrl());
        }

        return urls;
    }
}
