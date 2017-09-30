package com.example.robert.photolocation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices

/**
 * Created by robert on 30.9.2017.
 */
class GoogleLocationProvider(applicationContext: Context) : LocationProvider {
    private val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(applicationContext.applicationContext)

    @Throws(SecurityException::class)
    override fun getLastKnownLocation(): LiveData<Location> {
        val locationData = MutableLiveData<Location>()
        fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location -> locationData.value = location }
                .addOnFailureListener { locationData.value = null }

        return locationData
    }
}