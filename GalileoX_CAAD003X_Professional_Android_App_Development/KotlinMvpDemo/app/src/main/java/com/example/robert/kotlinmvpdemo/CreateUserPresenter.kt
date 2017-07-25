package com.example.robert.kotlinmvpdemo

/**
 * Created by robert on 25.7.2017.
 */
interface CreateUserPresenter<T : View> : Presenter<T> {
    fun saveUser(name: String, surname: String)
}