package io.catter2.di;

import io.catter2.cat_api.CacheTheCatAPI;
import io.catter2.cat_api.RetrofitTheCatAPI;
import io.catter2.cat_api.TheCatAPI;

/**
 * Created by robert on 20.9.2017.
 */

public class CachedRetrofitCatApiModule extends TheCatAPIModule {

    @Override
    public TheCatAPI provideTheCatAPI() {
        return provideTheCatAPICached(provideTheCatAPIRetrofit());
    }

    public TheCatAPI provideTheCatAPIRetrofit() {
        return new RetrofitTheCatAPI();
    }

    public TheCatAPI provideTheCatAPICached(TheCatAPI api) {
        return new CacheTheCatAPI(api);
    }
}
