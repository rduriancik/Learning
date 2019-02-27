package com.example.android.dessertpusher

import android.app.Application
import timber.log.Timber

/**
 * Created by Robert Duriancik on 2/27/19.
 */
class PusherApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}