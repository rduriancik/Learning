package com.example.robert.flickrlike.photoActivity.di;

import com.example.robert.flickrlike.api.FlickApiHelper;
import com.example.robert.flickrlike.api.FlickrClient;
import com.example.robert.flickrlike.api.FlickrService;
import com.example.robert.flickrlike.libs.base.EventBus;
import com.example.robert.flickrlike.photoActivity.PhotoInteractor;
import com.example.robert.flickrlike.photoActivity.PhotoInteractorImpl;
import com.example.robert.flickrlike.photoActivity.PhotoPresenter;
import com.example.robert.flickrlike.photoActivity.PhotoPresenterImpl;
import com.example.robert.flickrlike.photoActivity.PhotoRepository;
import com.example.robert.flickrlike.photoActivity.PhotoRepositoryImpl;
import com.example.robert.flickrlike.photoActivity.ui.PhotoView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 22.8.2017.
 */

@Module
public class PhotoModule {
    private PhotoView view;

    public PhotoModule(PhotoView view) {
        this.view = view;
    }

    @Provides
    PhotoPresenter providePhotoPresenter(PhotoView view, PhotoInteractor interactor, EventBus eventBus) {
        return new PhotoPresenterImpl(view, interactor, eventBus);
    }

    @Provides
    PhotoView providePhotoView() {
        return this.view;
    }

    @Provides
    PhotoInteractor providePhotoInteractor(PhotoRepository repository, FlickApiHelper apiHelper) {
        return new PhotoInteractorImpl(repository, apiHelper);
    }

    @Provides
    @Singleton
    PhotoRepository providePhotoRepository(EventBus eventBus) {
        return new PhotoRepositoryImpl(eventBus);
    }

    @Provides
    @Singleton
    FlickApiHelper provideFlickrApiHelper(EventBus eventBus, FlickrService flickrService) {
        return new FlickApiHelper(eventBus, flickrService);
    }

    @Provides
    @Singleton
    FlickrService provideFlickrService() {
        return FlickrClient.getFlickrService();
    }

}
