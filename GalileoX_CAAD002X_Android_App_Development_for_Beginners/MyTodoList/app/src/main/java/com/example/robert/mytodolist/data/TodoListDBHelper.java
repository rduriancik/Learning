package com.example.robert.mytodolist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by robert on 9.7.2017.
 */

public class TodoListDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "todolist.db";

    public TodoListDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE = "CREATE TABLE " + TodoListContract.TodoEntry.TABLE_NAME + " (" +
                TodoListContract.TodoEntry._ID + " INTEGER PRIMARY KEY," +
                TodoListContract.TodoEntry.COLUMN_DATE + " TEXT NOT NULL," +
                TodoListContract.TodoEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL," +
                TodoListContract.TodoEntry.COLUMN_DONE + " INTEGER, " +
                "UNIQUE (" + TodoListContract.TodoEntry.COLUMN_DATE + ", " +
                TodoListContract.TodoEntry.COLUMN_DESCRIPTION + ") ON " +
                "CONFLICT IGNORE" +
                ");";

        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TodoListContract.TodoEntry.TABLE_NAME);
        onCreate(db);
    }
}
