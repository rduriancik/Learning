package com.example.robert.flickrlike.photoActivity.ui;

import com.example.robert.flickrlike.entities.Photo;

import java.util.List;

/**
 * Created by robert on 21.8.2017.
 */

public interface PhotoView {
    int SWIPE_UP = 0;
    int SWIPE_DOWN = 1;
    int SWIPE_RIGHT = 3;
    int SWIPE_LEFT = 4;

    void showNextPhoto();

    void showProgress();

    void showContent();

    void showAnimation(int type);

    void showPhotoSaved();

    void setData(List<Photo> photos, int page);

    void onError(String error);

}
