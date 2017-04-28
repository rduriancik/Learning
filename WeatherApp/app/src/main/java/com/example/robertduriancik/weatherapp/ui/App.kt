package com.example.robertduriancik.weatherapp.ui

import android.app.Application
import com.example.robertduriancik.weatherapp.ui.utils.DelegatesExt

/**
 * Created by robert-ntb on 4/28/17.
 */

class App : Application() {
    companion object {
        var instance: App by DelegatesExt.notNullSingelValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}