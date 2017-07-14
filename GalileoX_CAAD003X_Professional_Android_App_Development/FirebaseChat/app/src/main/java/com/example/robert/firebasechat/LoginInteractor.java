package com.example.robert.firebasechat;

/**
 * Created by robert on 14.7.2017.
 */

public interface LoginInteractor {
    void checkAlreadyAuthenticated();

    void doSignUp(String email, String password);

    void doSignIn(String email, String password);
}
