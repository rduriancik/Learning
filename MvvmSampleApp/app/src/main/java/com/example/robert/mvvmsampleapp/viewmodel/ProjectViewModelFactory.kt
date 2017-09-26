package com.example.robert.mvvmsampleapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.robert.mvvmsampleapp.di.ViewModelSubComponent
import java.util.concurrent.Callable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by robert on 23.9.2017.
 */
@Singleton
class ProjectViewModelFactory @Inject constructor(viewModelSubComponent: ViewModelSubComponent) : ViewModelProvider.Factory {
    private val creators = mutableMapOf<Class<*>, Callable<out ViewModel>>()

    init {
        creators.put(ProjectViewModel::class.java, Callable { viewModelSubComponent.projectViewModel() })
        creators.put(ProjectListViewModel::class.java, Callable { viewModelSubComponent.projectListViewModel() })
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Callable<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            creators.entries.firstOrNull { modelClass.isAssignableFrom(it.key) }?.let { creator = it.value }
        }

        if (creator == null) {
            throw IllegalArgumentException("Unknown model class ${modelClass}")
        }

        try {
            return creator!!.call() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}