package com.example.robert.twitterclient.images;

import com.example.robert.twitterclient.images.events.ImagesEvent;
import com.example.robert.twitterclient.images.ui.ImagesView;
import com.example.robert.twitterclient.libs.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by robert on 26.7.2017.
 */

public class ImagesPresenterImpl implements ImagesPresenter {

    private EventBus eventBus;
    private ImagesView view;
    private ImagesInteractor interactor;

    public ImagesPresenterImpl(EventBus eventBus, ImagesView view, ImagesInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
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
    public void getImageTweets() {
        if (view != null) {
            view.hideImages();
            view.showProgress();
        }
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(ImagesEvent event) {
        String error = event.getError();
        if (view != null) {
            view.showImages();
            view.hideProgress();

            if (error != null) {
                view.onError(error);
            } else {
                view.setContent(event.getImages());
            }
        }
    }
}
