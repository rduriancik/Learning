package com.example.robert.widgetexercise;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by robert on 11.7.2017.
 */

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetRemoteViewFactory(getApplicationContext(), intent);
    }
}
