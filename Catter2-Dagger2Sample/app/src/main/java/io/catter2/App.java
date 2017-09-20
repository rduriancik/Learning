package io.catter2;

import android.app.Application;

import io.catter2.cat_api.CacheTheCatAPI;
import io.catter2.cat_api.RetrofitTheCatAPI;
import io.catter2.cat_api.TheCatAPI;

/**
 * Created by robert on 20.9.2017.
 */

public class App extends Application {
    private static TheCatAPI theCatAPI;

    @Override
    public void onCreate() {
        super.onCreate();

        TheCatAPI api = new RetrofitTheCatAPI();
        App.theCatAPI = new CacheTheCatAPI(api);
    }

    public static TheCatAPI getTheCatAPI() {
        return theCatAPI;
    }
}
