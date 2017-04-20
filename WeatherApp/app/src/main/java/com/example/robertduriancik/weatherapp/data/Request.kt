package com.example.robertduriancik.weatherapp.data

import android.util.Log
import java.net.URL


/**
 * Created by robert-ntb on 4/20/17.
 */

class Request(val url: String) {
    fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }
}