package com.example.robert.mvvmsampleapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.databinding.ObservableField
import android.util.Log
import com.example.robert.mvvmsampleapp.service.model.Project
import com.example.robert.mvvmsampleapp.service.repository.ProjectRepository
import javax.inject.Inject

/**
 * Created by robert on 13.9.2017.
 */
class ProjectViewModel @Inject constructor(projectRepository: ProjectRepository, application: Application)
    : AndroidViewModel(application) {

    companion object {
        private const val TAG: String = "ProjectViewModel"
        private val ABSENT = MutableLiveData<Project>()

    }

    val projectObservable: LiveData<Project>
    private val projectID: MutableLiveData<String>
    val project = ObservableField<Project>()

    init {
        ProjectViewModel.ABSENT.value = null
        this.projectID = MutableLiveData<String>()
        projectObservable = Transformations.switchMap(projectID) { input ->
            if (input.isEmpty()) {
                Log.i(TAG, "ProjectViewModel projectID is absent")
                ABSENT
            } else {
                Log.i(TAG, "ProjectViewModel projectID is ${projectID.value}")
                projectRepository.getProjectDetails("Google", projectID.value!!)
            }
        }
    }

    fun setProject(project: Project) {
        this.project.set(project)
    }

    fun setProjectID(projectID: String) {
        this.projectID.value = projectID
    }
}