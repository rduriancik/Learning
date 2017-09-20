package io.catter2.di;

import io.catter2.cat_api.TheCatAPI;
import io.catter2.favorites.FavoritesRepository;

/**
 * Created by robert on 20.9.2017.
 */

public class UserComponent {
    private static UserComponent instance;

    private FavoritesRepositoryModule favoritesRepositoryModule;
    private FavoritesRepository favoritesRepository;

    public static UserComponent get() {
        return UserComponent.instance;
    }

    public void initialize(FavoritesRepositoryModule module) {
        if (UserComponent.get() != null) {
            throw new RuntimeException("UserComponent already initialized");
        }
        UserComponent.instance = new UserComponent(module);
    }

    private UserComponent(FavoritesRepositoryModule favoritesRepositoryModule) {
        this.favoritesRepositoryModule = favoritesRepositoryModule;
    }

    public TheCatAPI getTheCatAPI() {
        return this.favoritesRepositoryModule.getAppComponent().getTheCatAPI();
    }

    public FavoritesRepository getFavoritesRepository() {
        if (favoritesRepository == null) {
            favoritesRepository = favoritesRepositoryModule.provideFavoritesRepository();
        }

        return favoritesRepository;
    }

    public void close() {
        if (favoritesRepository != null) {
            favoritesRepository.clearOnChangeListener();
        }
    }
}
