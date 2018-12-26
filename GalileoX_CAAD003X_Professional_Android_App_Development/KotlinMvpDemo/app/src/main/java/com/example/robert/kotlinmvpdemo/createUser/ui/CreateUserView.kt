package com.example.robert.kotlinmvpdemo.createUser.ui

import com.example.robert.kotlinmvpdemo.base.View
import com.example.robert.kotlinmvpdemo.entities.User

/**
 * Created by robert on 25.7.2017.
 */
interface CreateUserView : View {
    fun showEmptyNameError()
    fun showEmptySurnameError()
    fun showUserSaved()
    fun showUserDetails(user: User)
}