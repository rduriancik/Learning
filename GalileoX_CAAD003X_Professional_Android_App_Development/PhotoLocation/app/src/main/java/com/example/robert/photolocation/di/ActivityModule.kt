package com.example.robert.photolocation.di

import com.example.robert.photolocation.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by robert on 1.10.2017.
 */
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}