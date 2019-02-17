package com.example.robert.asynctaskloader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

/**
 * Created by robert on 7.7.2017.
 */

public class NamesAsyncTaskLoader extends AsyncTaskLoader<ArrayList<String>> {

    public NamesAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<String> loadInBackground() {
        return loadNamesFromDB();
    }

    private ArrayList<String> loadNamesFromDB() {
        ArrayList<String> names = new ArrayList<>();
        names.add("John");
        names.add("Mary");
        names.add("Emma");
        names.add("Emma");
        names.add("Noah");
        return names;
    }
}
