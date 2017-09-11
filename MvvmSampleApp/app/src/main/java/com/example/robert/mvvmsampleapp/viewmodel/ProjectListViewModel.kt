package com.example.robert.mvvmsampleapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.robert.mvvmsampleapp.service.model.Project

/**
 * Created by robert on 11.9.2017.
 */
class ProjectListViewModel(application: Application) : AndroidViewModel(application) {
    val projectListObservable: LiveData<List<Project>>

    init {
        projectListObservable = MutableLiveData<List<Project>>() // FIXME
    }
}