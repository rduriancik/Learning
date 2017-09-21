package io.catter2.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 20.9.2017.
 */

@Module
public class AppModule {
    @Provides
    @Singleton
    public Context provideAppContext() {
        throw new EmptyModuleException();
    }
}
