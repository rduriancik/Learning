package com.example.robert.kotlinmvpdemo.userDetails.ui

import com.example.robert.kotlinmvpdemo.base.View
import com.example.robert.kotlinmvpdemo.entities.User

/**
 * Created by robert on 26.7.2017.
 */
interface UserDetailsView : View {
    fun showUserDetails(user: User)
    fun showNoUserError()
}