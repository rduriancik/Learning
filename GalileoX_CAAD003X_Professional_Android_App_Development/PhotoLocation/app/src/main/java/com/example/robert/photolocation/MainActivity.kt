package com.example.robert.photolocation

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import com.example.robert.photolocation.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        @JvmField
        val REQUEST_IMAGE_CAPTURE = 1
    }

    private var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.callback = object : TakePhotoClickCallback {
            override fun onTakePhotoButtonClick() {
                takePicture()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            image.setImageURI(photoUri)
        }
    }

    private fun takePicture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).let {
            if (it.resolveActivity(packageManager) != null) {
                photoUri = getPhotoFileUri()
                it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(it, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun getPhotoFileUri(): Uri {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val photoFile = File.createTempFile(
                "PHOTO_$timeStamp",
                ".jpg",
                storageDir
        )

        return FileProvider.getUriForFile(this,
                "com.example.android.fileprovider",
                photoFile)
    }

}
