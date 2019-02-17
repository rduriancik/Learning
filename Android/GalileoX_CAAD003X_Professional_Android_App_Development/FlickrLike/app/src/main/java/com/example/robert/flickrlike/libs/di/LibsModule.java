package com.example.robert.flickrlike.libs.di;

import android.app.Activity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.robert.flickrlike.libs.GlideImageLoader;
import com.example.robert.flickrlike.libs.GreenRobotEventBus;
import com.example.robert.flickrlike.libs.base.EventBus;
import com.example.robert.flickrlike.libs.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 20.8.2017.
 */

@Module
public class LibsModule {

    private Activity activity;

    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return this.activity;
    }

    @Provides
    @Singleton
    ImageLoader provideImageLoader(RequestManager requestManager) {
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager provideRequestManager(Activity activity) {
        return Glide.with(activity);
    }

    @Provides
    @Singleton
    EventBus provideEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus provideLibraryEventBus() {
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}
