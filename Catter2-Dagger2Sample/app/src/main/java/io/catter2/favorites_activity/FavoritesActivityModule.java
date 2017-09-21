package io.catter2.favorites_activity;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import io.catter2.favorites.FavoritesRepository;
import io.catter2.favorites.GetFavoritesUseCase;

/**
 * Created by robert on 20.9.2017.
 */

@Module
public class FavoritesActivityModule {
    public static GetFavoritesUseCase testGetFavoritesUseCase;

    @Provides
    public static GetFavoritesUseCase provideGetFavoritesUseCase(Lazy<FavoritesRepository> repositoryLazy) {
        if (FavoritesActivityModule.testGetFavoritesUseCase != null) {
            return FavoritesActivityModule.testGetFavoritesUseCase;
        }

        return new GetFavoritesUseCase(repositoryLazy.get());
    }
}
