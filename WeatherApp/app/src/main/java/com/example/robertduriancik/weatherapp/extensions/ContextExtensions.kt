package com.example.robertduriancik.weatherapp.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created by robert on 30.7.2017.
 */

fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)