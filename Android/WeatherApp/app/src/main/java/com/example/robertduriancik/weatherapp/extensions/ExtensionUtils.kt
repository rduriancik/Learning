package com.example.robertduriancik.weatherapp.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by robert on 30.7.2017.
 */

fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}