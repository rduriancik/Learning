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
data class Task(@PrimaryKey(autoGenerate = true) val id: Int, var description: String) : Parcelable