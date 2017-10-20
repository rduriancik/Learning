package com.example.robert.kedditmvvm.common

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.example.robert.kedditmvvm.common.extensions.load

/**
 * Created by robert on 20.10.2017.
 */

@BindingAdapter("bind:imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String) {
    imageView.load(imageUrl)
}