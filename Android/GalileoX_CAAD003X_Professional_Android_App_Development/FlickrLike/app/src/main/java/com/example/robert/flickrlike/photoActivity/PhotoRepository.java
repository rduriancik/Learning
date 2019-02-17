package com.example.robert.flickrlike.photoActivity;

import com.example.robert.flickrlike.entities.Photo;

/**
 * Created by robert on 21.8.2017.
 */

public interface PhotoRepository {
    void savePhoto(Photo photo);
}
