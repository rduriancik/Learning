package com.example.robert.photofeed.login.di;

import com.example.robert.photofeed.login.ui.LoginActivity;

import dagger.Component;

/**
 * Created by robert on 28.8.2017.
 */
@Component
public interface LoginComponent {
    void inject(LoginActivity activity);
}
