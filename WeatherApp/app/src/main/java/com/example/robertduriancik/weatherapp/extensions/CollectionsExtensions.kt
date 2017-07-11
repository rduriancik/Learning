package com.example.robertduriancik.weatherapp.extensions

/**
 * Created by robert on 11.7.2017.
 */

fun <K, V : Any> MutableMap<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!) }).toTypedArray()