package com.example.robert.kedditmvvm.di.fragments

import com.example.robert.kedditmvvm.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by robert on 15.10.2017.
 */
@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector
    abstract fun bindNewsFragment(): NewsFragment
}