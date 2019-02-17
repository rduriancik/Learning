package com.example.robert.photolocation.model

import android.Manifest
import android.arch.lifecycle.LiveData
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.support.v4.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

/**
 * Created by robert on 1.10.2017.
 */
class LocationLiveData(private val appContext: Context) : LiveData<Location>() {

    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(appContext.applicationContext)
    }
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            value = p0?.lastLocation
        }
    }

    override fun onInactive() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    override fun onActive() {
        super.onActive()
        if (ActivityCompat.checkSelfPermission(appContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        val locationRequest = LocationRequest.create()
        val looper = Looper.myLooper()
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, looper)
    }
}