package com.example.robert.twitterclient;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.robert.twitterclient.images.di.DaggerImagesComponent;
import com.example.robert.twitterclient.images.di.ImagesComponent;
import com.example.robert.twitterclient.images.di.ImagesModule;
import com.example.robert.twitterclient.images.ui.ImagesView;
import com.example.robert.twitterclient.images.ui.adapters.OnItemClickListener;
import com.example.robert.twitterclient.libs.di.LibsModule;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

/**
 * Created by robert on 22.7.2017.
 */

public class TwitterClientApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initTwitter();
    }

    private void initTwitter() {
        TwitterConfig config = new TwitterConfig.Builder(this)
                .twitterAuthConfig(new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET))
                .build();
        Twitter.initialize(config);
    }

    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView view, OnItemClickListener clickListener) {
        return DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(view, clickListener))
                .build();
    }
}
