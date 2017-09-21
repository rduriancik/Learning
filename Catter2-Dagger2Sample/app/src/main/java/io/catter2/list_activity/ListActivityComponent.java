package io.catter2.list_activity;

import javax.inject.Scope;

import dagger.Component;
import io.catter2.di.UserComponent;

/**
 * Created by robert on 21.9.2017.
 */

@Component(modules = ListActivityModule.class,
        dependencies = UserComponent.class)
@ListActivityComponent.ListActivityScope
public abstract class ListActivityComponent {

    @Scope
    public @interface ListActivityScope {
    }

    public static void initializeAndInject(ListActivity activity) {
        DaggerListActivityComponent.builder()
                .userComponent(UserComponent.get())
                .build()
                .inject(activity);
    }

    public abstract void inject(ListActivity activity);
}
