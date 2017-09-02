package com.example.robert.photofeed.domain.di;

import android.content.Context;
import android.location.Geocoder;

import com.example.robert.photofeed.domain.FirebaseApiHelper;
import com.example.robert.photofeed.domain.Util;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 2.9.2017.
 */

@Module
public class DomainModule {

    @Provides
    @Singleton
    FirebaseApiHelper providesFirebaseApiHelper() {
        return new FirebaseApiHelper();
    }

    @Provides
    @Singleton
    Util providesUtil(Geocoder geocoder) {
        return new Util(geocoder);
    }

    @Provides
    @Singleton
    Geocoder providesGeocoder(Context context) {
        return new Geocoder(context);
    }
}
