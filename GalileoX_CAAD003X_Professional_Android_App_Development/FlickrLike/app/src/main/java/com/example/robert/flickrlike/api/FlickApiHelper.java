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

    public FlickApiHelper(EventBus eventBus, FlickrService flickrService, List<Photo> photos) {
        this.eventBus = eventBus;
        this.flickrService = flickrService;
    }

    public void findPhotos(@NonNull String tags, int page) {
        Call<PhotosResponse> call = flickrService.search(tags, 15, page);
        call.enqueue(new Callback<PhotosResponse>() {
            @Override
            public void onResponse(@NonNull Call<PhotosResponse> call, @NonNull Response<PhotosResponse> response) {
                if (response.isSuccessful()) {
                    PhotosResponse photosResponse = response.body();
                    if (photosResponse.getPhotos() != null && photosResponse.getCount() != 0) {
                        post(photosResponse.getPhotos(), photosResponse.getPage());
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

    private void post(@NonNull List<Photo> photos, int page) {
        post(photos, page, null);
    }

    private void post(@NonNull String error) {
        post(null, null, error);
    }

    private void post(@Nullable List<Photo> photos, @Nullable Integer page, @Nullable String error) {
        PhotoEvent event = new PhotoEvent();
        event.setType(PhotoEvent.NEXT_PHOTOS_EVENT);
        event.setError(error);
        event.setPhotos(photos);
        event.setPage(page);
        eventBus.post(event);
    }
}
