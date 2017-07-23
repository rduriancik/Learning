package com.example.robert.daggerdemo.data;

import android.content.Context;
import android.content.res.Resources;

import com.example.robert.daggerdemo.data.model.User;
import com.example.robert.daggerdemo.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by robert on 23.7.2017.
 */

@Singleton
public class DataManager {

    private Context mContext;
    private DbHelper mDbHelper;
    private SharedPreferencesHelper mSharedPreferencesHelper;

    @Inject
    public DataManager(@ApplicationContext Context context,
                       DbHelper dbHelper,
                       SharedPreferencesHelper sharedPreferencesHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mSharedPreferencesHelper = sharedPreferencesHelper;
    }

    public void saveAccessToken(String accessToken) {
        mSharedPreferencesHelper.put(SharedPreferencesHelper.PREF_KEY_ACCESS_TOKEN, accessToken);
    }

    private String getAccessToken() {
        return mSharedPreferencesHelper.get(SharedPreferencesHelper.PREF_KEY_ACCESS_TOKEN, null);
    }

    public Long createUser(User user) throws Exception {
        return mDbHelper.insertUser(user);
    }

    public User getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
        return mDbHelper.getUser(userId);
    }
}
