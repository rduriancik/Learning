package com.example.robert.mvvmsampleapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.robert.mvvmsampleapp.service.model.Project
import com.example.robert.mvvmsampleapp.view.ProjectFragment
import com.example.robert.mvvmsampleapp.view.ProjectListFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = ProjectListFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, ProjectListFragment.TAG)
                    .commit()
        }
    }

    fun show(project: Project) {
        val projectFragment = ProjectFragment.forProject(project.name)

        supportFragmentManager.beginTransaction()
                .addToBackStack("project")
                .replace(R.id.fragment_container, projectFragment, null)
                .commit()
    }
}
