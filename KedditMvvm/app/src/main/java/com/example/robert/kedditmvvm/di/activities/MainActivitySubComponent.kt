package com.example.robert.kedditmvvm.di.activities

import com.example.robert.kedditmvvm.MainActivity
import com.example.robert.kedditmvvm.di.fragments.FragmentBuilder
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by robert on 15.10.2017.
 */
@Subcomponent(modules = arrayOf(AndroidSupportInjectionModule::class,
        FragmentBuilder::class,
        MainActivityModule::class))
interface MainActivitySubComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}