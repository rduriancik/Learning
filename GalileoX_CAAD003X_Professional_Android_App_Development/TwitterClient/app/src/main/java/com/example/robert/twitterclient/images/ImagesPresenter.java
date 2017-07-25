package com.example.robert.twitterclient.images;

import com.example.robert.twitterclient.images.events.ImagesEvent;

/**
 * Created by robert on 25.7.2017.
 */

public interface ImagesPresenter {
    void onResume();

    void onPause();

    void onDestroy();

    void getImageTweets();

    void onEventMainThread(ImagesEvent event);
}
