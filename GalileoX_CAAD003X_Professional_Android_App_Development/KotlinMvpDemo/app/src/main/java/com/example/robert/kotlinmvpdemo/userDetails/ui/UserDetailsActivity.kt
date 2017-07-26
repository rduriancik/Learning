package com.example.robert.kotlinmvpdemo.userDetails.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.robert.kotlinmvpdemo.R
import com.example.robert.kotlinmvpdemo.USER_KEY
import com.example.robert.kotlinmvpdemo.entities.User
import com.example.robert.kotlinmvpdemo.userDetails.UserDetailsPresenter
import com.example.robert.kotlinmvpdemo.userDetails.UserDetailsPresenterImpl
import kotlinx.android.synthetic.main.activity_user_details.*
import org.jetbrains.anko.toast

/**
 * Created by robert on 26.7.2017.
 */
class UserDetailsActivity : AppCompatActivity(), UserDetailsView {

    private val presenter: UserDetailsPresenter<UserDetailsView> by lazy {
        UserDetailsPresenterImpl(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val user = intent.getParcelableExtra<User>(USER_KEY)
        presenter.user = user
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showUserDetails(user: User) {
        userFullName.text = "${user.name} ${user.surname}"
    }

    override fun showNoUserError() {
        toast(R.string.no_user_error)
        finish()
    }
}