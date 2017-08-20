package com.example.robert.flickrlike.libs;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.example.robert.flickrlike.libs.base.ImageLoader;

/**
 * Created by robert on 20.8.2017.
 */

public class GlideImageLoader implements ImageLoader {
    private RequestManager requestManager;

    public GlideImageLoader(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @Override
    public void load(ImageView imageView, String url) {
        requestManager.load(url).into(imageView);
    }
}
