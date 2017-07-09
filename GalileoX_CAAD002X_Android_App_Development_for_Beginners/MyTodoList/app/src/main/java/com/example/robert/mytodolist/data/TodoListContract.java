package com.example.robert.mytodolist.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by robert on 9.7.2017.
 */

public class TodoListContract {

    private TodoListContract() {
    }

    public static final String CONTENT_AUTHORITY = "robert.example.com";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String PATH_TODO = "todo";

    public static final class TodoEntry implements BaseColumns {
        public static final String TABLE_NAME = "todo";
        public static final String COLUMN_DESCRIPTION = "desc";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_DONE = "done";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TODO).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_TODO;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_TODO;
    }
}
