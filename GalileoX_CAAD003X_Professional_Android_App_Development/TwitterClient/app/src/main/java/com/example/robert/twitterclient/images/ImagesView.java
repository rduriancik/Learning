package com.example.robert.twitterclient.images;

import com.example.robert.twitterclient.entities.Image;

import java.util.List;

/**
 * Created by robert on 25.7.2017.
 */

public interface ImagesView {
    void showElements();

    void hideElements();

    void showProgress();

    void hideProgress();

    void onError(String error);

    void setContent(List<Image> items);
}
