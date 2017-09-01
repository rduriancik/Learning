package com.example.robert.photofeed.libs.base;

/**
 * Created by robert on 1.9.2017.
 */

public interface ImageStorageFinishedListener {
    void onSuccess();

    void onError(String error);
}
