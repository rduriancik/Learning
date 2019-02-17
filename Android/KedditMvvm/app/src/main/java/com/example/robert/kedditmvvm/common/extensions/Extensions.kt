package com.example.robert.kedditmvvm.common.extensions

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by robert on 11.10.2017.
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun ImageView.load(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(android.R.drawable.ic_menu_gallery).into(this)
    } else {
        Picasso.with(context).load(imageUrl).into(this)
    }
}
