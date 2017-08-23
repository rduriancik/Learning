package com.example.robert.flickrlike.likedPhotosActivity;

import com.example.robert.flickrlike.entities.Photo;
import com.example.robert.flickrlike.libs.base.EventBus;
import com.example.robert.flickrlike.likedPhotosActivity.events.LikedPhotosEvent;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by robert on 23.8.2017.
 */

public class LikedPhotosRepositoryImpl implements LikedPhotosRepository {
    private EventBus eventBus;

    public LikedPhotosRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getLikedPhotos() {
        FlowCursorList<Photo> photoCursor = SQLite.select().from(Photo.class).cursorList();
        post(photoCursor.getAll());
        photoCursor.close();
    }

    private void post(List<Photo> photos) {
        LikedPhotosEvent event = new LikedPhotosEvent();
        event.setPhotos(photos);
        eventBus.post(event);
    }
}
