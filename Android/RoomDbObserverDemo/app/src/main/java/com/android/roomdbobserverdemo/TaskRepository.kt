package com.android.roomdbobserverdemo

import android.content.Context
import androidx.room.Room

/**
 * Created by Robert Duriancik on 3/12/19.
 */
class TaskRepository private constructor(context: Context) {

    private val mTaskDao = Room.databaseBuilder(context, TaskDatabase::class.java, "task-database")
        .build()
        .taskDao()

    fun addTask(task: Task) {
        mTaskDao.insertTask(task)
    }

    fun deleteTask(task: Task) {

    }

    fun updateTask(task: Task) {

    }

    companion object {
        @Volatile
        private var instance: TaskRepository? = null

        fun getInstance(context: Context): TaskRepository {
            return instance ?: synchronized(this) {
                instance ?: TaskRepository(context.applicationContext).also { instance = it }
            }
        }
    }
}