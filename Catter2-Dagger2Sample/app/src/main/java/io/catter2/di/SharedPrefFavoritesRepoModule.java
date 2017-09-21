package io.catter2.di;

import android.content.Context;

import io.catter2.favorites.FavoritesRepository;
import io.catter2.favorites.SharedPrefFavoritesRepository;

/**
 * Created by robert on 20.9.2017.
 */

public class SharedPrefFavoritesRepoModule extends FavoritesRepositoryModule {
    private String userToken;

    public SharedPrefFavoritesRepoModule(String userToken) {
        this.userToken = userToken;
    }

    @Override
    public FavoritesRepository provideFavoritesRepository(Context context, String userToken) {
        return new SharedPrefFavoritesRepository(context, userToken);
    }

    @Override
    public String provideUserToken() {
        return userToken;
    }
}
