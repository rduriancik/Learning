package com.example.robert.twitterclient.hashtags;

import com.example.robert.twitterclient.entities.Hashtag;

import java.util.List;

/**
 * Created by robert on 27.7.2017.
 */

public interface HashtagsView {
    void showHashtags();

    void hideHashtags();

    void showProgress();

    void hideProgress();

    void onError(String error);

    void setContent(List<Hashtag> items);
}
