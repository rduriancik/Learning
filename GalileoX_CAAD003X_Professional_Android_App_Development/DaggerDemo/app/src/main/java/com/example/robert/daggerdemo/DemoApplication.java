package com.example.robert.daggerdemo;

import android.app.Application;
import android.content.Context;

import com.example.robert.daggerdemo.data.DataManager;
import com.example.robert.daggerdemo.di.components.ApplicationComponent;
import com.example.robert.daggerdemo.di.components.DaggerApplicationComponent;
import com.example.robert.daggerdemo.di.modules.ApplicationModule;

import javax.inject.Inject;

/**
 * Created by robert on 23.7.2017.
 */

public class DemoApplication extends Application {

    protected ApplicationComponent applicationComponent;

    @Inject
    DataManager dataManager;

    public static DemoApplication get(Context context) {
        return (DemoApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
