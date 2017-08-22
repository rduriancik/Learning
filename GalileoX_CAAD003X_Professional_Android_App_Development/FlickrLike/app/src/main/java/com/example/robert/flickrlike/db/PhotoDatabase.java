package com.example.robert.flickrlike.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by robert on 22.8.2017.
 */

@Database(name = PhotoDatabase.NAME, version = PhotoDatabase.VERSION)
public class PhotoDatabase {
    public static final String NAME = "PhotoDatabase";
    public static final int VERSION = 1;
}
