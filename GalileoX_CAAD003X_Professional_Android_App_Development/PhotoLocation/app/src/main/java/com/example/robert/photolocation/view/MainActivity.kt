package com.example.robert.photolocation.view

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.example.robert.photolocation.R
import com.example.robert.photolocation.databinding.ActivityMainBinding
import com.example.robert.photolocation.util.firstTimeAskingPermission
import com.example.robert.photolocation.util.isFirstTimeAskingPermission
import com.example.robert.photolocation.viewmodel.MainActivityViewModel
import dagger.android.AndroidInjection
import java.io.IOException
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    companion object {
        @JvmField
        val REQUEST_IMAGE_CAPTURE = 1
        @JvmField
        val REQUEST_LOCATION_PERMISSION = 2
        @JvmField
        val RESTORE_KEY_LOCATION = "location"
        @JvmField
        val RESTORE_KEY_URI = "uri"
    }

    @Inject lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    private var photoUri: Uri? = null
    private var photoLastLocation: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java).apply {
            location.observe(this@MainActivity, Observer { t ->
                photoLastLocation = t
            })
        }

        binding.callback = object : TakePhotoClickCallback {
            override fun onTakePhotoButtonClick() {
                val permission = ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION)
                if (permission == PackageManager.PERMISSION_GRANTED) {
                    takePicture()
                } else {
                    requestLocationPermission()
                }
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        photoLastLocation = savedInstanceState.getParcelable(RESTORE_KEY_LOCATION)
        photoUri = savedInstanceState.getParcelable(RESTORE_KEY_URI)

        updateUI()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(RESTORE_KEY_LOCATION, photoLastLocation)
        outState.putParcelable(RESTORE_KEY_URI, photoUri)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            updateUI()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_LOCATION_PERMISSION &&
                grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            takePicture()
        }
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_LOCATION_PERMISSION)
        } else {
            if (isFirstTimeAskingPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
                firstTimeAskingPermission(Manifest.permission.ACCESS_FINE_LOCATION, false)
                ActivityCompat.requestPermissions(this@MainActivity,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_LOCATION_PERMISSION)
            } else {
                AlertDialog.Builder(this)
                        .setMessage("You need to grant access to your location for this application " +
                                "in the application settings in order to use this feature")
                        .setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ -> /**/ })
                        .setPositiveButton("Grant Access", DialogInterface.OnClickListener { _, _ ->
                            val intent = Intent().apply {
                                action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                                data = Uri.fromParts("package", this@MainActivity.packageName, null)

                            }
                            startActivity(intent)
                        })
                        .show()
            }

        }
    }

    private fun updateUI() {
        binding.image.setImageURI(photoUri)

        photoLastLocation?.let {
            if (binding.photoLocation.text != getString(R.string.photo_location_text)) {
                try {
                    val geocoder = Geocoder(this, Locale.getDefault())
                    val addresses = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                    binding.photoLocation.text = "Photo at ... Country: ${addresses.get(0).countryName}" +
                            ", State: ${addresses.get(0).adminArea}, City: ${addresses.get(0).locality}"
                } catch (e: IOException) {
                    e.printStackTrace()
                    binding.photoLocation.text = "Photo at ... $photoLastLocation"
                }
            }
        }
    }

    private fun takePicture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).let {
            if (it.resolveActivity(packageManager) != null) {
                photoUri = viewModel.getNewFileUri()
                it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(it, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

}
