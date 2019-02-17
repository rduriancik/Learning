package com.example.robert.photolocation.viewmodel

import android.arch.lifecycle.ViewModel
import android.net.Uri
import com.example.robert.photolocation.model.FileManager
import com.example.robert.photolocation.model.LocationProvider

/**
 * Created by robert on 29.9.2017.
 */
class MainActivityViewModel(private val fileManager: FileManager,
                            locationProvider: LocationProvider) : ViewModel() {

    val location = locationProvider.getLocation()
    private var newFileUri: Uri = fileManager.getNewFileUri()

    fun getNewFileUri(): Uri {
        fileManager.removeFile(newFileUri)
        newFileUri = fileManager.getNewFileUri()

        return newFileUri
    }

    override fun onCleared() {
        fileManager.removeFile(newFileUri)
        super.onCleared()
    }
}