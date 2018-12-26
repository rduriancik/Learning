package com.example.robert.kotlinmvpdemo.userDetails

import com.example.robert.kotlinmvpdemo.entities.User
import com.example.robert.kotlinmvpdemo.userDetails.ui.UserDetailsView

/**
 * Created by robert on 26.7.2017.
 */
class UserDetailsPresenterImpl(override var view: UserDetailsView?) : UserDetailsPresenter<UserDetailsView> {
    override var user: User? = null
        set(value) {
            field = value
            if (field != null) {
                view?.showUserDetails(field!!)
            } else {
                view?.showNoUserError()
            }
        }
}