package com.example.robert.flickrlike.photoActivity.events;

import com.example.robert.flickrlike.entities.Photo;

/**
 * Created by robert on 21.8.2017.
 */

public class PhotoEvent {

    public static final int NEXT_PHOTO_EVENT = 0;
    public static final int SAVE_PHOTO_EVENT = 1;

    private int type;
    private String error;
    private Photo photo;

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

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
