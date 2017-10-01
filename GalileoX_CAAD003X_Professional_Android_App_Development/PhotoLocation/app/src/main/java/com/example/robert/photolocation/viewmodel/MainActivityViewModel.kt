package com.example.robert.photolocation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.net.Uri
import com.example.robert.photolocation.model.FileManager
import com.example.robert.photolocation.model.LocationProvider

/**
 * Created by robert on 29.9.2017.
 */
class MainActivityViewModel(private val fileManager: FileManager,
                            private val locationProvider: LocationProvider) : ViewModel() {

    private val fileUri: MutableLiveData<Uri> = MutableLiveData()
    val location = Transformations.switchMap(fileUri) { _ -> locationProvider.getLastKnownLocation() }

    fun newPhotoFileUri() {
        if (fileUri.value != null) {
            fileManager.removeFile(fileUri.value!!)
        }

        fileUri.value = fileManager.getNewFileUri()
    }

    fun getFileUri(): LiveData<Uri> = fileUri

    override fun onCleared() {
        fileUri.value?.let { fileManager.removeFile(it) }
        super.onCleared()
    }
}