package io.catter2.list_activity;

import io.catter2.cat_api.FetchImageUseCase;
import io.catter2.di.UserComponent;
import io.catter2.favorites.AddFavoriteUseCase;

/**
 * Created by robert on 21.9.2017.
 */

public class ListActivityModule {
    public static AddFavoriteUseCase testAddFavoriteUseCase;
    public static FetchImageUseCase testFetchImageUseCase;

    private UserComponent userComponent;

    public ListActivityModule(UserComponent userComponent) {
        this.userComponent = userComponent;
    }

    AddFavoriteUseCase provideAddFavoriteUseCase() {
        if (testAddFavoriteUseCase != null) {
            return testAddFavoriteUseCase;
        }

        return new AddFavoriteUseCase(userComponent.getFavoritesRepository());
    }

    FetchImageUseCase provideFetchImageUseCase() {
        if (testFetchImageUseCase != null) {
            return testFetchImageUseCase;
        }

        return new FetchImageUseCase(userComponent.getTheCatAPI());
    }
}
