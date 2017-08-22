package com.example.robert.flickrlike.photoActivity.ui;

/**
 * Created by robert on 21.8.2017.
 */

public interface PhotoView {
    void showNextPhoto();

    void showProgress();

    void showContent();

    void showSaveAnimation();

    void showDismissAnimation();

    void onError(String error);

}
