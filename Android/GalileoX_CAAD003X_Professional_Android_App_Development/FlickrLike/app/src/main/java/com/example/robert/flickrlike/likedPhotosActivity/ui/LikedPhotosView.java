package com.example.robert.flickrlike.likedPhotosActivity.ui;

import com.example.robert.flickrlike.entities.Photo;

import java.util.List;

/**
 * Created by robert on 23.8.2017.
 */

public interface LikedPhotosView {
    void showProgressBar();

    void hideProgressBar();

    void showContent();

    void hideContent();

    void showEmpty();

    void hideEmpty();

    void setContent(List<Photo> photos);
}
