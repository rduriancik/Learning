package com.example.robert.flickrlike.main;

import android.text.TextUtils;

import com.example.robert.flickrlike.main.ui.MainView;

/**
 * Created by robert on 20.8.2017.
 */

public class MainPresenterImpl implements MainPresenter {
    private MainView view;

    public MainPresenterImpl(MainView view) {
        this.view = view;
    }

    @Override
    public void onSearchClick(String tags) {
        if (view != null) {
            if (!TextUtils.isEmpty(tags)) {
                view.navigateToPhotoActivity(tags);
            } else {
                view.onError("Enter tags");
            }
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
