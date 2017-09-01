package com.example.robert.photofeed.main.ui;

/**
 * Created by robert on 31.8.2017.
 */

public interface MainView {
    void onUploadInit();

    void onUploadComplete();

    void onUploadError(String error);
}
