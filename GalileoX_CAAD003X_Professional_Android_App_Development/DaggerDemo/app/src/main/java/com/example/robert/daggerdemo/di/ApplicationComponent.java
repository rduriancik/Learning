package com.example.robert.daggerdemo.di;

import android.app.Application;
import android.content.Context;

import com.example.robert.daggerdemo.DemoApplication;
import com.example.robert.daggerdemo.data.DataManager;
import com.example.robert.daggerdemo.data.DbHelper;
import com.example.robert.daggerdemo.data.SharedPreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by robert on 23.7.2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(DemoApplication demoApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();

    SharedPreferencesHelper getPreferencesHelper();

    DbHelper getDbHelper();
}
