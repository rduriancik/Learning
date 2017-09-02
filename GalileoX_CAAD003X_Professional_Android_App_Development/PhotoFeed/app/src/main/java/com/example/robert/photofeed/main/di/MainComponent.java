package com.example.robert.photofeed.main.di;

import com.example.robert.photofeed.PhotoFeedAppModule;
import com.example.robert.photofeed.libs.di.LibsModule;
import com.example.robert.photofeed.main.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by robert on 2.9.2017.
 */
@Singleton
@Component(modules = {MainModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
