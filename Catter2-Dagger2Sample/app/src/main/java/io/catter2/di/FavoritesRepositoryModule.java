package io.catter2.di;

import io.catter2.favorites.FavoritesRepository;

/**
 * Created by robert on 20.9.2017.
 */

public interface FavoritesRepositoryModule {
    AppComponent getAppComponent();

    FavoritesRepository provideFavoritesRepository();
}
