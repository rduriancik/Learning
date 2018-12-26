package com.example.robert.twitterclient.images.di;

import com.example.robert.twitterclient.api.CustomTwitterApiClient;
import com.example.robert.twitterclient.entities.Image;
import com.example.robert.twitterclient.images.ImagesInteractor;
import com.example.robert.twitterclient.images.ImagesInteractorImpl;
import com.example.robert.twitterclient.images.ImagesPresenter;
import com.example.robert.twitterclient.images.ImagesPresenterImpl;
import com.example.robert.twitterclient.images.ImagesRepository;
import com.example.robert.twitterclient.images.ImagesRepositoryImpl;
import com.example.robert.twitterclient.images.ui.ImagesView;
import com.example.robert.twitterclient.images.ui.adapters.ImagesAdapter;
import com.example.robert.twitterclient.images.ui.adapters.OnItemClickListener;
import com.example.robert.twitterclient.libs.base.EventBus;
import com.example.robert.twitterclient.libs.base.ImageLoader;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 26.7.2017.
 */

@Module
public class ImagesModule {

    private ImagesView view;
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> dataset, ImageLoader imageLoader, OnItemClickListener clickListener) {
        return new ImagesAdapter(dataset, imageLoader, clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Image> providesItemsList() {
        return new ArrayList<Image>();
    }

    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(EventBus eventBus, ImagesView view, ImagesInteractor interactor) {
        return new ImagesPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    ImagesView providesImagesView() {
        return this.view;
    }

    @Provides
    @Singleton
    ImagesInteractor providesImagesInteractor(ImagesRepository repository) {
        return new ImagesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ImagesRepository providesImagesRepository(EventBus eventBus, CustomTwitterApiClient client) {
        return new ImagesRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(TwitterSession session) {
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    TwitterSession providesTwitter() {
        return TwitterCore.getInstance().getSessionManager().getActiveSession();
    }
}
