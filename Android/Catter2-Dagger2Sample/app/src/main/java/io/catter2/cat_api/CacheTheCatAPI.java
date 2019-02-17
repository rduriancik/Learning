package io.catter2.cat_api;

import android.util.Log;

/**
 * Created by robert on 19.9.2017.
 */

public class CacheTheCatAPI implements TheCatAPI {
    private static final String TAG = "CacheTheCatAPI";

    private TheCatAPI service;
    private CatImagesModel lastResponse;

    public CacheTheCatAPI(TheCatAPI service) {
        this.service = service;
    }

    @Override
    public void getCatsWithHat(final Callback callback) {
        if (lastResponse != null) {
            Log.d(TAG, "Using cached response");
            callback.response(lastResponse);
        } else {
            Log.d(TAG, "Querying a new response");
            service.getCatsWithHat(new Callback() {
                @Override
                public void response(CatImagesModel response) {
                    Log.d(TAG, "Saving response to the cache");
                    lastResponse = response;
                    callback.response(lastResponse);
                }
            });
        }
    }
}
