package com.example.robert.photofeed;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.robert.photofeed.domain.di.DomainModule;
import com.example.robert.photofeed.libs.di.LibsModule;
import com.example.robert.photofeed.login.di.DaggerLoginComponent;
import com.example.robert.photofeed.login.di.LoginComponent;
import com.example.robert.photofeed.login.di.LoginModule;
import com.example.robert.photofeed.login.ui.LoginView;
import com.example.robert.photofeed.main.di.DaggerMainComponent;
import com.example.robert.photofeed.main.di.MainComponent;
import com.example.robert.photofeed.main.di.MainModule;
import com.example.robert.photofeed.main.ui.MainView;

/**
 * Created by robert on 28.8.2017.
 */

public class PhotoFeedApp extends Application {
    private final static String EMAIL_KEY = "email";
    private LibsModule libsModule;
    private DomainModule domainModule;
    private PhotoFeedAppModule photoFeedAppModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initModules();
    }

    private void initModules() {
        libsModule = new LibsModule();
        domainModule = new DomainModule();
        photoFeedAppModule = new PhotoFeedAppModule(this);
    }

    public String getEmailKey() {
        return EMAIL_KEY;
    }

    /*public PhotoListComponent getPhotoListComponent(Fragment fragment, PhotoListView view, OnItemClickListener listener) {

    }*/

    /*public PhotoMapComponent getPhotoMapComponent(Fragment fragment, PhotoMapView view) {

    }*/

    public MainComponent getMainComponent(MainView view, FragmentManager fragmentManager, Fragment[] fragments, String[] titles) {
        return DaggerMainComponent.builder()
                .photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule)
                .libsModule(libsModule)
                .mainModule(new MainModule(view, titles, fragments, fragmentManager))
                .build();
    }

    public LoginComponent getLoginComponent(LoginView view) {
        return DaggerLoginComponent.builder()
                .photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule)
                .libsModule(libsModule)
                .loginModule(new LoginModule(view))
                .build();
    }
}
