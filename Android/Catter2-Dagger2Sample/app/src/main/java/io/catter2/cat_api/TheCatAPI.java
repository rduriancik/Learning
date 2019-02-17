package io.catter2.cat_api;

/**
 * Created by robert on 19.9.2017.
 */

public interface TheCatAPI {
    interface Callback {
        void response(CatImagesModel response);
    }

    void getCatsWithHat(Callback callback);
}
