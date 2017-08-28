package com.example.robert.photofeed.login;

/**
 * Created by robert on 28.8.2017.
 */

public class SignupInteractorImpl implements SignupInteractor {
    private LoginRepository repository;

    public SignupInteractorImpl(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String email, String password) {
        repository.signUp(email, password);
    }
}
