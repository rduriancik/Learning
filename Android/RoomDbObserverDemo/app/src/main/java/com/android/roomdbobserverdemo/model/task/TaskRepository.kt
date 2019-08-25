package com.android.roomdbobserverdemo.model.task

import android.content.Context
import androidx.room.Room
import com.android.roomdbobserverdemo.model.utility.DatabaseEvent
import com.android.roomdbobserverdemo.model.utility.DatabaseEventType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

/**
 * Created by Robert Duriancik on 3/12/19.
 */
@ExperimentalCoroutinesApi
class TaskRepository private constructor(context: Context) {

    private val mTaskDao = Room.databaseBuilder(context, TaskDatabase::class.java, "tasks-database")
        .build()
        .taskDao()

    private val mDatabaseObserver by lazy { BroadcastChannel<DatabaseEvent<Task>>(1) }

    suspend fun addTask(task: Task) {
        withContext(Dispatchers.IO) {
            mTaskDao.insertTask(task)
            mDatabaseObserver.send(DatabaseEvent(DatabaseEventType.INSERTED, task))
        }
    }

    suspend fun deleteTask(task: Task) {
        withContext(Dispatchers.IO) {
            mTaskDao.deleteTask(task)
            mDatabaseObserver.send(DatabaseEvent(DatabaseEventType.REMOVED, task))
        }
    }

    suspend fun updateTask(task: Task) {
        withContext(Dispatchers.IO) {
            mTaskDao.updateTask(task)
            mDatabaseObserver.send(DatabaseEvent(DatabaseEventType.UPDATED, task))
        }
    }

    suspend fun observeTasks(): Flow<DatabaseEvent<Task>> {
        return mTaskDao.getAll()
            .asFlow()
            .map { DatabaseEvent(DatabaseEventType.INSERTED, it) }
            .onCompletion { emitAll(mDatabaseObserver.asFlow()) }
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