package com.example.robert.kedditmvvm.di.fragments

import android.support.v4.app.Fragment
import com.example.robert.kedditmvvm.NewsFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

/**
 * Created by robert on 15.10.2017.
 */
@Module
abstract class FragmentBuilder {
    @Binds
    @IntoMap
    @FragmentKey(NewsFragment::class)
    abstract fun bindNewsFragment(builder: NewsFragmentSubComponent.Builder): AndroidInjector.Factory<out Fragment>
}