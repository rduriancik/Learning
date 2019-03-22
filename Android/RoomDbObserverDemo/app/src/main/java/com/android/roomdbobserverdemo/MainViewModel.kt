package com.android.roomdbobserverdemo

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by Robert Duriancik on 3/5/19.
 */
class MainViewModel(application: Application, private val mTaskRepository: TaskRepository) :
    AndroidViewModel(application) {

    val newTaskDescription = ObservableField<String>()

    private val _task = MutableLiveData<DatabaseEvent<Task>>()
    val task: LiveData<DatabaseEvent<Task>>
        get() = _task

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String>
        get() = _toastText

    fun deleteTask(task: Task) {
        mTaskRepository.deleteTask(task)
    }

    fun updateTask(task: Task) {
        mTaskRepository.updateTask(task)
    }

    fun addTask() {
        newTaskDescription.get()
            ?.takeIf { it.isNotEmpty() }
            ?.let { description ->
                val newTask = Task(description)
                mTaskRepository.addTask(newTask)
                newTaskDescription.set("")
            } ?: showToast(getApplication<Application>().getString(R.string.main_description_not_entered))
    }

    fun observeTasks() {
        // TODO
    }

    private fun showToast(text: String) {
        _toastText.postValue(text)
    }
}