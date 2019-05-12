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
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*

/**
 * Created by Robert Duriancik on 3/5/19.
 */

private const val TAG = "MainViewModel"

class MainViewModel(application: Application, private val mTaskRepository: TaskRepository) :
    AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val mCompositeDisposable = CompositeDisposable()

    val newTaskDescription = ObservableField<String>()

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String>
        get() = _toastText

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

    fun taskCheckedChanged(task: Task, isChecked: Boolean) {
        updateTask(task.apply { isDone = isChecked })
    }

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

    fun addTask() {
        newTaskDescription.get()
            ?.takeIf { it.isNotEmpty() }
            ?.let { description ->
                addTask(Task(description))
                newTaskDescription.set("")
            } ?: showToast(getApplication<Application>().getString(R.string.main_description_not_entered))
    }

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

    fun observeTasks(): Observable<DatabaseEvent<Task>> {
        return mTaskRepository.observeTasks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { throwable ->
                val errMsg = "Error while observing tasks"
                showToast(errMsg)
                Log.e(TAG, errMsg, throwable)
            }
    }

    private fun showToast(text: String) {
        _toastText.postValue(text)
    }

    override fun onCleared() {
        uiScope.cancel()
        mCompositeDisposable.dispose()
        super.onCleared()
    }
}