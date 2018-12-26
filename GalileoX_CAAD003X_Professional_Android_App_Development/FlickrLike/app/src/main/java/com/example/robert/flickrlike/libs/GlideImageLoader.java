package com.example.robert.flickrlike.libs;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestListener;
import com.example.robert.flickrlike.libs.base.ImageLoader;

/**
 * Created by robert on 20.8.2017.
 */

public class GlideImageLoader implements ImageLoader {
    private RequestManager requestManager;
    private RequestListener onFinishedLoadingListener;

    public GlideImageLoader(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @Override
    public void setOnFinishedLoadingListener(Object listener) {
        if (listener instanceof RequestListener) {
            this.onFinishedLoadingListener = (RequestListener) listener;
        }
    }

    @Override
    public void load(ImageView imageView, String url) {
        requestManager.load(url)
                .listener(onFinishedLoadingListener)
                .centerCrop()
                .into(imageView);
    }
}
