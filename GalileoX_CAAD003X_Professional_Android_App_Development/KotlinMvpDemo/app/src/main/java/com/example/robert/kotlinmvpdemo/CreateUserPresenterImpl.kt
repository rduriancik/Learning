package com.example.robert.kotlinmvpdemo

/**
 * Created by robert on 25.7.2017.
 */
class CreateUserPresenterImpl(override var view: CreateUserView?) : CreateUserPresenter<CreateUserView> {
    override fun saveUser(name: String, surname: String) {

    }
}