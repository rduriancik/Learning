package com.android.roomdbobserverdemo.model.task

import androidx.room.*
import io.reactivex.Maybe

/**
 * Created by Robert Duriancik on 3/5/19.
 */
@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("SELECT * FROM Task WHERE id = :id")
    suspend fun getTask(id: Int): Maybe<Task>

    @Query("SELECT * FROM Task")
    suspend fun getAll(): List<Task>

    @Delete
    suspend fun deleteTask(task: Task)
}