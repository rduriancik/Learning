package com.example.robert.flickrlike.likedPhotosActivity.di;

import com.example.robert.flickrlike.entities.Photo;
import com.example.robert.flickrlike.libs.base.EventBus;
import com.example.robert.flickrlike.libs.base.ImageLoader;
import com.example.robert.flickrlike.likedPhotosActivity.LikedPhotosInteractor;
import com.example.robert.flickrlike.likedPhotosActivity.LikedPhotosInteractorImpl;
import com.example.robert.flickrlike.likedPhotosActivity.LikedPhotosPresenter;
import com.example.robert.flickrlike.likedPhotosActivity.LikedPhotosPresenterImpl;
import com.example.robert.flickrlike.likedPhotosActivity.LikedPhotosRepository;
import com.example.robert.flickrlike.likedPhotosActivity.LikedPhotosRepositoryImpl;
import com.example.robert.flickrlike.likedPhotosActivity.adapters.LikedPhotosAdapter;
import com.example.robert.flickrlike.likedPhotosActivity.ui.LikedPhotosView;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 23.8.2017.
 */

@Module
public class LikedPhotosModule {
    private LikedPhotosView view;

    public LikedPhotosModule(LikedPhotosView view) {
        this.view = view;
    }

    @Provides
    LikedPhotosPresenter provideLikedPhotosPresenter(LikedPhotosView view, EventBus eventBus, LikedPhotosInteractor interactor) {
        return new LikedPhotosPresenterImpl(view, eventBus, interactor);
    }

    @Provides
    LikedPhotosView provideLikedPhotosView() {
        return this.view;
    }

    @Provides
    LikedPhotosInteractor provideLikedPhotosInteractor(LikedPhotosRepository repository) {
        return new LikedPhotosInteractorImpl(repository);
    }

    @Provides
    LikedPhotosRepository provideLikedPhotosRepository(EventBus eventBus) {
        return new LikedPhotosRepositoryImpl(eventBus);
    }

    @Provides
    LikedPhotosAdapter provideLikedPhotosAdapter(List<Photo> photos, ImageLoader imageLoader) {
        return new LikedPhotosAdapter(photos, imageLoader);
    }

    @Provides
    List<Photo> provideEmptyList() {
        return new ArrayList<Photo>();
    }
}
