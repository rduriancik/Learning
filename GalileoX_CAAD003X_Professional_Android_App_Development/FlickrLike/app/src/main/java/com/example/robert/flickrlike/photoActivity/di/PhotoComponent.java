package com.example.robert.flickrlike.photoActivity.di;

import com.example.robert.flickrlike.libs.di.LibsModule;
import com.example.robert.flickrlike.photoActivity.ui.PhotoActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by robert on 22.8.2017.
 */

@Singleton
@Component(modules = {PhotoModule.class, LibsModule.class})
public interface PhotoComponent {
    void inject(PhotoActivity activity);
}
