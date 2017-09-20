package io.catter2;

import android.app.Application;

import io.catter2.cat_api.CacheTheCatAPI;
import io.catter2.cat_api.RetrofitTheCatAPI;
import io.catter2.cat_api.TheCatAPI;
import io.catter2.favorites.FavoritesRepository;
import io.catter2.favorites.SharedPrefFavoritesRepository;

/**
 * Created by robert on 20.9.2017.
 */

public class App extends Application {
    private static TheCatAPI theCatAPI;
    private static FavoritesRepository favoritesRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        TheCatAPI api = new RetrofitTheCatAPI();
        App.theCatAPI = new CacheTheCatAPI(api);
    }

    public void initializeFavoriteRepository(String userToken) {
        if (App.favoritesRepository != null) {
            throw new RuntimeException("FavoritesRepository already initialized");
        }

        App.favoritesRepository = new SharedPrefFavoritesRepository(this, userToken);
    }

    public void destroyFavoritesRepository() {
        if (App.favoritesRepository != null) {
            App.favoritesRepository.clearOnChangeListener();
            App.favoritesRepository = null;
        }
    }

    public static TheCatAPI getTheCatAPI() {
        return theCatAPI;
    }

    public static FavoritesRepository getFavoritesRepository() {
        return favoritesRepository;
    }
}
