package com.example.robert.photofeed.main;

import android.location.Location;

import com.example.robert.photofeed.main.events.MainEvent;

/**
 * Created by robert on 31.8.2017.
 */

public interface MainPresenter {
    void onCreate();

    void onDestroy();

    void logout();

    void uploadPhoto(Location location, String path);

    void onEventMainThread(MainEvent event);
}

