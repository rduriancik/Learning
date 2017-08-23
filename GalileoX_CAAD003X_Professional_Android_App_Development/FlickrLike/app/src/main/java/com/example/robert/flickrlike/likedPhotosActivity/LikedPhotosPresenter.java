package com.example.robert.flickrlike.likedPhotosActivity;

import com.example.robert.flickrlike.likedPhotosActivity.events.LikedPhotosEvent;

/**
 * Created by robert on 23.8.2017.
 */

public interface LikedPhotosPresenter {
    void onCreate();

    void onDestroy();

    void getLikedPhotos();

    void onEventMainThread(LikedPhotosEvent event);
}
