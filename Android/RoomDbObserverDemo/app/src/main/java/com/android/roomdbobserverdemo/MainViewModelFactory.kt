package com.android.roomdbobserverdemo

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Robert Duriancik on 3/12/19.
 */
class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val appContext = context.applicationContext

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) return MainViewModel(
            TaskRepository.getInstance(appContext)
        ) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}