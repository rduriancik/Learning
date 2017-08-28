package com.example.robert.photofeed.login.ui;

/**
 * Created by robert on 28.8.2017.
 */

public interface LoginView {
    void enableInputs();

    void disableInputs();

    void showProgress();

    void hideProgress();

    void handleSignUp();

    void handleSignIn();

    void newUserSuccess();

    void navigateToMainScreen();

    void setUserEmail(String email);

    void loginError(String error);

    void newUserError(String error);
}
