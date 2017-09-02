package com.example.robert.photofeed.libs.di;

import com.example.robert.photofeed.PhotoFeedAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by robert on 1.9.2017.
 */
@Singleton
@Component(modules = {LibsModule.class, PhotoFeedAppModule.class})
public interface LibsComponent {
}
