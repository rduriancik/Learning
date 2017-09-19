package io.catter2;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.catter2.cat_api.CatImageModel;
import io.catter2.cat_api.CatImagesModel;
import io.catter2.cat_api.FetchImageUseCase;
import io.catter2.cat_api.TheCatAPI;

/**
 * Created by robert on 19.9.2017.
 */

public class FetchCatImageUseCaseTest {

    @Test
    public void testNoImage() throws InterruptedException {
        StubAPI api = new StubAPI();
        FetchImageUseCase imageUseCase = useCase(api);

        final CountDownLatch latch = new CountDownLatch(1);
        imageUseCase.getImagesUrls(new FetchImageUseCase.Callback() {
            @Override
            public void imageUrls(List<String> urls) {
                Assert.assertEquals(urls.size(), 0);
                latch.countDown();
            }
        });

        api.respond(new ArrayList<String>());
        latch.await(10, TimeUnit.SECONDS);
    }

    @Test
    public void testTwoImages() throws InterruptedException {
        StubAPI stubAPI = new StubAPI();
        FetchImageUseCase useCase = useCase(stubAPI);

        List<String> responseUrls = new ArrayList<>();
        responseUrls.add("url0");
        responseUrls.add("url1");

        final CountDownLatch latch = new CountDownLatch(1);
        useCase.getImagesUrls(new FetchImageUseCase.Callback() {
            @Override
            public void imageUrls(List<String> urls) {
                Assert.assertEquals(urls.size(), 2);
                Assert.assertEquals(urls.get(0), "url0");
                Assert.assertEquals(urls.get(1), "url1");

                latch.countDown();
            }
        });

        stubAPI.respond(responseUrls);
        latch.await(10, TimeUnit.SECONDS);
    }

    private FetchImageUseCase useCase(TheCatAPI api) {
        return new FetchImageUseCase(api);
    }

    class StubAPI implements TheCatAPI {
        Callback callback;

        @Override
        public void getCatsWithHat(Callback callback) {
            this.callback = callback;
        }

        public void respond(List<String> urls) {
            CatImagesModel response = new CatImagesModel();
            List<CatImageModel> images = new ArrayList<>();
            for (String url : urls) {
                CatImageModel model = new CatImageModel();
                model.url = url;
                model.sourceUrl = url;
                model.id = url + "id";
                images.add(model);
            }
            response.catImages = images;

            callback.response(response);
        }
    }
}
