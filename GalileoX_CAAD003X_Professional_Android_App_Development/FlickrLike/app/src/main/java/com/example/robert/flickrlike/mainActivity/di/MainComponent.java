package com.example.robert.flickrlike.mainActivity.di;

import com.example.robert.flickrlike.mainActivity.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by robert on 20.8.2017.
 */
@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
