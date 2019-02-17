package com.example.robert.photolocation.di

import android.app.Application
import com.example.robert.photolocation.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by robert on 1.10.2017.
 */
@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class))
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}