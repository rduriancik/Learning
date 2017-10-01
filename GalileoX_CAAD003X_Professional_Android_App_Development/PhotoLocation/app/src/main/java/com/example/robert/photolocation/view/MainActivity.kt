package com.example.robert.photolocation.view

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import com.example.robert.photolocation.R
import com.example.robert.photolocation.databinding.ActivityMainBinding
import com.example.robert.photolocation.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    companion object {
        @JvmField
        val REQUEST_IMAGE_CAPTURE = 1
    }

    @Inject lateinit var factory: ViewModelProvider.Factory
    private var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java).apply {
            getFileUri().observe(this@MainActivity, android.arch.lifecycle.Observer { uri ->
                photoUri = uri
                takePicture()
            })

            location.observe(this@MainActivity, android.arch.lifecycle.Observer { location ->
                if (location != null) {
                    binding.photoLocation.text = location.toString()
                } else {
                    binding.photoLocation.text = "Location not provided"
                }
            })
        }
        binding.callback = object : TakePhotoClickCallback {
            override fun onTakePhotoButtonClick() {
                viewModel.newPhotoFileUri()
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
                it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(it, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

}
