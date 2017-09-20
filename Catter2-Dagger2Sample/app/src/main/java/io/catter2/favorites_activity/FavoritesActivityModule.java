package io.catter2.favorites_activity;

import io.catter2.di.UserComponent;
import io.catter2.favorites.GetFavoritesUseCase;

/**
 * Created by robert on 20.9.2017.
 */

public class FavoritesActivityModule {
    public static GetFavoritesUseCase testGetFavoritesUseCase;

    private UserComponent userComponent;

    public FavoritesActivityModule(UserComponent userComponent) {
        this.userComponent = userComponent;
    }

    GetFavoritesUseCase provideGetFavoritesUseCase() {
        if (FavoritesActivityModule.testGetFavoritesUseCase != null) {
            return FavoritesActivityModule.testGetFavoritesUseCase;
        }

        return new GetFavoritesUseCase(userComponent.getFavoritesRepository());
    }
}
