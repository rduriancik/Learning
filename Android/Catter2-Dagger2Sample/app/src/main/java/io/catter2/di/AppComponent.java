package io.catter2.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.catter2.cat_api.TheCatAPI;

/**
 * Created by robert on 20.9.2017.
 */
@Singleton
@Component(modules = {AppModule.class, TheCatAPIModule.class})
public abstract class AppComponent {
    private static AppComponent instance;

    public static AppComponent get() {
        return AppComponent.instance;
    }

    public static void initialize(AppModule appModule, TheCatAPIModule theCatAPIModule) {
        if (AppComponent.get() != null) {
            throw new RuntimeException("AppComponent already initialized");
        }

        AppComponent.instance = DaggerAppComponent.builder()
                .appModule(appModule)
                .theCatAPIModule(theCatAPIModule)
                .build();
    }

    abstract public Context getAppContext();

    abstract public TheCatAPI getTheCatAPI();
}
