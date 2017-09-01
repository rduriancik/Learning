package com.example.robert.photofeed.login.di;

import com.example.robert.photofeed.domain.FirebaseApiHelper;
import com.example.robert.photofeed.libs.base.EventBus;
import com.example.robert.photofeed.login.LoginInteractor;
import com.example.robert.photofeed.login.LoginInteractorImpl;
import com.example.robert.photofeed.login.LoginPresenter;
import com.example.robert.photofeed.login.LoginPresenterImpl;
import com.example.robert.photofeed.login.LoginRepository;
import com.example.robert.photofeed.login.LoginRepositoryImpl;
import com.example.robert.photofeed.login.SignupInteractor;
import com.example.robert.photofeed.login.SignupInteractorImpl;
import com.example.robert.photofeed.login.ui.LoginView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 28.8.2017.
 */

@Module
public class LoginModule {
    LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    LoginView provideLoginView() {
        return this.view;
    }

    @Provides
    @Singleton
    LoginPresenter providesLoginPresenter(EventBus eventBus, LoginView loginView, LoginInteractor loginInteractor, SignupInteractor signupInteractor) {
        return new LoginPresenterImpl(eventBus, loginView, loginInteractor, signupInteractor);
    }

    @Provides
    @Singleton
    LoginInteractor providesLoginInteractor(LoginRepository repository) {
        return new LoginInteractorImpl(repository);
    }

    @Provides
    @Singleton
    SignupInteractor providesSignupInteractor(LoginRepository repository) {
        return new SignupInteractorImpl(repository);
    }

    @Provides
    @Singleton
    LoginRepository providesLoginRepository(FirebaseApiHelper firebase, EventBus eventBus) {
        return new LoginRepositoryImpl(eventBus, firebase);
    }
}
