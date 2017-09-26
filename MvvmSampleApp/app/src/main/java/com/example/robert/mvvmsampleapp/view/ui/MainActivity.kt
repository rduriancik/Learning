package com.example.robert.mvvmsampleapp.view.ui

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.robert.mvvmsampleapp.R
import com.example.robert.mvvmsampleapp.service.model.Project
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : LifecycleActivity(), HasSupportFragmentInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            ProjectListFragment().let {
                supportFragmentManager.beginTransaction()
                        .add(R.id.fragment_container, it, ProjectListFragment.TAG)
                        .commit()
            }
        }
    }

    fun show(project: Project) {
        val projectFragment = ProjectFragment.forProject(project.name)

        supportFragmentManager.beginTransaction()
                .addToBackStack("project")
                .replace(R.id.fragment_container, projectFragment, null)
                .commit()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
}
