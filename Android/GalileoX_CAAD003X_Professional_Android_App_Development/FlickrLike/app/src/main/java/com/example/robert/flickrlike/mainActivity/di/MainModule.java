package com.example.robert.flickrlike.mainActivity.di;

import com.example.robert.flickrlike.mainActivity.MainPresenter;
import com.example.robert.flickrlike.mainActivity.MainPresenterImpl;
import com.example.robert.flickrlike.mainActivity.ui.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 20.8.2017.
 */

@Module
public class MainModule {
    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    MainPresenter providePresenter(MainView view) {
        return new MainPresenterImpl(view);
    }

    @Provides
    MainView provideMainView() {
        return this.view;
    }
}
