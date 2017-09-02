package com.example.robert.photofeed.main.di;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.robert.photofeed.domain.FirebaseApiHelper;
import com.example.robert.photofeed.libs.base.EventBus;
import com.example.robert.photofeed.libs.base.ImageStorage;
import com.example.robert.photofeed.main.MainPresenter;
import com.example.robert.photofeed.main.MainPresenterImpl;
import com.example.robert.photofeed.main.MainRepository;
import com.example.robert.photofeed.main.MainRepositoryImpl;
import com.example.robert.photofeed.main.SessionInteractor;
import com.example.robert.photofeed.main.SessionInteractorImpl;
import com.example.robert.photofeed.main.UploadInteractor;
import com.example.robert.photofeed.main.UploadInteractorImpl;
import com.example.robert.photofeed.main.ui.MainView;
import com.example.robert.photofeed.main.ui.adapters.MainSectionsPagerAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 2.9.2017.
 */

@Module
public class MainModule {
    private MainView view;
    private String[] titles;
    private Fragment[] fragments;
    private FragmentManager fragmentManager;

    public MainModule(MainView view, String[] titles, Fragment[] fragments, FragmentManager fragmentManager) {
        this.view = view;
        this.titles = titles;
        this.fragments = fragments;
        this.fragmentManager = fragmentManager;
    }

    @Provides
    @Singleton
    MainView providesMainView() {
        return this.view;
    }

    @Provides
    @Singleton
    MainPresenter providesMainPresenter(MainView view, EventBus eventBus, UploadInteractor interactor, SessionInteractor sessionInteractor) {
        return new MainPresenterImpl(view, eventBus, interactor, sessionInteractor);
    }

    @Provides
    @Singleton
    UploadInteractor providesUploadInteractor(MainRepository repository) {
        return new UploadInteractorImpl(repository);
    }

    @Provides
    @Singleton
    SessionInteractor providesSessionInteractor(MainRepository repository) {
        return new SessionInteractorImpl(repository);
    }

    @Provides
    @Singleton
    MainRepository providesMainRepository(EventBus eventBus, FirebaseApiHelper firebaseApiHelper, ImageStorage imageStorage) {
        return new MainRepositoryImpl(eventBus, firebaseApiHelper, imageStorage);
    }

    @Provides
    @Singleton
    MainSectionsPagerAdapter providesAdapter(FragmentManager fragmentManager, Fragment[] fragments, String[] titles) {
        return new MainSectionsPagerAdapter(fragmentManager, titles, fragments);
    }

    @Provides
    @Singleton
    FragmentManager providesFragmentManager() {
        return this.fragmentManager;
    }

    @Provides
    @Singleton
    Fragment[] providesFragmentArray() {
        return this.fragments;
    }

    @Provides
    @Singleton
    String[] providesStringArray() {
        return this.titles;
    }


}
