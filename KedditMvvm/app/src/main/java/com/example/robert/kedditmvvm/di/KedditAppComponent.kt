package com.example.robert.kedditmvvm.di

import com.example.robert.kedditmvvm.KedditApp
import com.example.robert.kedditmvvm.di.activities.ActivityBuilder
import com.example.robert.kedditmvvm.di.fragments.NewsModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by robert on 15.10.2017.
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,
        ActivityBuilder::class,
        NewsModule::class))
interface KedditAppComponent {
    fun inject(app: KedditApp)
}