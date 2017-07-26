package com.example.robert.twitterclient.images.di;

import com.example.robert.twitterclient.images.ImagesPresenter;
import com.example.robert.twitterclient.images.ui.ImagesFragment;
import com.example.robert.twitterclient.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by robert on 26.7.2017.
 */

@Singleton
@Component(modules = {ImagesModule.class, LibsModule.class})
public interface ImagesComponent {
    void inject(ImagesFragment fragment);

    ImagesPresenter getPresenter();
}
