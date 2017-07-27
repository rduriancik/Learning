package com.example.robert.twitterclient.hashtags;

/**
 * Created by robert on 27.7.2017.
 */

public class HashtagsInteractorImpl implements HashtagsInteractor {
    private HashtagsRepository repository;

    public HashtagsInteractorImpl(HashtagsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getHashtags();
    }
}
