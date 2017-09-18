package io.catter2.favorites;

import java.util.List;

/**
 * Created by robert on 18.9.2017.
 */

public interface FavoritesRepository {
    interface OnChangeListener {
        void onChanged(List<FavoriteModel> favorites);
    }

    void registerOnChangeListener(OnChangeListener listener);

    List<FavoriteModel> getFavorites();

    void clearOnChangeListener();

    List<FavoriteModel> addFavorite(FavoriteModel favorite);
}
