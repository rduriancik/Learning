package com.example.robert.photofeed.main;

import android.location.Location;

import com.example.robert.photofeed.domain.FirebaseApiHelper;
import com.example.robert.photofeed.entities.Photo;
import com.example.robert.photofeed.libs.base.EventBus;
import com.example.robert.photofeed.libs.base.ImageStorage;
import com.example.robert.photofeed.libs.base.ImageStorageFinishedListener;
import com.example.robert.photofeed.main.events.MainEvent;

import java.io.File;

/**
 * Created by robert on 2.9.2017.
 */

public class MainRepositoryImpl implements MainRepository {
    private EventBus eventBus;
    private FirebaseApiHelper firebaseApiHelper;
    private ImageStorage imageStorage;

    public MainRepositoryImpl(EventBus eventBus, FirebaseApiHelper firebaseApiHelper, ImageStorage imageStorage) {
        this.eventBus = eventBus;
        this.firebaseApiHelper = firebaseApiHelper;
        this.imageStorage = imageStorage;
    }

    @Override
    public void logout() {
        firebaseApiHelper.logout();
    }

    @Override
    public void uploadPhoto(Location location, String path) {
        final String newPhotoId = firebaseApiHelper.create();
        final Photo photo = new Photo();
        photo.setId(newPhotoId);
        photo.setEmail(firebaseApiHelper.getAuthEmail());
        if (location != null) {
            photo.setLatitude(location.getLatitude());
            photo.setLongitude(location.getLongitude());
        }

        post(MainEvent.UPLOAD_INIT);
        imageStorage.upload(new File(path), photo.getId(), new ImageStorageFinishedListener() {
            @Override
            public void onSuccess() {
                String url = imageStorage.getImageUrl(photo.getId());
                photo.setUrl(url);
                firebaseApiHelper.update(photo);

                post(MainEvent.UPLOAD_COMPLETE);
            }

            @Override
            public void onError(String error) {
                post(MainEvent.UPLOAD_ERROR, error);
            }
        });
    }

    private void post(int type) {
        post(type, null);
    }

    private void post(int type, String error) {
        MainEvent event = new MainEvent();
        event.setType(type);
        event.setError(error);
        eventBus.post(event);
    }
}
