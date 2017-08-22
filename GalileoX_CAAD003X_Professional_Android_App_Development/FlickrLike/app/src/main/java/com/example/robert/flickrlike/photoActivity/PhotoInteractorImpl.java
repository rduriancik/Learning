package com.example.robert.flickrlike.photoActivity;

import com.example.robert.flickrlike.api.FlickApiHelper;
import com.example.robert.flickrlike.entities.Photo;

/**
 * Created by robert on 22.8.2017.
 */

public class PhotoInteractorImpl implements PhotoInteractor {
    private PhotoRepository repository;
    private FlickApiHelper flickApiHelper;

    public PhotoInteractorImpl(PhotoRepository repository, FlickApiHelper flickApiHelper) {
        this.repository = repository;
        this.flickApiHelper = flickApiHelper;
    }

    @Override
    public void savePhoto(Photo photo) {
        repository.savePhoto(photo);
    }

    @Override
    public void findPhotos(String tags, int page) {
        flickApiHelper.findPhotos(tags, page);
    }
}
