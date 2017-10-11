package com.example.robert.kedditmvvm.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by robert on 11.10.2017.
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
