package com.example.robert.mvvmsampleapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.ObservableField
import com.example.robert.mvvmsampleapp.service.model.Project
import com.example.robert.mvvmsampleapp.service.repository.ProjectRepository

/**
 * Created by robert on 13.9.2017.
 */
class ProjectViewModel(application: Application, val projectId: String) : AndroidViewModel(application) {

    val projectObservable = ProjectRepository().getProjectDetails("Google", projectId)
    val project = ObservableField<Project>()

    fun setProject(project: Project) {
        this.project.set(project)
    }

    /**
     * A creator is used to inject the project ID into the ViewModel
     */
    class Factory(private val application: Application, private val projectId: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>?): T =
                ProjectViewModel(application, projectId) as T
    }
}