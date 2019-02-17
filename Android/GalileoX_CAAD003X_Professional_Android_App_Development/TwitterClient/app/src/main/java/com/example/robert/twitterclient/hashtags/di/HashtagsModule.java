package com.example.robert.twitterclient.hashtags.di;

import com.example.robert.twitterclient.api.CustomTwitterApiClient;
import com.example.robert.twitterclient.entities.Hashtag;
import com.example.robert.twitterclient.hashtags.HashtagsInteractor;
import com.example.robert.twitterclient.hashtags.HashtagsInteractorImpl;
import com.example.robert.twitterclient.hashtags.HashtagsPresenter;
import com.example.robert.twitterclient.hashtags.HashtagsPresenterImpl;
import com.example.robert.twitterclient.hashtags.HashtagsRepository;
import com.example.robert.twitterclient.hashtags.HashtagsRepositoryImpl;
import com.example.robert.twitterclient.hashtags.adapters.HashtagsAdapter;
import com.example.robert.twitterclient.hashtags.adapters.OnItemClickListener;
import com.example.robert.twitterclient.hashtags.ui.HashtagsView;
import com.example.robert.twitterclient.libs.base.EventBus;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 27.7.2017.
 */

@Module
public class HashtagsModule {
    private HashtagsView view;
    private OnItemClickListener clickListener;

    public HashtagsModule(HashtagsView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    HashtagsAdapter providesAdapter(List<Hashtag> dataset, OnItemClickListener clickListener) {
        return new HashtagsAdapter(dataset, clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return this.clickListener;
    }

    @Provides
    @Singleton
    HashtagsView providesHashtagsView() {
        return this.view;
    }

    @Provides
    @Singleton
    List<Hashtag> providesItemList() {
        return new ArrayList<Hashtag>();
    }

    @Provides
    @Singleton
    HashtagsPresenter providesHashtagsPresenter(HashtagsView view, EventBus eventBus, HashtagsInteractor interactor) {
        return new HashtagsPresenterImpl(view, eventBus, interactor);
    }

    @Provides
    @Singleton
    HashtagsInteractor providesHashtagsInteractor(HashtagsRepository repository) {
        return new HashtagsInteractorImpl(repository);
    }

    @Provides
    @Singleton
    HashtagsRepository providesHashtagsRepository(EventBus eventBus, CustomTwitterApiClient client) {
        return new HashtagsRepositoryImpl(eventBus, client);
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
