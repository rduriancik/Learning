package com.example.robertduriancik.weatherapp.ui

import android.app.Application

/**
 * Created by robert-ntb on 4/28/17.
 */

class App : Application() {
    companion object {
        private var instance: Application? = null
        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}