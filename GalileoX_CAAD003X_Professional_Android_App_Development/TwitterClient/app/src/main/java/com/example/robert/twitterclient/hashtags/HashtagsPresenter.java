package com.example.robert.twitterclient.hashtags;

import com.example.robert.twitterclient.hashtags.events.HashtagsEvent;

/**
 * Created by robert on 27.7.2017.
 */

public interface HashtagsPresenter {
    void onResume();

    void onPause();

    void onDestroy();

    void getHashtagTweets();

    void onEventMainThread(HashtagsEvent event);
}
