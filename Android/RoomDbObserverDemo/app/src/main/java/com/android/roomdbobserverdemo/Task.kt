package com.android.roomdbobserverdemo

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Robert Duriancik on 3/5/19.
 */
@Entity
data class Task(@PrimaryKey(autoGenerate = true) val id: Int, val description: String)