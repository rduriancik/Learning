package com.example.robert.twitterclient.libs.di;

import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.robert.twitterclient.libs.GlideImageLoader;
import com.example.robert.twitterclient.libs.GreenRobotEventBus;
import com.example.robert.twitterclient.libs.base.EventBus;
import com.example.robert.twitterclient.libs.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 23.7.2017.
 */

@Module
public class LibsModule {

    private Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager) {
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment) {
        return Glide.with(fragment);
    }

    @Provides
    @Singleton
    Fragment providesFragment() {
        return this.fragment;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus() {
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}
