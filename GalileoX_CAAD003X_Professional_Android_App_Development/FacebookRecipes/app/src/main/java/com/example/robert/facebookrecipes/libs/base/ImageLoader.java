package com.example.robert.facebookrecipes.libs.base;

import android.widget.ImageView;

/**
 * Created by robert on 3.8.2017.
 */

public interface ImageLoader {
    void load(ImageView imageView, String URL);

    void setOnFinishedImageLoadingListener(Object listener);
}
