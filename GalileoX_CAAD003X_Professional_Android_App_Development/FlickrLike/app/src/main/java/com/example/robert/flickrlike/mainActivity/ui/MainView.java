package com.example.robert.flickrlike.mainActivity.ui;

/**
 * Created by robert on 20.8.2017.
 */

public interface MainView {
    void navigateToPhotoActivity(String tags);
    void onError(String error);
}
