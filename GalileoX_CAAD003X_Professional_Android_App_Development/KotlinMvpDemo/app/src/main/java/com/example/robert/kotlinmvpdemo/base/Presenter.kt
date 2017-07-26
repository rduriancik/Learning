package com.example.robert.kotlinmvpdemo.base

/**
 * Created by robert on 25.7.2017.
 */
interface Presenter<T : View> {
    var view: T?

    fun onDestroy() {
        view = null
    }
}