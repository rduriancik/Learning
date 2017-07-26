package com.example.robert.kotlinmvpdemo.userDetails

import com.example.robert.kotlinmvpdemo.base.Presenter
import com.example.robert.kotlinmvpdemo.base.View
import com.example.robert.kotlinmvpdemo.entities.User

/**
 * Created by robert on 26.7.2017.
 */
interface UserDetailsPresenter<T : View> : Presenter<T> {
    var user: User?
}