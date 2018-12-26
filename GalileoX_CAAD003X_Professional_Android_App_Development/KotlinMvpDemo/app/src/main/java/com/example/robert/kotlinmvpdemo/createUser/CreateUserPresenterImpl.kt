package com.example.robert.kotlinmvpdemo.createUser

import com.example.robert.kotlinmvpdemo.createUser.ui.CreateUserView
import com.example.robert.kotlinmvpdemo.entities.User
import com.example.robert.kotlinmvpdemo.entities.UserError
import com.example.robert.kotlinmvpdemo.entities.UserStore
import com.example.robert.kotlinmvpdemo.entities.UserValidator

/**
 * Created by robert on 25.7.2017.
 */
class CreateUserPresenterImpl(override var view: CreateUserView?) : CreateUserPresenter<CreateUserView> {
    override fun saveUser(name: String, surname: String) {
        val user = User(name, surname)
        when (UserValidator.validateUser(user)) {
            UserError.EMPTY_NAME -> view?.showEmptyNameError()
            UserError.EMPTY_SURNAME -> view?.showEmptySurnameError()
            UserError.NO_ERROR -> {
                UserStore.saveUser(user)
                view?.showUserSaved()
                view?.showUserDetails(user)
            }
        }
    }
}