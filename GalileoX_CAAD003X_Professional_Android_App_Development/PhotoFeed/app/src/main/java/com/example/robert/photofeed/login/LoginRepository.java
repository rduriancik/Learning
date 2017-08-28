package com.example.robert.photofeed.login;

/**
 * Created by robert on 28.8.2017.
 */

public interface LoginRepository {
    void signIn(String email, String password);

    void signUp(final String email, final String password);
}
