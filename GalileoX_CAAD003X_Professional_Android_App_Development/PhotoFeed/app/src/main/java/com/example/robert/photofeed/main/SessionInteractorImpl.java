package com.example.robert.photofeed.main;

/**
 * Created by robert on 2.9.2017.
 */

public class SessionInteractorImpl implements SessionInteractor {
    MainRepository repository;

    public SessionInteractorImpl(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void logout() {
        repository.logout();
    }
}
