package com.example.robert.photofeed.main;

import android.location.Location;

/**
 * Created by robert on 2.9.2017.
 */

public class UploadInteractorImpl implements UploadInteractor {
    MainRepository repository;

    public UploadInteractorImpl(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Location location, String path) {
        repository.uploadPhoto(location, path);
    }
}
