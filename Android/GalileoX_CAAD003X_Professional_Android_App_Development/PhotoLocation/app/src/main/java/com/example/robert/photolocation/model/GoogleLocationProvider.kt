package com.example.robert.photolocation.model

import android.arch.lifecycle.LiveData
import android.content.Context
import android.location.Location

/**
 * Created by robert on 30.9.2017.
 */
class GoogleLocationProvider(private val applicationContext: Context) : LocationProvider {
    override fun getLocation(): LiveData<Location> = LocationLiveData(applicationContext)
}