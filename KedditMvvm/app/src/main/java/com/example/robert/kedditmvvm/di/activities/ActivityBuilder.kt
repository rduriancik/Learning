package com.example.robert.kedditmvvm.di.activities

import com.example.robert.kedditmvvm.MainActivity
import com.example.robert.kedditmvvm.di.fragments.FragmentBuilder
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by robert on 15.10.2017.
 */
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = arrayOf(FragmentBuilder::class))
    abstract fun bindMainActivity(): MainActivity
}