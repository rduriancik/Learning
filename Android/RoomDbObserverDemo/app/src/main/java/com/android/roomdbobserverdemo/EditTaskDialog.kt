package com.android.roomdbobserverdemo

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.android.roomdbobserverdemo.databinding.EditItemDialogBinding

/**
 * Created by Robert Duriancik on 3/10/19.
 */
class EditTaskDialog : DialogFragment() {

    private lateinit var mBinding: EditItemDialogBinding

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = MainViewModelFactory(requireActivity().application)
        mViewModel = ViewModelProviders.of(requireActivity(), factory).get(MainViewModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mBinding = EditItemDialogBinding.inflate(LayoutInflater.from(requireContext()))
        val task = arguments?.getParcelable<Task>(Task::class.java.simpleName)
            ?: throw IllegalArgumentException("No task provided")
        mBinding.inputEdit.setText(task.description, TextView.BufferType.EDITABLE)
        mBinding.inputEdit.setSelection(task.description.length)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.edit_task_dialog_title))
            .setView(mBinding.root)
            .setPositiveButton(getString(R.string.edit_task_dialog_save)) { dialog, _ ->
                task.description = mBinding.inputEdit.text.toString()
                mViewModel.updateTask(task)
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.edit_task_dialog_cancel)) { dialog, _ -> dialog.dismiss() }
            .create()
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.colorSubmit))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(requireContext(), android.R.color.darker_gray))
        }
        return dialog
    }

    companion object {
        fun newInstance(task: Task): EditTaskDialog {
            return EditTaskDialog().apply {
                arguments = Bundle().apply {
                    putParcelable(Task::class.java.simpleName, task)
                }
            }
        }
    }
}