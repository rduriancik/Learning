package com.android.roomdbobserverdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.roomdbobserverdemo.databinding.ItemTaskBinding

/**
 * Created by Robert Duriancik on 3/5/19.
 */
class TaskAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = DataBindingUtil.inflate<ItemTaskBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_task,
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.binding.setVariable(BR.data, tasks[position])
    }

    class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)
}