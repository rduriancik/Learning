package com.example.robert.flickrlike;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by robert on 20.8.2017.
 */

public class FlickrLikeApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }

    @Override
    public void onTerminate() {
        FlowManager.destroy();
        super.onTerminate();
    }
}
