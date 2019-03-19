package com.android.roomdbobserverdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.roomdbobserverdemo.databinding.ItemTaskBinding

/**
 * Created by Robert Duriancik on 3/5/19.
 */
class TaskAdapter(private val mListener: TaskItemListener) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val mTasks = mutableListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = DataBindingUtil.inflate<ItemTaskBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_task,
            parent,
            false
        )
        binding.listener = mListener
        return TaskViewHolder(binding)
    }

    override fun getItemCount() = mTasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(mTasks[position])
    }

    fun addItem(task: Task) {
        mTasks.add(task)
        notifyItemInserted(mTasks.size - 1)
    }

    fun updateItem(task: Task) {
        val index = mTasks.indexOfFirst { it.id == task.id }
        mTasks[index] = task
        notifyItemChanged(index)
    }

    fun removeItem(task: Task) {
        val index = mTasks.indexOf(task)
        mTasks.removeAt(index)
        notifyItemRemoved(index)
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.data = task
            binding.taskCheckbox.setOnCheckedChangeListener { _, isChecked -> mListener.onTaskChecked(task, isChecked) }
            binding.executePendingBindings()
        }
    }

    interface TaskItemListener {
        fun onTaskChecked(task: Task, isChecked: Boolean)

        fun onEditTaskClick(task: Task)
    }
}