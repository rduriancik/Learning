package com.example.robert.kedditmvvm.di.activities

import android.app.Activity
import com.example.robert.kedditmvvm.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by robert on 15.10.2017.
 */
@Module
abstract class ActivityBuilder {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivity(builder: MainActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>
}