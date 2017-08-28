package com.example.robert.photofeed.login;

/**
 * Created by robert on 28.8.2017.
 */

public class LoginInteractorImpl implements LoginInteractor {
    private LoginRepository repository;

    public LoginInteractorImpl(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String email, String password) {
        repository.signIn(email, password);
    }
}
