package com.example.robert.mytodolist;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.robert.mytodolist.service.TodoListService;

/**
 * Created by robert on 9.7.2017.
 */

public class AddTaskDialogFragment extends DialogFragment {

    public static AddTaskDialogFragment getInstance(String idTask) {
        Bundle bundle = new Bundle();
        bundle.putString("ID_TASK", idTask);
        AddTaskDialogFragment fragment = new AddTaskDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.task_create_dialog);
        builder.setTitle("Add a new task");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getContext(), TodoListService.class);
                intent.putExtra(TodoListService.EXTRA_TASK_DESCRIPTION, "");
                getActivity().startService(intent);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }
}
