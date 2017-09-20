package io.catter2.favorites_activity;

import io.catter2.di.UserComponent;

/**
 * Created by robert on 20.9.2017.
 */

public class FavoritesActivityComponent {
    private FavoritesActivityModule activityModule;

    public FavoritesActivityComponent() {
        this.activityModule = new FavoritesActivityModule(UserComponent.get());
    }

    public void inject(FavoritesActivity activity) {
        activity.injectGetFavoritesUseCase(activityModule.provideGetFavoritesUseCase());
    }


}
