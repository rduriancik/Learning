package io.catter2.login_activity;

import javax.inject.Scope;

import dagger.Component;

/**
 * Created by robert on 21.9.2017.
 */

@Component(modules = LoginActivityModule.class)
@LoginActivityComponent.LoginActivityScope
public abstract class LoginActivityComponent {
    @Scope
    public @interface LoginActivityScope {
    }

    public static void initializeAndInject(LoginActivity activity) {
        DaggerLoginActivityComponent.builder()
                .build()
                .inject(activity);
    }

    public abstract void inject(LoginActivity activity);
}
