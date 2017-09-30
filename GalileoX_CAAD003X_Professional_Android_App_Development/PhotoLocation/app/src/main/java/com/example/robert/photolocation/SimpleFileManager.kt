package com.example.robert.photolocation

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.support.v4.content.FileProvider
import android.util.Log
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by robert on 30.9.2017.
 */
class SimpleFileManager(context: Context) : FileManager {
    companion object {
        const val TAG = "SimpleFileManager"
    }

    private val context = context.applicationContext

    override fun getNewFileUri(): Uri {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val photoFile = File.createTempFile(
                "PHOTO_$timeStamp",
                ".jpg",
                storageDir
        )

        return FileProvider.getUriForFile(context,
                "com.example.android.fileprovider",
                photoFile)
    }

    override fun removeFile(uri: Uri) {
        val deleted = File(uri.path).delete()
        Log.d(TAG, "File was deleted = $deleted")
    }
}