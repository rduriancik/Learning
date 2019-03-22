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

    private val mComparator = Comparator<Task> { o1, o2 ->
        if (o1.isDone != o2.isDone) {
            if (o1.isDone) 1 else -1
        } else {
            (o1.created - o2.created).toInt()
        }
    }

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

    fun isEmpty(): Boolean = mTasks.isEmpty()

    private fun sortItems() {
        mTasks.sortWith(mComparator)
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.data = task
            binding.taskCheckbox.setOnCheckedChangeListener { _, isChecked ->
                binding.data?.isDone = isChecked
                sortItems()
            }
            binding.executePendingBindings()
        }
    }

    interface TaskItemListener {
        fun onEditTaskClick(task: Task)
    }
}