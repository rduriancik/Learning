package edu.galileo.mvp;

/**
 * Created by robert on 12.7.2017.
 */

public interface LoginModel {

    interface OnLoginFinishedListener {

        void onCancelled();

        void onPasswordError();

        void onSuccess();
    }

    void login(String username, String password, OnLoginFinishedListener listener);

}
