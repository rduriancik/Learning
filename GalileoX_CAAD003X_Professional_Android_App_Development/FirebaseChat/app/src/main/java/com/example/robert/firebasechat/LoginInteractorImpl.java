package com.example.robert.firebasechat;

/**
 * Created by robert on 14.7.2017.
 */

class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository loginRepository;

    public LoginInteractorImpl() {
        this.loginRepository = new LoginRepositoryImpl();
    }

    @Override
    public void checkAlreadyAuthenticated() {

    }

    @Override
    public void doSignUp(String email, String password) {

    }

    @Override
    public void doSignIn(String email, String password) {

    }
}
