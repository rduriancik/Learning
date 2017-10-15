package com.example.robert.kedditmvvm.di

import com.example.robert.kedditmvvm.di.activities.MainActivitySubComponent
import dagger.Module

/**
 * Created by robert on 15.10.2017.
 */
@Module(subcomponents = arrayOf(MainActivitySubComponent::class))
class KedditAppModule