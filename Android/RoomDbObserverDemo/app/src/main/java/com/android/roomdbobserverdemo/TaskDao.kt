package com.android.roomdbobserverdemo

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Created by Robert Duriancik on 3/5/19.
 */
@Dao
interface TaskDao {
    @Insert
    fun insertTask(task: Task): Completable

    @Update
    fun updateTask(task: Task): Completable

    @Query("SELECT * FROM Task WHERE id = :id")
    fun getTask(id: Int): Maybe<Task>

    @Query("SELECT * FROM Task")
    fun getAll(): Single<List<Task>>

    @Delete
    fun deleteTask(task: Task): Completable
}