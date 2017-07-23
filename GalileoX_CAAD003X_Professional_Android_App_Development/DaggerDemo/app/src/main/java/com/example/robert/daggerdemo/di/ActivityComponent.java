package com.example.robert.daggerdemo.di;

import com.example.robert.daggerdemo.MainActivity;

import dagger.Component;

/**
 * Created by robert on 23.7.2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
}
