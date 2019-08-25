package com.android.roomdbobserverdemo.viewmodel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.roomdbobserverdemo.R
import com.android.roomdbobserverdemo.model.task.Task
import com.android.roomdbobserverdemo.model.task.TaskRepository
import com.android.roomdbobserverdemo.model.utility.DatabaseEvent
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by Robert Duriancik on 3/5/19.
 */

private const val TAG = "MainViewModel"

class MainViewModel(application: Application, private val mTaskRepository: TaskRepository) :
    AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val newTaskDescription = ObservableField<String>()

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String> = _toastText

    @ExperimentalCoroutinesApi
    fun deleteTask(task: Task) {
        uiScope.launch {
            try {
                mTaskRepository.deleteTask(task)
                Log.d(TAG, "Task $task deleted")
            } catch (e: Exception) {
                val errMsg = "Error while deleting tasks $task"
                showToast(errMsg)
                Log.e(TAG, errMsg, e)
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun taskCheckedChanged(task: Task, isChecked: Boolean) {
        updateTask(task.apply { isDone = isChecked })
    }

    @ExperimentalCoroutinesApi
    fun updateTask(task: Task) {
        uiScope.launch {
            try {
                mTaskRepository.updateTask(task)
                Log.d(TAG, "Task $task updated")
            } catch (e: Exception) {
                val errMsg = "Error while updating tasks $task"
                showToast(errMsg)
                Log.e(TAG, errMsg, e)
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun addTask() {
        newTaskDescription.get()
            ?.takeIf { it.isNotEmpty() }
            ?.let { description ->
                addTask(Task(description))
                newTaskDescription.set("")
            }
            ?: showToast(getApplication<Application>().getString(R.string.main_description_not_entered))
    }

    @ExperimentalCoroutinesApi
    private fun addTask(task: Task) {
        uiScope.launch {
            try {
                mTaskRepository.addTask(task)
                Log.d(TAG, "Task $task added")
            } catch (e: Exception) {
                val errMsg = "Error while adding tasks $task"
                showToast(errMsg)
                Log.e(TAG, errMsg, e)
            }
        }
    }

    @ExperimentalCoroutinesApi
    suspend fun observeTasks(): Flow<DatabaseEvent<Task>> {
        return mTaskRepository.observeTasks()
    }

    private fun showToast(text: String) {
        _toastText.postValue(text)
    }

    override fun onCleared() {
        uiScope.cancel()
        super.onCleared()
    }
}