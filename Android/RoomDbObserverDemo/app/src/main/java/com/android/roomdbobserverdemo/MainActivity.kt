package com.android.roomdbobserverdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.roomdbobserverdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskAdapter.TaskItemListener {

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mAdapter: TaskAdapter

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val factory = MainViewModelFactory(this)
        mViewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        setupTaskRecycler()
        setupObservers()
    }

    private fun setupTaskRecycler() {
        mAdapter = TaskAdapter(this)

    }

    private fun setupObservers() {
        mViewModel.task.observe(this, Observer { databaseEvent ->
            when (databaseEvent.eventType) {
                DatabaseEventType.INSERTED -> mAdapter.addItem(databaseEvent.value)
                DatabaseEventType.UPDATED -> mAdapter.updateItem(databaseEvent.value)
                DatabaseEventType.REMOVED -> mAdapter.removeItem(databaseEvent.value)
            }
        })
    }

    override fun onTaskChecked(task: Task, isChecked: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onEditTaskClick(task: Task) {
        val dialog = EditTaskDialog.newInstance(task)
        dialog.show(supportFragmentManager, EditTaskDialog::class.java.simpleName)
    }
}
