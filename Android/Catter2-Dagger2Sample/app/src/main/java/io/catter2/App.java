package io.catter2;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import io.catter2.di.AppComponent;
import io.catter2.di.AppModule;
import io.catter2.di.CachedRetrofitCatApiModule;

/**
 * Created by robert on 20.9.2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("App", "Initialized");
        AppModule appModule = new AppModule() {
            @Override
            public Context provideAppContext() {
                return App.this;
            }
        };
        AppComponent.initialize(appModule, new CachedRetrofitCatApiModule());
    }

}
