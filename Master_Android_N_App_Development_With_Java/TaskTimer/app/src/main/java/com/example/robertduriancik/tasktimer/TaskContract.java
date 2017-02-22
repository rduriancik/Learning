package com.example.robertduriancik.tasktimer;

import android.provider.BaseColumns;

/**
 * Created by robert on 22.2.2017.
 */

public class TaskContract {
    static final String TABLE_NAME = "Tasks";

    // Tasks fields
    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String TASKS_NAME = "Name";
        public static final String TASKS_DESCRIPTION = "Description";
        public static final String TASKS_SORTORDER = "SortOrder";

        private Columns() {
            // Private constructor to prevent instantiation
        }
    }
}
