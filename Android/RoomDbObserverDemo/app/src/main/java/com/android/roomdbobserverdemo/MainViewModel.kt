package com.android.roomdbobserverdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Robert Duriancik on 3/5/19.
 */
class MainViewModel(taskRepository: TaskRepository) : ViewModel() {

    private val _task = MutableLiveData<DatabaseEvent<Task>>()
    val task: LiveData<DatabaseEvent<Task>>
        get() = _task

    fun deleteTask(task: Task) {

    }

    fun updateTask(task: Task) {

    }

    fun addTask(task: Task) {

    }
}