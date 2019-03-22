package com.android.roomdbobserverdemo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.roomdbobserverdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskAdapter.TaskItemListener {

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mAdapter: TaskAdapter

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val factory = MainViewModelFactory(application)
        mViewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        mBinding.viewModel = mViewModel
        setupTaskRecycler()
        setupObservers()
    }

    private fun setupTaskRecycler() {
        mAdapter = TaskAdapter(this)
        mBinding.recyclerTask.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }

    private fun setupObservers() {
        mViewModel.toastText.observe(this, Observer { text ->
            Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
        })
        mViewModel.task.observe(this, Observer { databaseEvent ->
            when (databaseEvent.eventType) {
                DatabaseEventType.INSERTED -> mAdapter.addItem(databaseEvent.value)
                DatabaseEventType.UPDATED -> mAdapter.updateItem(databaseEvent.value)
                DatabaseEventType.REMOVED -> mAdapter.removeItem(databaseEvent.value)
            }
            setTaskRecyclerVisibility(!mAdapter.isEmpty())
        })
    }

    private fun setTaskRecyclerVisibility(isVisible: Boolean) {
        if (isVisible) {
            mBinding.recyclerTask.visibility = View.VISIBLE
            mBinding.groupEmpty.visibility = View.GONE
        } else {
            mBinding.recyclerTask.visibility = View.GONE
            mBinding.groupEmpty.visibility = View.VISIBLE
        }
    }

    override fun onEditTaskClick(task: Task) {
        val dialog = EditTaskDialog.newInstance(task)
        dialog.show(supportFragmentManager, EditTaskDialog::class.java.simpleName)
    }
}
