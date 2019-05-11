package com.android.roomdbobserverdemo.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.roomdbobserverdemo.model.task.TaskRepository

/**
 * Created by Robert Duriancik on 3/12/19.
 */
class MainViewModelFactory(private val mApplication: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) return MainViewModel(
            mApplication,
            TaskRepository.getInstance(mApplication.applicationContext)
        ) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}