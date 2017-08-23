package com.example.robert.flickrlike.likedPhotosActivity;

import com.example.robert.flickrlike.libs.base.EventBus;
import com.example.robert.flickrlike.likedPhotosActivity.events.LikedPhotosEvent;
import com.example.robert.flickrlike.likedPhotosActivity.ui.LikedPhotosView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by robert on 23.8.2017.
 */

public class LikedPhotosPresenterImpl implements LikedPhotosPresenter {
    private LikedPhotosView view;
    private EventBus eventBus;
    private LikedPhotosInteractor interactor;

    public LikedPhotosPresenterImpl(LikedPhotosView view, EventBus eventBus, LikedPhotosInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
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
    public void getLikedPhotos() {
        if (view != null) {
            view.hideContent();
            view.hideEmpty();
            view.showProgressBar();
        }
        interactor.getLikedPhotos();
    }

    @Subscribe
    @Override
    public void onEventMainThread(LikedPhotosEvent event) {
        if (view != null && event != null) {
            view.hideProgressBar();
            if (event.getPhotos() == null || event.getPhotos().isEmpty()) {
                view.showEmpty();
            } else {
                view.setContent(event.getPhotos());
                view.showContent();
            }
        }
    }
}
