package com.example.robert.photolocation.model

import android.net.Uri

/**
 * Created by robert on 30.9.2017.
 */
interface FileManager {
    fun getNewFileUri(): Uri
    fun removeFile(uri: Uri)
}