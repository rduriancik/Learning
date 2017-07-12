package edu.galileo.mvp;

/**
 * Created by robert on 12.7.2017.
 */

public interface LoginView {

    void showProgress(boolean showProgress);

    void setUsernameError(int messageResId);

    void setPasswordError(int messageResId);

}
