package com.example.robert.flickrlike.likedPhotosActivity.di;

import com.example.robert.flickrlike.libs.di.LibsModule;
import com.example.robert.flickrlike.likedPhotosActivity.ui.LikedPhotosActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by robert on 23.8.2017.
 */
@Singleton
@Component(modules = {LikedPhotosModule.class, LibsModule.class})
public interface LikedPhotosComponent {
    void inject(LikedPhotosActivity activity);
}
