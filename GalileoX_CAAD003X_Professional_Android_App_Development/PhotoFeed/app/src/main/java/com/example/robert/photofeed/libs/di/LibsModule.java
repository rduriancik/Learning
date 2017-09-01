package com.example.robert.photofeed.libs.di;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.robert.photofeed.libs.CloudinaryImageStorage;
import com.example.robert.photofeed.libs.GlideImageLoader;
import com.example.robert.photofeed.libs.GreenRobotEventBus;
import com.example.robert.photofeed.libs.base.EventBus;
import com.example.robert.photofeed.libs.base.ImageLoader;
import com.example.robert.photofeed.libs.base.ImageStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 1.9.2017.
 */
@Module
public class LibsModule {
    private Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return new GreenRobotEventBus();
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(Fragment fragment) {
        GlideImageLoader glideImageLoader = new GlideImageLoader();
        if (fragment != null) {
            glideImageLoader.setLoaderContext(fragment);
        }
        return glideImageLoader;
    }

    @Provides
    @Singleton
    ImageStorage providesImageStorage(Context context, EventBus eventBus) {
        return new CloudinaryImageStorage(context, eventBus);
    }

    @Provides
    @Singleton
    Fragment providesFragment() {
        return this.fragment;
    }
}
