package com.example.robert.photofeed.login;

import com.example.robert.photofeed.login.events.LoginEvent;

/**
 * Created by robert on 28.8.2017.
 */

public interface LoginPresenter {
    void onCreate();

    void login(String email, String password);

    void registerNewUser(String email, String password);

    void onDestroy();

    void onEventMainThread(LoginEvent event);
}
