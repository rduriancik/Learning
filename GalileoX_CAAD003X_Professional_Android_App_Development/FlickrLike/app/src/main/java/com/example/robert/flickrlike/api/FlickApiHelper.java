package com.example.robert.flickrlike.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.robert.flickrlike.entities.Photo;
import com.example.robert.flickrlike.libs.base.EventBus;
import com.example.robert.flickrlike.photoActivity.events.PhotoEvent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by robert on 20.8.2017.
 */

public class FlickApiHelper {
    EventBus eventBus;
    FlickrService flickrService;
    private String tags;
    private List<Photo> photos;

    public FlickApiHelper(EventBus eventBus, FlickrService flickrService, List<Photo> photos) {
        this.eventBus = eventBus;
        this.flickrService = flickrService;
        this.photos = photos;

    }

    public void findPhotos(@NonNull String tags) {
        this.tags = tags;

        Call<PhotosResponse> call = flickrService.search(this.tags, 15);
        call.enqueue(new Callback<PhotosResponse>() {
            @Override
            public void onResponse(@NonNull Call<PhotosResponse> call, @NonNull Response<PhotosResponse> response) {
                if (response.isSuccessful()) {
                    PhotosResponse photosResponse = response.body();
                    if (photosResponse.getPhotos() != null && photosResponse.getCount() != 0) {
                        photos.clear();
                        photos.addAll(photosResponse.getPhotos());
                        postNextPhoto();
                    } else {
                        post("Empty response");
                    }
                } else {
                    post(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhotosResponse> call, @NonNull Throwable t) {
                post(t.getLocalizedMessage());
            }
        });
    }

    public void getNextPhoto() {
        if (tags == null) {
            throw new IllegalStateException("No tags were provided");
        }

        if (photos.isEmpty()) {
            findPhotos(tags);
        } else {
            postNextPhoto();
        }
    }

    private void postNextPhoto() {
        Photo photo = photos.get(0);
        photos.remove(0);
        post(photo);
    }

    private void post(@NonNull Photo photo) {
        post(photo, null);
    }

    private void post(@NonNull String error) {
        post(null, error);
    }

    private void post(@Nullable Photo photo, @Nullable String error) {
        PhotoEvent event = new PhotoEvent();
        event.setType(PhotoEvent.NEXT_PHOTO_EVENT);
        event.setError(error);
        event.setPhoto(photo);
        eventBus.post(event);
    }
}
