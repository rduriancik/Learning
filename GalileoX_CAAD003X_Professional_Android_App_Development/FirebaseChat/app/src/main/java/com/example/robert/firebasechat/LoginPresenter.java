package com.example.robert.firebasechat;

/**
 * Created by robert on 14.7.2017.
 */

public interface LoginPresenter {
    void onCreate();

    void onDestroy();

    void checkForAuthenticatedUser(LoginEvent event);

    void onEventMainThread();

    void validateLogin(String email, String password);

    void registerNewUser(String email, String password);

}
