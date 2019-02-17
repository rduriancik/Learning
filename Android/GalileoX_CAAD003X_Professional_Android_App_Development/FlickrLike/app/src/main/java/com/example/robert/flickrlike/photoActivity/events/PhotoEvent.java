package com.example.robert.flickrlike.photoActivity.events;

import com.example.robert.flickrlike.entities.Photo;

import java.util.List;

/**
 * Created by robert on 21.8.2017.
 */

public class PhotoEvent {

    public static final int NEXT_PHOTOS_EVENT = 0;
    public static final int SAVE_PHOTO_EVENT = 1;

    private int type;
    private String error;
    private List<Photo> photos;
    private Integer page;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public int getPage() {
        return page != null ? page : 0;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
