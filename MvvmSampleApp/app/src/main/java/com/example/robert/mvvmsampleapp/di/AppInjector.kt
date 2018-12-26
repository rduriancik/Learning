package com.example.robert.mvvmsampleapp.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.example.robert.mvvmsampleapp.MVVMApplication
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

/**
 * Created by robert on 23.9.2017.
 */

object AppInjector {
    fun init(application: MVVMApplication) {
        DaggerAppComponent.builder()
                .application(application)
                .build()
                .inject(application)

        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {
            }

            override fun onActivityResumed(activity: Activity?) {
            }

            override fun onActivityStarted(activity: Activity?) {
            }

            override fun onActivityDestroyed(activity: Activity?) {
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                activity?.let { handleActivity(it) }
            }
        })
    }

    private fun handleActivity(activity: Activity) {
        when (activity) {
            is HasSupportFragmentInjector -> AndroidInjection.inject(activity)
            is FragmentActivity -> {
                activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                        object : FragmentManager.FragmentLifecycleCallbacks() {

                            override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
                                if (f != null && f is Injectable) {
                                    AndroidSupportInjection.inject(f)
                                }
                            }
                        }, true)
            }
        }
    }
}