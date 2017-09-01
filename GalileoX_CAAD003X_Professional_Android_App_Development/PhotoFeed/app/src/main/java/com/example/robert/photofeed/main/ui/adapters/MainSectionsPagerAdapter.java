package com.example.robert.photofeed.main.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by robert on 31.8.2017.
 */

public class MainSectionsPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private Fragment[] fragments;

    public MainSectionsPagerAdapter(FragmentManager fm, String[] titles, Fragment[] fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
