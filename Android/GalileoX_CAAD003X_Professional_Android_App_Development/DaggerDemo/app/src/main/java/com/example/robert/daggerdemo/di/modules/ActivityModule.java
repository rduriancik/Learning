package com.example.robert.daggerdemo.di.modules;

import android.app.Activity;
import android.content.Context;

import com.example.robert.daggerdemo.di.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 23.7.2017.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }
}
