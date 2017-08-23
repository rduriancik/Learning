package com.example.robert.flickrlike.libs.base;

import android.widget.ImageView;

/**
 * Created by robert on 20.8.2017.
 */

public interface ImageLoader {
    void load(ImageView imageView, String url);

    void setOnFinishedLoadingListener(Object listener);
}
