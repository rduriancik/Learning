package com.example.robert.photofeed.login.di;

import com.example.robert.photofeed.PhotoFeedAppModule;
import com.example.robert.photofeed.domain.di.DomainModule;
import com.example.robert.photofeed.libs.di.LibsModule;
import com.example.robert.photofeed.login.ui.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by robert on 28.8.2017.
 */
@Singleton
@Component(modules = {LoginModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}
