package io.catter2.cat_api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 14.9.2017.
 */

public class FetchImageUseCase {
    public interface Callback {
        void imageUrls(List<String> urls);
    }

    private static final String TAG = "FetchImageUseCase";
    private TheCatApi theCatApi;

    public FetchImageUseCase(TheCatApi theCatApi) {
        this.theCatApi = theCatApi;
    }

    public void getImagesUrls(final Callback callback) {
        theCatApi.getCatsWithHat(new TheCatApi.Callback() {
            @Override
            public void response(CatImagesModel response) {
                List<String> imageUrls = new ArrayList<>();
                if (response != null) {
                    for (CatImageModel model : response.catImages) {
                        imageUrls.add(model.url);
                    }
                }
                callback.imageUrls(imageUrls);
            }
        });
    }
}
