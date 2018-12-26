package com.example.robert.twitterclient.images.events;

import com.example.robert.twitterclient.entities.Image;

import java.util.List;

/**
 * Created by robert on 25.7.2017.
 */

public class ImagesEvent {

    private String error;
    private List<Image> images;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
