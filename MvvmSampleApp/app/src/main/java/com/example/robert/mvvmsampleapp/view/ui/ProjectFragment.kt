package com.example.robert.mvvmsampleapp.view.ui

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.robert.mvvmsampleapp.R
import com.example.robert.mvvmsampleapp.databinding.FragmentProjectDetailsBinding
import com.example.robert.mvvmsampleapp.di.Injectable
import com.example.robert.mvvmsampleapp.viewmodel.ProjectViewModel
import javax.inject.Inject

/**
 * Created by robert on 11.9.2017.
 */

class ProjectFragment : LifecycleFragment(), Injectable {
    companion object {
        const val KEY_PROJECT_ID = "project_id"

        fun forProject(projectId: String) = ProjectFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_PROJECT_ID, projectId)
            }
        }
    }

    @Inject lateinit var factory: ViewModelProvider.Factory
    private lateinit var binding: FragmentProjectDetailsBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_details, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this, factory).get(ProjectViewModel::class.java)
        viewModel.setProjectID(arguments.getString(KEY_PROJECT_ID))

        binding.projectViewModel = viewModel
        binding.isLoading = true

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectViewModel) {
        viewModel.projectObservable.observe(this, Observer { project ->
            project?.let {
                binding.isLoading = false
                viewModel.setProject(project)
            }
        })
    }
}