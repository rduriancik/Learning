package com.example.robert.mvvmsampleapp.di

import com.example.robert.mvvmsampleapp.view.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by robert on 23.9.2017.
 */
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    abstract fun contributeMainActivity(): MainActivity
}