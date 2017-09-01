package com.example.robert.photofeed.libs;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.robert.photofeed.libs.base.ImageLoader;

/**
 * Created by robert on 1.9.2017.
 */

public class GlideImageLoader implements ImageLoader {
    private RequestManager requestManager;

    public void setLoaderContext(Fragment fragment) {
        this.requestManager = Glide.with(fragment);
    }

    @Override
    public void load(ImageView imageView, String URL) {
        requestManager.load(URL)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(800, 800)
                        .centerCrop())
                .into(imageView);
    }
}
