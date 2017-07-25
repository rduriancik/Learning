package com.example.robert.kotlinmvpdemo

/**
 * Created by robert on 25.7.2017.
 */
interface CreateUserView : View {
    fun showEmptyNameError()
    fun showEmptySurnameError()
    fun showUserSaved()
    fun showUserDetails(user: User)
}