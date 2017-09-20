package io.catter2.di;

import android.content.Context;

import io.catter2.cat_api.TheCatAPI;

/**
 * Created by robert on 20.9.2017.
 */

public class AppComponent {
    private static AppComponent instance;

    private AppModule appModule;
    private TheCatAPIModule theCatAPIModule;

    private Context context;
    private TheCatAPI theCatAPI;

    private AppComponent(AppModule appModule, TheCatAPIModule theCatAPIModule) {
        this.appModule = appModule;
        this.theCatAPIModule = theCatAPIModule;
    }

    public static AppComponent get() {
        return AppComponent.instance;
    }

    public static void initialize(AppModule appModule, TheCatAPIModule theCatAPIModule) {
        if (AppComponent.get() != null) {
            throw new RuntimeException("AppComponent already initialized");
        }

        AppComponent.instance = new AppComponent(appModule, theCatAPIModule);
    }

    public Context getAppContext() {
        if (context == null) {
            context = appModule.provideAppContext();
        }

        return context;
    }

    public TheCatAPI getTheCatAPI() {
        if (theCatAPI == null) {
            theCatAPI = theCatAPIModule.provideTheCatAPI();
        }

        return theCatAPI;
    }
}
