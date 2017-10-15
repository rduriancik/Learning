package com.example.robert.kedditmvvm.di.activities

import com.example.robert.kedditmvvm.di.fragments.NewsFragmentSubComponent
import dagger.Module

/**
 * Created by robert on 15.10.2017.
 */
@Module(subcomponents = arrayOf(NewsFragmentSubComponent::class))
class MainActivityModule