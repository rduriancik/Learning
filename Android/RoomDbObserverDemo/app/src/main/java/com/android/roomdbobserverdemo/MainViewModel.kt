package com.android.roomdbobserverdemo

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Robert Duriancik on 3/5/19.
 */

private const val TAG = "MainViewModel"

class MainViewModel(application: Application, private val mTaskRepository: TaskRepository) :
    AndroidViewModel(application) {

    private val mCompositeDisposable = CompositeDisposable()

    val newTaskDescription = ObservableField<String>()

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String>
        get() = _toastText

    fun deleteTask(task: Task) {
        val disposable = mTaskRepository.deleteTask(task)
            .subscribeOn(Schedulers.io())
            .subscribe({ Log.d(TAG, "Task $task deleted") }, { throwable ->
                val errMsg = "Error while deleting tasks $task"
                showToast(errMsg)
                Log.e(TAG, errMsg, throwable)
            })
        mCompositeDisposable.add(disposable)
    }

    fun taskCheckedChanged(task: Task, isChecked: Boolean) {
        updateTask(task.apply { isDone = isChecked })
    }

    fun updateTask(task: Task) {
        val disposable = mTaskRepository.updateTask(task)
            .subscribeOn(Schedulers.io())
            .subscribe({ Log.d(TAG, "Task $task updated") }, { throwable ->
                val errMsg = "Error while updating tasks $task"
                showToast(errMsg)
                Log.e(TAG, errMsg, throwable)
            })
        mCompositeDisposable.add(disposable)
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
        val disposable = mTaskRepository.addTask(task)
            .subscribeOn(Schedulers.io())
            .subscribe({ Log.d(TAG, "Task $task added") }, { throwable ->
                val errMsg = "Error while adding tasks $task"
                showToast(errMsg)
                Log.e(TAG, errMsg, throwable)
            })
        mCompositeDisposable.add(disposable)
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
        mCompositeDisposable.dispose()
        super.onCleared()
    }
}