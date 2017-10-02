package com.example.robert.photolocation.util

import android.app.Activity
import android.content.Context.MODE_PRIVATE

/**
 * Created by robert on 2.10.2017.
 */

fun Activity.isFirstTimeAskingPermission(permission: String) =
        getSharedPreferences("permissions", MODE_PRIVATE).getBoolean(permission, true)

fun Activity.firstTimeAskingPermission(permission: String, isFirstTime: Boolean) {
    getSharedPreferences("permissions", MODE_PRIVATE).edit()
            .putBoolean(permission, isFirstTime)
            .apply()
}