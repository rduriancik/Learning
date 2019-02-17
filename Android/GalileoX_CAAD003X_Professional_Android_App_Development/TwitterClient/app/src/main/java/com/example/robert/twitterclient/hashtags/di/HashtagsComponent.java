package com.example.robert.twitterclient.hashtags.di;

import com.example.robert.twitterclient.hashtags.ui.HashtagsFragment;
import com.example.robert.twitterclient.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by robert on 27.7.2017.
 */
@Singleton
@Component(modules = {LibsModule.class, HashtagsModule.class})
public interface HashtagsComponent {
    void inject(HashtagsFragment fragment);
}
