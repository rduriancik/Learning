package com.example.robert.firebasechat;

/**
 * Created by robert on 14.7.2017.
 */

public interface LoginRepository {
    void signUp(final String email, final String password);

    void signIn(String email, String password);

    void checkAlreadyAuthenticated();
}
