package com.example.robert.twitterclient;

import android.app.Application;

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
}
