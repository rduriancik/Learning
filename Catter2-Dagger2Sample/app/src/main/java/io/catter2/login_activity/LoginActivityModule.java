package io.catter2.login_activity;

import dagger.Module;
import dagger.Provides;
import io.catter2.login.LoginUseCase;

/**
 * Created by robert on 21.9.2017.
 */
@Module
public class LoginActivityModule {
    public static LoginUseCase testLoginUseCase;

    @Provides
    public LoginUseCase provideLoginUseCase() {
        if (testLoginUseCase != null) {
            return testLoginUseCase;
        }

        return new LoginUseCase();
    }
}
