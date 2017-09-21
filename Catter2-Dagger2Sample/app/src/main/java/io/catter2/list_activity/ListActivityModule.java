package io.catter2.list_activity;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import io.catter2.cat_api.FetchImageUseCase;
import io.catter2.cat_api.TheCatAPI;
import io.catter2.favorites.AddFavoriteUseCase;
import io.catter2.favorites.FavoritesRepository;

/**
 * Created by robert on 21.9.2017.
 */

@Module
public class ListActivityModule {
    public static AddFavoriteUseCase testAddFavoriteUseCase;
    public static FetchImageUseCase testFetchImageUseCase;

    @Provides
    public AddFavoriteUseCase provideAddFavoriteUseCase(Lazy<FavoritesRepository> repositoryLazy) {
        if (testAddFavoriteUseCase != null) {
            return testAddFavoriteUseCase;
        }

        return new AddFavoriteUseCase(repositoryLazy.get());
    }

    @Provides
    public FetchImageUseCase provideFetchImageUseCase(Lazy<TheCatAPI> theCatAPILazy) {
        if (testFetchImageUseCase != null) {
            return testFetchImageUseCase;
        }

        return new FetchImageUseCase(theCatAPILazy.get());
    }
}
