package com.example.robert.mvvmsampleapp.view.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.robert.mvvmsampleapp.R
import com.example.robert.mvvmsampleapp.databinding.FragmentProjectListBinding
import com.example.robert.mvvmsampleapp.service.model.Project
import com.example.robert.mvvmsampleapp.view.adapters.ProjectAdapter
import com.example.robert.mvvmsampleapp.view.callback.ProjectClickCallback
import com.example.robert.mvvmsampleapp.viewmodel.ProjectListViewModel

/**
 * Created by robert on 11.9.2017.
 */
class ProjectListFragment : LifecycleFragment() {
    companion object {
        const val TAG = "ProjectListFragment"
    }

    private lateinit var projectAdapter: ProjectAdapter
    private lateinit var binding: FragmentProjectListBinding
    private val projectClickCallback: ProjectClickCallback = object : ProjectClickCallback {
        override fun onClick(project: Project) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (activity as MainActivity).show(project)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentProjectListBinding>(inflater,
                R.layout.fragment_project_list, container, false)

        projectAdapter = ProjectAdapter(projectClickCallback)
        binding.projectList.adapter = projectAdapter
        binding.isLoading = true

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(ProjectListViewModel::class.java)
        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectListViewModel) {
        viewModel.projectListObservable.observe(this, Observer { projects ->
            projects?.let {
                binding.isLoading = false
                projectAdapter.projectList = it
            }
        })
    }
}