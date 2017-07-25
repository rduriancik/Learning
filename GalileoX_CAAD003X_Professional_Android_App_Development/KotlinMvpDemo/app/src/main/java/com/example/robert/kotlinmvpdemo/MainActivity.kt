package com.example.robert.kotlinmvpdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), CreateUserView {

    private val presenter: CreateUserPresenter<CreateUserView> by lazy {
        CreateUserPresenterImpl(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveUserBtn.setOnClickListener {
            presenter.saveUser(editTxtName.textValue(), editTxtSurname.textValue())
        }
    }

    override fun showEmptyNameError() {
        editTxtName.error = getString(R.string.name_empty_error)
    }

    override fun showEmptySurnameError() {
        editTxtSurname.error = getString(R.string.surname_empty_error)
    }

    override fun showUserSaved() {
        toast(R.string.user_saved)
    }

    override fun showUserDetails(user: User) {

    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
