package com.android.roomdbobserverdemo

/**
 * Created by Robert Duriancik on 2/25/19.
 */
data class DatabaseEvent<T>(val eventType: DatabaseEventType, val value: T)