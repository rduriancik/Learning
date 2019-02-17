package com.example.robert.mvvmsampleapp.view.adapters

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.robert.mvvmsampleapp.R
import com.example.robert.mvvmsampleapp.databinding.ProjectListItemBinding
import com.example.robert.mvvmsampleapp.service.model.Project
import com.example.robert.mvvmsampleapp.view.callback.ProjectClickCallback


/**
 * Created by robert on 11.9.2017.
 */
class ProjectAdapter(val projectClickCallback: ProjectClickCallback? = null) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    var projectList: List<Project>? = null
        set(value) {
            if (field == null) {
                field = value
                notifyItemRangeInserted(0, value?.size ?: 0)
            } else {
                val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                            this@ProjectAdapter.projectList?.get(oldItemPosition)?.id == value?.get(newItemPosition)?.id

                    override fun getOldListSize(): Int =
                            this@ProjectAdapter.projectList?.size ?: 0


                    override fun getNewListSize(): Int = value?.size ?: 0

                    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                        val old = value?.get(oldItemPosition)
                        val new = value?.get(newItemPosition)
                        return old?.id == new?.id &&
                                (new?.git_url === old?.git_url || new?.git_url == old?.git_url)
                    }
                })

                field = value
                result.dispatchUpdatesTo(this)
            }
        }

    override fun getItemCount(): Int = projectList?.size ?: 0

    override fun onBindViewHolder(holder: ProjectViewHolder?, position: Int) {
        holder?.binding?.project = projectList?.get(position)
        holder?.binding?.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProjectViewHolder {
        val binding = DataBindingUtil.inflate<ProjectListItemBinding>(LayoutInflater.from(parent?.context),
                R.layout.project_list_item, parent, false)
        binding.callback = projectClickCallback
        return ProjectViewHolder(binding)
    }

    class ProjectViewHolder(val binding: ProjectListItemBinding) : RecyclerView.ViewHolder(binding.root)
}