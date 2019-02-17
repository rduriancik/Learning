package com.example.robert.photolocation.di

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.robert.photolocation.model.FileManager
import com.example.robert.photolocation.model.GoogleLocationProvider
import com.example.robert.photolocation.model.LocationProvider
import com.example.robert.photolocation.model.SimpleFileManager
import com.example.robert.photolocation.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by robert on 1.10.2017.
 */
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideAppContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideSimpleFileManager(context: Context): FileManager = SimpleFileManager(context)

    @Singleton
    @Provides
    fun provideGoogleLocationProvider(context: Context): LocationProvider = GoogleLocationProvider(context)

    @Singleton
    @Provides
    fun provideViewModelFactory(fileManger: FileManager, locationProvider: LocationProvider): ViewModelProvider.Factory
            = ViewModelFactory(fileManger, locationProvider)
}