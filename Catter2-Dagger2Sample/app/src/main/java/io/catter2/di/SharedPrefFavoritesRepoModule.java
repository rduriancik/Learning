package io.catter2.di;

import io.catter2.favorites.FavoritesRepository;
import io.catter2.favorites.SharedPrefFavoritesRepository;

/**
 * Created by robert on 20.9.2017.
 */

public class SharedPrefFavoritesRepoModule implements FavoritesRepositoryModule {
    private AppComponent appComponent;
    private String userToken;

    public SharedPrefFavoritesRepoModule(AppComponent appComponent, String userToken) {
        this.appComponent = appComponent;
        this.userToken = userToken;
    }

    @Override
    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public FavoritesRepository provideFavoritesRepository() {
        return new SharedPrefFavoritesRepository(appComponent.getAppContext(), provideUserToken());
    }

    public String provideUserToken() {
        return userToken;
    }
}
