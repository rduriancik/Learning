package com.example.robert.flickrlike.mainActivity.di;

import com.example.robert.flickrlike.mainActivity.ui.MainActivity;

import dagger.Component;

/**
 * Created by robert on 20.8.2017.
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
