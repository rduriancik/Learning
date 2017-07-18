package com.example.robert.firebasechat;

import android.app.Application;

import com.example.robert.firebasechat.lib.GlideImageLoader;
import com.example.robert.firebasechat.lib.ImageLoader;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by robert on 18.7.2017.
 */

public class AndroidChatApplication extends Application {
    private ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
        setupImageLoader();
    }

    private void setupImageLoader() {
        imageLoader = new GlideImageLoader(this);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    private void setupFirebase() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
