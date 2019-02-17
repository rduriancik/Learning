package io.catter2.di;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.catter2.favorites.FavoritesRepository;

/**
 * Created by robert on 20.9.2017.
 */

@Module
public class FavoritesRepositoryModule {
    @Provides
    @UserScope
    public FavoritesRepository provideFavoritesRepository(Context context, @Named("UserToken") String userToken) {
        throw new EmptyModuleException();
    }

    @Provides
    @Named("UserToken")
    @UserScope
    public String provideUserToken() {
        throw new EmptyModuleException();
    }
}
