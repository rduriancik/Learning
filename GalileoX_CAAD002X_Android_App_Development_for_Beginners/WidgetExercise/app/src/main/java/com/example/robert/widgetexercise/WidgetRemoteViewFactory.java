package com.example.robert.widgetexercise;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 11.7.2017.
 */

public class WidgetRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private List<String> mNames;

    public WidgetRemoteViewFactory(Context context, Intent intent) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {
        mNames = new ArrayList<>();
    }

    private void populateListItem() {
        mNames.add("John");
        mNames.add("Mary");
        mNames.add("Emma");
        mNames.add("William");
        mNames.add("Noah");
        mNames.add("Susan");
        mNames.add("Patricia");
        mNames.add("Robert");
    }

    @Override
    public void onDataSetChanged() {
        populateListItem();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mNames.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        String name = mNames.get(position);

        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_row);
        views.setTextViewText(R.id.name, name);

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
