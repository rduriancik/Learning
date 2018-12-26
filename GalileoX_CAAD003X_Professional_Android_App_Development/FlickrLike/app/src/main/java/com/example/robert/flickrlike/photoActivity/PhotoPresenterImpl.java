package com.example.robert.flickrlike.photoActivity;

import com.example.robert.flickrlike.entities.Photo;
import com.example.robert.flickrlike.libs.base.EventBus;
import com.example.robert.flickrlike.photoActivity.events.PhotoEvent;
import com.example.robert.flickrlike.photoActivity.ui.PhotoView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by robert on 22.8.2017.
 */

public class PhotoPresenterImpl implements PhotoPresenter {
    private static final String TAG = "PhotoPresenterImpl";
    private PhotoView view;
    private PhotoInteractor interactor;
    private EventBus eventBus;

    public PhotoPresenterImpl(PhotoView view, PhotoInteractor interactor, EventBus eventBus) {
        this.view = view;
        this.interactor = interactor;
        this.eventBus = eventBus;
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void findPhotos(String tags, int page) {
        if (view != null) {
            view.hideContent();
            view.showProgressBar();
        }
        interactor.findPhotos(tags, page);
    }

    @Override
    public void getNextPhoto() {
        if (view != null) {
            view.getNextPhoto();
        }
    }

    @Override
    public void onSwipePhoto(Photo photo, int type) {
        if (view != null) {
            view.showAnimation(type);
            view.hideContent();
            view.showProgressBar();
            if (type == PhotoView.SWIPE_LEFT || type == PhotoView.SWIPE_RIGHT) {
                interactor.savePhoto(photo);
            }
        }
    }

    @Override
    public void imageReady() {
        if (view != null) {
            view.hideProgressBar();
            view.showContent();
        }
    }

    @Override
    public void imageError(String error) {
        if (view != null) {
            view.hideContent();
            view.hideProgressBar();
            view.onError(error);
            view.getNextPhoto();
        }
    }

    @Override
    @Subscribe
    public void onEventMainThread(PhotoEvent event) {
        if (event != null && view != null) {
            if (event.getType() == PhotoEvent.NEXT_PHOTOS_EVENT) {
                if (event.getError() != null) {
                    view.hideContent();
                    view.hideProgressBar();
                    view.onError(event.getError());
                } else {
                    view.setData(event.getPhotos(), event.getPage());
                    view.getNextPhoto();
                }
            } else if (event.getType() == PhotoEvent.SAVE_PHOTO_EVENT) {
                view.showPhotoSaved();
            }
        }
    }
}
