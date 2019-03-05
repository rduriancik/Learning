package com.android.roomdbobserverdemo

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Robert Duriancik on 3/5/19.
 */
@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}