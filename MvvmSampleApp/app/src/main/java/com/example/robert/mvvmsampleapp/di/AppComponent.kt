package com.example.robert.mvvmsampleapp.di

import android.app.Application
import com.example.robert.mvvmsampleapp.MVVMApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by robert on 23.9.2017.
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, MainActivityModule::class))
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MVVMApplication)
}