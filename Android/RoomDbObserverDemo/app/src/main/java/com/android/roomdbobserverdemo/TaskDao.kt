package com.android.roomdbobserverdemo

import androidx.room.*

/**
 * Created by Robert Duriancik on 3/5/19.
 */
@Dao
interface TaskDao {
    @Insert
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("SELECT * FROM Task WHERE id = :id")
    fun getTask(id: Int): Task

    @Delete
    fun deleteTask(task: Task)
}