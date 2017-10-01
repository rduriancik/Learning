package com.example.robert.photolocation.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.robert.photolocation.model.FileManager
import com.example.robert.photolocation.model.LocationProvider
import javax.inject.Inject

/**
 * Created by robert on 1.10.2017.
 */
class ViewModelFactory @Inject constructor(private val fileManager: FileManager,
                                           private val locationProvider: LocationProvider) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(fileManager, locationProvider) as T
    }
}