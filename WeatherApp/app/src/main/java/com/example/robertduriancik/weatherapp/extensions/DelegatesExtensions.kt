package com.example.robertduriancik.weatherapp.extensions

import kotlin.reflect.KProperty

/**
 * Created by robert-ntb on 4/28/17.
 */

object DelegatesExt {
    fun <T> notNullSingelValue() = NotNullSingleValueVar<T>()
}

class NotNullSingleValueVar<T> {
    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("${property.name} no initialized")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}