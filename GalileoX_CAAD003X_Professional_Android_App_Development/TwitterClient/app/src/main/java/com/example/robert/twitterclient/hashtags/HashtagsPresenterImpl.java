package com.example.robert.twitterclient.hashtags;

import com.example.robert.twitterclient.hashtags.events.HashtagsEvent;
import com.example.robert.twitterclient.libs.base.EventBus;

/**
 * Created by robert on 27.7.2017.
 */

public class HashtagsPresenterImpl implements HashtagsPresenter {
    private HashtagsView view;
    private EventBus eventBus;
    private HashtagsInteractor interactor;

    public HashtagsPresenterImpl(HashtagsView view, EventBus eventBus, HashtagsInteractor interactor) {
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
    public void getHashtagTweets() {
        if (view != null) {
            view.hideHashtags();
            view.showProgress();
        }
        interactor.execute();
    }

    @Override
    public void onEventMainThread(HashtagsEvent event) {
        String error = event.getError();
        if (view != null) {
            view.showHashtags();
            view.hideProgress();

            if (error != null) {
                view.onError(error);
            } else {
                view.setContent(event.getHashtags());
            }
        }
    }
}
