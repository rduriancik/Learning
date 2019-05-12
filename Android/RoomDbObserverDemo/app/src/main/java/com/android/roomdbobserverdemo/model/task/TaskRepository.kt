package com.android.roomdbobserverdemo.model.task

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.roomdbobserverdemo.model.utility.DatabaseEvent
import com.android.roomdbobserverdemo.model.utility.DatabaseEventType
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Robert Duriancik on 3/12/19.
 */
class TaskRepository private constructor(context: Context) {

    private val mTaskDao = Room.databaseBuilder(context, TaskDatabase::class.java, "tasks-database")
        .build()
        .taskDao()

    private val _databaseObserver = MutableLiveData<DatabaseEvent<Task>>()

    suspend fun addTask(task: Task) {
        withContext(Dispatchers.IO) {
            mTaskDao.insertTask(task)
            _databaseObserver.postValue(DatabaseEvent(DatabaseEventType.INSERTED, task))
        }
    }

    suspend fun deleteTask(task: Task) {
        withContext(Dispatchers.IO) {
            mTaskDao.deleteTask(task)
            _databaseObserver.postValue(DatabaseEvent(DatabaseEventType.REMOVED, task))
        }
    }

    suspend fun updateTask(task: Task) {
        withContext(Dispatchers.IO) {
            mTaskDao.updateTask(task)
            _databaseObserver.postValue(DatabaseEvent(DatabaseEventType.UPDATED, task))
        }
    }

    fun observeTasks(): Observable<DatabaseEvent<Task>> {
        return mTaskDao.getAll()
            .flatMapObservable { Observable.fromIterable(it) }
            .map {
                DatabaseEvent(
                    DatabaseEventType.INSERTED,
                    it
                )
            }
            .concatWith(mObserverSubject)
    }

    companion object {
        @Volatile
        private var instance: TaskRepository? = null

        fun getInstance(context: Context): TaskRepository {
            return instance ?: synchronized(this) {
                instance
                    ?: TaskRepository(context.applicationContext).also { instance = it }
            }
        }
    }
}