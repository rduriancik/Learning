package com.example.robert.twitterclient.images.ui;

import com.example.robert.twitterclient.entities.Image;

import java.util.List;

/**
 * Created by robert on 25.7.2017.
 */

public interface ImagesView {
    void showImages();

    void hideImages();

    void showProgress();

    void hideProgress();

    void onError(String error);

    void setContent(List<Image> items);
}
