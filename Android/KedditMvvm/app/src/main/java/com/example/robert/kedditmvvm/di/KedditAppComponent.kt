package com.example.robert.kedditmvvm.di

import android.app.Application
import com.example.robert.kedditmvvm.KedditApp
import com.example.robert.kedditmvvm.di.activities.ActivityBuilder
import com.example.robert.kedditmvvm.di.fragments.NewsModule
import dagger.BindsInstance
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
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): KedditAppComponent
    }
    fun inject(app: KedditApp)
}