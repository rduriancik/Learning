package com.example.robert.flickrlike.api;

import com.example.robert.flickrlike.entities.Photo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by robert on 20.8.2017.
 */

class PhotosResponse {
    @SerializedName("photo")
    private List<Photo> photos;

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public int getCount() {
        return photos.size();
    }
}
