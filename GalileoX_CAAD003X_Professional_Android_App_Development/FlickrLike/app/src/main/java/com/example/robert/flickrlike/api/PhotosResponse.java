package com.example.robert.flickrlike.api;

import com.example.robert.flickrlike.entities.Photo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by robert on 20.8.2017.
 */

class PhotosResponse {
    private Photos photos;

    public List<Photo> getPhotos() {
        return photos.photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos.photos = photos;
    }

    public int getPage() {
        return photos.page;
    }

    public void setPage(int page) {
        this.photos.page = page;
    }

    public int getCount() {
        return photos.photos.size();
    }

    private static class Photos {
        private int page;
        @SerializedName("photo")
        private List<Photo> photos;
    }
}
