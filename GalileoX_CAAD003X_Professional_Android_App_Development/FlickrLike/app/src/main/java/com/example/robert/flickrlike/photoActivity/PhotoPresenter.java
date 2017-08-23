package com.example.robert.flickrlike.photoActivity;

import com.example.robert.flickrlike.entities.Photo;
import com.example.robert.flickrlike.photoActivity.events.PhotoEvent;

/**
 * Created by robert on 20.8.2017.
 */

public interface PhotoPresenter {
    void onResume();

    void onPause();

    void onDestroy();

    void findPhotos(String tags, int page);

    void getNextPhoto();

    void onSwipePhoto(Photo photo, int type);

    void imageReady();

    void imageError(String error);

    void onEventMainThread(PhotoEvent event);
}
