package com.example.robert.mvvmsampleapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.example.robert.mvvmsampleapp.service.repository.ProjectRepository
import javax.inject.Inject

/**
 * Created by robert on 11.9.2017.
 */
class ProjectListViewModel @Inject constructor(projectRepository: ProjectRepository, application: Application)
    : AndroidViewModel(application) {
    val projectListObservable = projectRepository.getProjectList("Google")
}