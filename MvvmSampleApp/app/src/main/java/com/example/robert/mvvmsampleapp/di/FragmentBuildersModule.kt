package com.example.robert.mvvmsampleapp.di

import com.example.robert.mvvmsampleapp.view.ui.ProjectFragment
import com.example.robert.mvvmsampleapp.view.ui.ProjectListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by robert on 23.9.2017.
 */
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeProjectFragment(): ProjectFragment

    @ContributesAndroidInjector
    abstract fun contributeProjectListFragment(): ProjectListFragment
}