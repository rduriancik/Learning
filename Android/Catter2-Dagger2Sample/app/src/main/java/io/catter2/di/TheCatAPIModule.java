package io.catter2.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.catter2.cat_api.TheCatAPI;

/**
 * Created by robert on 20.9.2017.
 */
@Module
public class TheCatAPIModule {
    @Provides
    @Singleton
    public TheCatAPI provideTheCatAPI() {
        throw new EmptyModuleException();
    }
}
