package com.example.robert.flickrlike.likedPhotosActivity.events;

import com.example.robert.flickrlike.entities.Photo;

import java.util.List;

/**
 * Created by robert on 23.8.2017.
 */

public class LikedPhotosEvent {
    private List<Photo> photos;

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
