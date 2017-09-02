package com.example.robert.photofeed.domain;

/**
 * Created by robert on 28.8.2017.
 */

public interface FirebaseActionListenerCallback {
    void onSuccess();

    void onError(String error);
}
