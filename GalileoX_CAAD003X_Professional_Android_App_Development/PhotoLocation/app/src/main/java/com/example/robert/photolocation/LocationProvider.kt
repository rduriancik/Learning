package com.example.robert.photolocation

import android.arch.lifecycle.LiveData
import android.location.Location

/**
 * Created by robert on 30.9.2017.
 */
interface LocationProvider {
    fun getLastKnownLocation(): LiveData<Location>
}