package com.example.robert.flickrlike.likedPhotosActivity;

/**
 * Created by robert on 23.8.2017.
 */

public class LikedPhotosInteractorImpl implements LikedPhotosInteractor {
    private LikedPhotosRepository repository;

    public LikedPhotosInteractorImpl(LikedPhotosRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getLikedPhotos() {
        repository.getLikedPhotos();
    }
}
