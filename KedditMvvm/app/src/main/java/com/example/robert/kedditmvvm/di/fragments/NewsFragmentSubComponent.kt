package com.example.robert.kedditmvvm.di.fragments

import com.example.robert.kedditmvvm.NewsFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by robert on 15.10.2017.
 */
@Subcomponent
interface NewsFragmentSubComponent : AndroidInjector<NewsFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<NewsFragment>()
}