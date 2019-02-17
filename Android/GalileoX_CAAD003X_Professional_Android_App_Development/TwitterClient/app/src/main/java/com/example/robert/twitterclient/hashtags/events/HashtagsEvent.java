package com.example.robert.twitterclient.hashtags.events;

import com.example.robert.twitterclient.entities.Hashtag;

import java.util.List;

/**
 * Created by robert on 27.7.2017.
 */

public class HashtagsEvent {
    private String error;
    private List<Hashtag> hashtags;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}
