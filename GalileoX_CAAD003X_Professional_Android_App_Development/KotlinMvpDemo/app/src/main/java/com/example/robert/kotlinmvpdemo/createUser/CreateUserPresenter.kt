package com.example.robert.kotlinmvpdemo.createUser

import com.example.robert.kotlinmvpdemo.base.Presenter
import com.example.robert.kotlinmvpdemo.base.View

/**
 * Created by robert on 25.7.2017.
 */
interface CreateUserPresenter<T : View> : Presenter<T> {
    fun saveUser(name: String, surname: String)
}