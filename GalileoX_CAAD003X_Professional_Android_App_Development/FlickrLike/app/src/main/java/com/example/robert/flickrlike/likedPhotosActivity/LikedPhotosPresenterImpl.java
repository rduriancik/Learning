package com.example.robert.flickrlike.likedPhotosActivity;

import android.util.Log;

import com.example.robert.flickrlike.libs.base.EventBus;
import com.example.robert.flickrlike.likedPhotosActivity.events.LikedPhotosEvent;
import com.example.robert.flickrlike.likedPhotosActivity.ui.LikedPhotosView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by robert on 23.8.2017.
 */

public class LikedPhotosPresenterImpl implements LikedPhotosPresenter {
    private static final String TAG = "LikedPhotosPresenterImp";
    private LikedPhotosView view;
    private EventBus eventBus;
    private LikedPhotosInteractor interactor;

    public LikedPhotosPresenterImpl(LikedPhotosView view, EventBus eventBus, LikedPhotosInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void getLikedPhotos() {
        if (view != null) {
            view.hideContent();
            view.hideEmpty();
            view.showProgressBar();
        }
        Log.d(TAG, "getLikedPhotos: called");
        interactor.getLikedPhotos();
    }

    @Override
    @Subscribe
    public void onEventMainThread(LikedPhotosEvent event) {
        if (view != null && event != null) {
            Log.d(TAG, "onEventMainThread: called");
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
