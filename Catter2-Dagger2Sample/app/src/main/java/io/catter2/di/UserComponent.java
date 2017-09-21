package io.catter2.di;

import dagger.Component;
import io.catter2.favorites.FavoritesRepository;

/**
 * Created by robert on 20.9.2017.
 */
@UserScope
@Component(modules = FavoritesRepositoryModule.class,
        dependencies = AppComponent.class)
public abstract class UserComponent extends AppComponent {
    private static UserComponent instance;

    public static UserComponent get() {
        return UserComponent.instance;
    }

    public static void initialize(FavoritesRepositoryModule favoritesRepositoryModule) {
        if (UserComponent.get() != null) {
            throw new RuntimeException("UserComponent already initialized");
        }
        UserComponent.instance = DaggerUserComponent.builder()
                .appComponent(AppComponent.get())
                .favoritesRepositoryModule(favoritesRepositoryModule)
                .build();
    }

    abstract public FavoritesRepository getFavoritesRepository();

    public void close() {
        final FavoritesRepository repository = getFavoritesRepository();
        if (repository != null) {
            repository.clearOnChangeListener();
        }
        UserComponent.instance = null;
    }
}
