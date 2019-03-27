package com.android.roomdbobserverdemo

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.roomdbobserverdemo.databinding.ActivityMainBinding
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity(), TaskAdapter.TaskItemListener {

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mAdapter: TaskAdapter

    private lateinit var mViewModel: MainViewModel

    private var mTasksDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val factory = MainViewModelFactory(application)
        mViewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        mBinding.viewModel = mViewModel
        setupTaskRecycler()
        setupObservers()
    }

    override fun onDestroy() {
        mTasksDisposable?.dispose()
        super.onDestroy()
    }

    private fun setupTaskRecycler() {
        mAdapter = TaskAdapter(this)
        mBinding.recyclerTask.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }
    }

    private fun setupObservers() {
        mViewModel.toastText.observe(this, Observer { text ->
            Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
        })
        mTasksDisposable = mViewModel.observeTasks()
            .subscribe({ databaseEvent ->
                when (databaseEvent.eventType) {
                    DatabaseEventType.INSERTED -> {
                        mAdapter.addItem(databaseEvent.value)
                        hideKeyboard()
                    }
                    DatabaseEventType.UPDATED -> mAdapter.updateItem(databaseEvent.value)
                    DatabaseEventType.REMOVED -> mAdapter.removeItem(databaseEvent.value)
                }
                setTaskRecyclerVisibility(!mAdapter.isEmpty())
            }, {})
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

    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = currentFocus ?: View(this)
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onEditTaskClick(task: Task) {
        val dialog = EditTaskDialog.newInstance(task)
        dialog.show(supportFragmentManager, EditTaskDialog::class.java.simpleName)
    }

    override fun onDeleteTaskClick(task: Task) {
        mViewModel.deleteTask(task)
    }

    override fun onTaskCheckedChanged(task: Task, isChecked: Boolean) {
        mViewModel.taskCheckedChanged(task, isChecked)
    }
}
