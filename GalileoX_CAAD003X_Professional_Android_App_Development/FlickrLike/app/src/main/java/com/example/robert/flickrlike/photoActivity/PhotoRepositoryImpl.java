package com.example.robert.flickrlike.photoActivity;

import com.example.robert.flickrlike.entities.Photo;
import com.example.robert.flickrlike.libs.base.EventBus;
import com.example.robert.flickrlike.photoActivity.events.PhotoEvent;

/**
 * Created by robert on 22.8.2017.
 */

public class PhotoRepositoryImpl implements PhotoRepository {
    private EventBus eventBus;

    public PhotoRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void savePhoto(Photo photo) {
        photo.save();
        post();
    }

    private void post() {
        PhotoEvent event = new PhotoEvent();
        event.setType(PhotoEvent.SAVE_PHOTO_EVENT);
        eventBus.post(event);
    }
}
