package io.catter2.favorites_activity;

import javax.inject.Scope;

import dagger.Component;
import io.catter2.di.UserComponent;

/**
 * Created by robert on 20.9.2017.
 */

@Component(modules = FavoritesActivityModule.class,
        dependencies = UserComponent.class)
@FavoritesActivityComponent.FavoritesActivityScope
public abstract class FavoritesActivityComponent {

    @Scope
    public @interface FavoritesActivityScope {
    }

    public static void initializeAndInject(FavoritesActivity activity) {
        DaggerFavoritesActivityComponent.builder()
                .userComponent(UserComponent.get())
                .build()
                .inject(activity);
    }

    public abstract void inject(FavoritesActivity activity);


}
