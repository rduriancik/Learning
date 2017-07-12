package edu.galileo.mvp;

import android.text.TextUtils;

/**
 * Created by robert on 12.7.2017.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginModel.OnLoginFinishedListener {

    private LoginView mLoginView;
    private LoginModel mLoginModel;

    public LoginPresenterImpl(LoginView mLoginView) {
        this.mLoginView = mLoginView;
        this.mLoginModel = new LoginModelImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mLoginView.setPasswordError(R.string.error_invalid_password);
            return;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            mLoginView.setUsernameError(R.string.error_field_required);
            return;
        } else if (!isEmailValid(username)) {
            mLoginView.setUsernameError(R.string.error_invalid_email);
            return;
        }

        mLoginView.showProgress(true);
        mLoginModel.login(username, password, this);
    }


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    public void onCancelled() {
        mLoginView.showProgress(false);
    }

    @Override
    public void onPasswordError() {
        mLoginView.showProgress(false);
        mLoginView.setPasswordError(R.string.error_incorrect_password);
    }

    @Override
    public void onSuccess() {
        mLoginView.showProgress(false);
        mLoginView.successAction();
    }
}
