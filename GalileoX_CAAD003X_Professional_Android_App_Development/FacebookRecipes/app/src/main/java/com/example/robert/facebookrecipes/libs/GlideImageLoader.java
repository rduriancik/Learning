package com.example.robert.facebookrecipes.libs;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.example.robert.facebookrecipes.libs.base.ImageLoader;

/**
 * Created by robert on 3.8.2017.
 */

public class GlideImageLoader implements ImageLoader {
    private RequestManager requestManager;
    private RequestListener onFinishedLoadingListener;

    public GlideImageLoader(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @Override
    public void load(ImageView imageView, String URL) {
        if (onFinishedLoadingListener != null) {
            requestManager
                    .load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .listener(onFinishedLoadingListener)
                    .into(imageView);
        } else {
            requestManager
                    .load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imageView);
        }
    }

    @Override
    public void setOnFinishedImageLoadingListener(Object listener) {
        if (listener instanceof RequestListener) {
            this.onFinishedLoadingListener = (RequestListener) listener;
        }
    }
}
