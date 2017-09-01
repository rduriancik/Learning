package com.example.robert.photofeed.login;

import com.example.robert.photofeed.libs.base.EventBus;
import com.example.robert.photofeed.login.events.LoginEvent;
import com.example.robert.photofeed.login.ui.LoginView;

import org.greenrobot.eventbus.Subscribe;

import static com.example.robert.photofeed.login.events.LoginEvent.onFailedToRecoverSession;
import static com.example.robert.photofeed.login.events.LoginEvent.onSignInError;
import static com.example.robert.photofeed.login.events.LoginEvent.onSignInSuccess;
import static com.example.robert.photofeed.login.events.LoginEvent.onSignUpError;
import static com.example.robert.photofeed.login.events.LoginEvent.onSignUpSuccess;

/**
 * Created by robert on 28.8.2017.
 */

public class LoginPresenterImpl implements LoginPresenter {
    EventBus eventBus;
    LoginView loginView;
    LoginInteractor loginInteractor;
    SignupInteractor signupInteractor;

    public LoginPresenterImpl(EventBus eventBus, LoginView loginView, LoginInteractor loginInteractor, SignupInteractor signupInteractor) {
        this.eventBus = eventBus;
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
        this.signupInteractor = signupInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void login(String email, String password) {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }

        loginInteractor.execute(email, password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }

        signupInteractor.execute(email, password);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        loginView = null;
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()) {
            case onSignInError:
                onSignInError(event.getErrorMessage());
                break;
            case onSignUpError:
                onSignUpError(event.getErrorMessage());
                break;
            case onSignInSuccess:
                onSignInSuccess(event.getLoggedUserEmail());
                break;
            case onSignUpSuccess:
                onSignUpSuccess();
                break;
            case onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }

    private void onSignInError(String error) {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }

    private void onSignUpError(String error) {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }

    private void onSignInSuccess(String email) {
        if (loginView != null) {
            loginView.setUserEmail(email);
            loginView.navigateToMainScreen();
        }
    }

    private void onSignUpSuccess() {
        loginView.newUserSuccess();
    }

    private void onFailedToRecoverSession() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
        }
    }
}
