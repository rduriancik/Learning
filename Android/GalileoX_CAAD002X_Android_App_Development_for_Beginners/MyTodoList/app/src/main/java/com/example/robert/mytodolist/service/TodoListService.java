package com.example.robert.mytodolist.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.robert.mytodolist.data.TodoListContract;

/**
 * Created by robert on 10.7.2017.
 */

public class TodoListService extends IntentService {

    public static final String EXTRA_TASK_DESCRIPTION = "EXTRA_TASK_DESCRIPTION";

    public TodoListService() {
        super("TodoListService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String taskDescription = intent.getStringExtra(EXTRA_TASK_DESCRIPTION);

        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoListContract.TodoEntry.COLUMN_DESCRIPTION, taskDescription);

        getContentResolver().insert(TodoListContract.TodoEntry.CONTENT_URI, contentValues);
    }
}
