package com.example.robert.firebasechat.login;

import com.example.robert.firebasechat.lib.EventBus;
import com.example.robert.firebasechat.lib.GreenRobotEventBus;
import com.example.robert.firebasechat.login.events.LoginEvent;
import com.example.robert.firebasechat.login.ui.LoginView;


/**
 * Created by robert on 14.7.2017.
 */

public class LoginPresenterImpl implements LoginPresenter {
    private EventBus eventBus;
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticatedUser() {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.checkAlreadyAuthenticated();
    }

    @Override
    public void onEventMainThread(LoginEvent loginEvent) {
        switch (loginEvent.getEventType()) {
            case LoginEvent.onSignInError:
                onSignInError(loginEvent.getErrorMessage());
                break;
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(loginEvent.getErrorMessage());
                break;
            case LoginEvent.onSignUpSuccess:
                onSignUpSuccess();
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }


    @Override
    public void validateLogin(String email, String password) {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignIn(email, password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignUp(email, password);
    }

    private void onSignInError(String error) {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }

    private void onSignInSuccess() {
        if (loginView != null) {
            loginView.navigateToMainScreen();
        }
    }

    private void onSignUpError(String error) {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }

    private void onSignUpSuccess() {
        if (loginView != null) {
            loginView.newUserSuccess();
        }
    }

    private void onFailedToRecoverSession() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
        }
    }
}
