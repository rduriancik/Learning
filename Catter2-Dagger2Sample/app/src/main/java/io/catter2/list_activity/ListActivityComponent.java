package io.catter2.list_activity;

import io.catter2.di.UserComponent;

/**
 * Created by robert on 21.9.2017.
 */

public class ListActivityComponent {
    public ListActivityModule module;

    public ListActivityComponent() {
        this.module = new ListActivityModule(UserComponent.get());
    }

    public void inject(ListActivity activity) {
        activity.injectAddFavoriteUseCase(module.provideAddFavoriteUseCase());
        activity.injectFetchImageUseCase(module.provideFetchImageUseCase());
    }
}
