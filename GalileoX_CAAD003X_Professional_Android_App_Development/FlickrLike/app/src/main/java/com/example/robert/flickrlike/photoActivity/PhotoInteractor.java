package com.example.robert.flickrlike.photoActivity;

import com.example.robert.flickrlike.entities.Photo;

/**
 * Created by robert on 20.8.2017.
 */

public interface PhotoInteractor {
    void savePhoto(Photo photo);

    void findPhotos(String tags, int page);
}
