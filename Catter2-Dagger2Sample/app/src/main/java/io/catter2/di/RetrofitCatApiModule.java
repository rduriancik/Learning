package io.catter2.di;

import io.catter2.cat_api.TheCatAPI;

/**
 * Created by robert on 21.9.2017.
 */

public class RetrofitCatApiModule extends CachedRetrofitCatApiModule {
    @Override
    public TheCatAPI provideTheCatAPI() {
        return super.provideTheCatAPI();
    }
}
