package com.android.roomdbobserverdemo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Robert Duriancik on 3/5/19.
 */
@Parcelize
@Entity
data class Task(
    var description: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val created: Long = System.currentTimeMillis(),
    var isDone: Boolean = false
) : Parcelable